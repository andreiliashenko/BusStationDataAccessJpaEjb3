package com.anli.busstation.dal.jpa.reflective;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

public class FieldDescriptor {

    protected final Method getter;
    protected final Method setter;
    protected final Class collectionClass;
    protected final Class elementClass;
    protected final boolean reference;

    public FieldDescriptor(Method getter, Method setter, Class collectionClass, Class elementClass) {
        this.getter = getter;
        this.setter = setter;
        this.collectionClass = collectionClass;
        this.elementClass = elementClass;
        this.reference = elementClass.isAnnotationPresent(Entity.class)
                || elementClass.isAnnotationPresent(MappedSuperclass.class);
    }

    public FieldDescriptor(Method getter, Method setter, Class clazz) {
        this(getter, setter, null, clazz);
    }

    public Method getGetter() {
        return getter;
    }

    public Method getSetter() {
        return setter;
    }

    public boolean isCollection() {
        return collectionClass != null;
    }

    public boolean isReference() {
        return reference;
    }

    public Class getCollectionClass() {
        return collectionClass;
    }

    public Class getElementClass() {
        return elementClass;
    }
}
