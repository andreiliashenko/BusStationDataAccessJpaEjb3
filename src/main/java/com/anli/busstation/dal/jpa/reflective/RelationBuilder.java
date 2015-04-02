package com.anli.busstation.dal.jpa.reflective;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Singleton
public class RelationBuilder {

    public EntityRelationHolder buildRelation(Class entityClass) {
        String name = ((Entity) entityClass.getAnnotation(Entity.class)).name();
        List<FieldDescriptor> fields = new LinkedList<>();
        while (entityClass != null) {
            for (Field field : entityClass.getDeclaredFields()) {
                FieldDescriptor descriptor = null;
                if (field.isAnnotationPresent(OneToOne.class)) {
                    descriptor = getDescriptor(field, false, field.getType());
                } else if (field.isAnnotationPresent(OneToMany.class)) {
                    ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                    Class elementClass = (Class) genericType.getActualTypeArguments()[0];
                    descriptor = getDescriptor(field, true, elementClass);
                }
                if (descriptor != null) {
                    descriptor.getField().setAccessible(true);
                    fields.add(descriptor);
                }
            }
            entityClass = entityClass.getSuperclass();
        }
        return getHolder(name, fields);
    }

    protected EntityRelationHolder getHolder(String name, Collection<FieldDescriptor> fields) {
        return new EntityRelationHolder(name, fields);
    }

    protected FieldDescriptor getDescriptor(Field field, boolean isCollection, Class elementClass) {
        return new FieldDescriptor(field, isCollection, elementClass);
    }
}
