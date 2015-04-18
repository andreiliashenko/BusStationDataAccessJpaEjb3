package com.anli.busstation.dal.jpa.reflective;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Singleton
public class EntityDescriptorBuilder {

    public EntityDescriptor buildRelation(Class entityClass) {
        String name = ((Entity) entityClass.getAnnotation(Entity.class)).name();
        List<FieldDescriptor> fields = new LinkedList<>();
        FieldDescriptor idField = null;
        while (entityClass != null) {
            for (Field field : entityClass.getDeclaredFields()) {
                int fieldModifiers = field.getModifiers();
                if (Modifier.isStatic(fieldModifiers) || Modifier.isFinal(fieldModifiers)
                        || Modifier.isTransient(fieldModifiers)) {
                    continue;
                }
                FieldDescriptor descriptor;
                Method getter = getGetterMethod(entityClass, field);
                Method setter = getSetterMethod(entityClass, field);
                if (getter == null || setter == null) {
                    continue;
                }
                if (field.isAnnotationPresent(OneToMany.class)
                        || field.isAnnotationPresent(ManyToMany.class)) {
                    ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                    Class elementClass = (Class) genericType.getActualTypeArguments()[0];
                    descriptor = getDescriptor(getter, setter, (Class) genericType.getRawType(),
                            elementClass);
                } else {
                    descriptor = getDescriptor(getter, setter, null, field.getType());
                }
                if (field.isAnnotationPresent(Id.class)) {
                    idField = descriptor;
                }
                fields.add(descriptor);
            }
            entityClass = entityClass.getSuperclass();
        }
        return getHolder(name, fields, idField);
    }

    protected Method getGetterMethod(Class clazz, Field field) {
        String fieldName = field.getName();
        String generalGetterName = getPrefixedName("get", fieldName);
        Method getterMethod = getClassMethod(clazz, generalGetterName);
        if (getterMethod != null) {
            return getterMethod;
        }
        String booleanGetterName = getPrefixedName("is", fieldName);
        return getClassMethod(clazz, booleanGetterName);
    }

    protected Method getSetterMethod(Class clazz, Field field) {
        String fieldName = field.getName();
        String setterName = getPrefixedName("set", fieldName);
        return getSetterRecursively(clazz, setterName, field.getType());
    }

    protected Method getSetterRecursively(Class clazz, String setterName, Class parameterClass) {
        if (parameterClass == null) {
            return null;
        }
        Method setter = getClassMethod(clazz, setterName, parameterClass);
        if (setter != null) {
            return setter;
        }
        for (Class interfaceClass : parameterClass.getInterfaces()) {
            setter = getSetterRecursively(clazz, setterName, interfaceClass);
            if (setter != null) {
                return setter;
            }
        }
        return getSetterRecursively(clazz, setterName, parameterClass.getSuperclass());
    }

    protected Method getClassMethod(Class clazz, String name, Class... paramTypes) {
        try {
            return clazz.getMethod(name, paramTypes);
        } catch (NoSuchMethodException nsmException) {
            return null;
        }
    }

    protected String getPrefixedName(String prefix, String name) {
        char firstCharacter = Character.toUpperCase(name.charAt(0));
        return prefix + firstCharacter + name.substring(1);
    }

    protected EntityDescriptor getHolder(String name, Collection<FieldDescriptor> fields,
            FieldDescriptor idField) {
        return new EntityDescriptor(name, fields, idField);
    }

    protected FieldDescriptor getDescriptor(Method getter, Method setter,
            Class collectionClass, Class elementClass) {
        return new FieldDescriptor(getter, setter, collectionClass, elementClass);
    }
}
