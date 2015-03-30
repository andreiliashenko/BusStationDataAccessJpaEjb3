package com.anli.busstation.dal.jpa.reflective;

import java.lang.reflect.Field;

public class FieldDescriptor {

    protected final Field field;
    protected final boolean collection;
    protected final Class elementClass;

    public FieldDescriptor(Field field, boolean isCollection, Class elementClass) {
        this.field = field;
        this.collection = isCollection;
        this.elementClass = elementClass;
    }

    public Field getField() {
        return field;
    }

    public boolean isCollection() {
        return collection;
    }

    public Class getElementClass() {
        return elementClass;
    }
}
