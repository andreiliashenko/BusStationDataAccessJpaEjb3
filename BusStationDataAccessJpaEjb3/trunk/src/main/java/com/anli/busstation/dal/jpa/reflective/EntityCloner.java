package com.anli.busstation.dal.jpa.reflective;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import org.eclipse.persistence.indirection.IndirectCollection;

@Singleton
public class EntityCloner {

    private final DescriptorHolder descriptorHolder;

    @Inject
    public EntityCloner(DescriptorHolder relationHolder) {
        this.descriptorHolder = relationHolder;
    }

    public <E> E clone(E sourceEntity) {
        if (sourceEntity == null) {
            return null;
        }
        Class<E> entityClass = (Class<E>) sourceEntity.getClass();
        E clonedEntity;
        try {
            clonedEntity = entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        populateEntity(sourceEntity, clonedEntity);
        return clonedEntity;
    }

    public <E> List populateEntity(E sourceEntity, E targetEntity) {
        return populateEntity(sourceEntity, targetEntity, null);
    }

    public <E> List populateEntity(E sourceEntity, E targetEntity, EntityManager manager) {
        LinkedList inconsistent = new LinkedList<>();
        EntityDescriptor entityDescriptor =
                descriptorHolder.getHolder(sourceEntity.getClass());
        for (FieldDescriptor fieldDescriptor : entityDescriptor.getFields()) {
            try {
                inconsistent.addAll(populateField(fieldDescriptor, sourceEntity,
                        targetEntity, manager));
            } catch (IllegalAccessException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        }
        return inconsistent;
    }

    protected List populateField(FieldDescriptor descriptor, Object sourceEntity, Object targetEntity,
            EntityManager manager) throws IllegalAccessException, InvocationTargetException {
        Method getter = descriptor.getGetter();
        Method setter = descriptor.getSetter();
        Object value = getter.invoke(sourceEntity);
        if (descriptor.isCollection()) {
            return populateCollection(getter, setter, targetEntity, (Collection) value, manager);
        } else if (descriptor.isReference()) {
            Object inconsistent = populateReference(setter, targetEntity, value, manager);
            return inconsistent == null ? Collections.emptyList()
                    : Collections.singletonList(inconsistent);
        } else {
            populateFieldValue(setter, targetEntity, value);
            return Collections.emptyList();
        }
    }

    protected void populateFieldValue(Method setter, Object targetEntity,
            Object value) throws IllegalAccessException, InvocationTargetException {
        setter.invoke(targetEntity, value);
    }

    protected Object populateReference(Method setter, Object targetEntity, Object value,
            EntityManager manager) throws IllegalAccessException, InvocationTargetException {
        Object actualValue;
        if (manager == null) {
            actualValue = clone(value);
        } else {
            actualValue = getReference(manager, value);
            if (actualValue == null && value != null) {
                return value;
            }
        }
        populateFieldValue(setter, targetEntity, actualValue);
        return null;
    }

    protected Object getReference(EntityManager manager, Object detachedEntity)
            throws IllegalAccessException, InvocationTargetException {
        if (detachedEntity == null) {
            return null;
        }
        Class entityClass = detachedEntity.getClass();
        Method idGetter = descriptorHolder.getHolder(entityClass)
                .getIdField().getGetter();
        return manager.find(entityClass, idGetter.invoke(detachedEntity));
    }

    protected List populateCollection(Method getter, Method setter, Object targetEntity, Collection value,
            EntityManager manager) throws IllegalAccessException, InvocationTargetException {
        if (manager == null) {
            Collection clonedCollection = cloneCollection(value);
            populateFieldValue(setter, targetEntity, clonedCollection);
            return Collections.emptyList();
        } else {
            return mergeCollection(getter, targetEntity, value, manager);
        }
    }

    protected List mergeCollection(Method getter, Object targetEntity, Collection value,
            EntityManager manager) throws IllegalAccessException, InvocationTargetException {
        if (value == null) {
            return Collections.emptyList();
        }
        LinkedList inconsistent = new LinkedList();
        Collection targetCollection = (Collection) getter.invoke(targetEntity);
        targetCollection.clear();
        for (Object element : value) {
            Object reference = getReference(manager, element);
            if (reference == null && element != null) {
                inconsistent.add(element);
            } else {
                targetCollection.add(reference);
            }
        }
        return inconsistent;
    }

    public <C extends Collection> C cloneCollection(C sourceCollection) {
        return cloneCollection(sourceCollection, true);
    }

    public <C extends Collection> C cloneCollection(C sourceCollection, boolean nullLazy) {
        C clonedCollection = getCollectionInstance(sourceCollection, nullLazy);
        if (clonedCollection == null) {
            return null;
        }
        for (Object sourceElement : sourceCollection) {
            clonedCollection.add(clone(sourceElement));
        }
        return clonedCollection;
    }

    protected <C extends Collection> C getCollectionInstance(C sourceCollection, boolean nullLazy) {
        if (sourceCollection == null) {
            return null;
        }
        if (sourceCollection instanceof IndirectCollection && nullLazy) {
            return null;
        }
        if (sourceCollection instanceof List) {
            return (C) new ArrayList<>(sourceCollection.size());
        }
        if (sourceCollection instanceof Set) {
            return (C) new HashSet<>((int) (sourceCollection.size() / 0.75));
        }
        throw new RuntimeException();
    }
}
