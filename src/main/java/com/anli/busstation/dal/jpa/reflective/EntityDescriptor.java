package com.anli.busstation.dal.jpa.reflective;

import java.util.Collection;

public class EntityDescriptor {

    protected final String entityName;
    protected final Collection<FieldDescriptor> fields;
    protected final FieldDescriptor idField;

    public EntityDescriptor(String entityName, Collection<FieldDescriptor> fields,
            FieldDescriptor idField) {
        this.entityName = entityName;
        this.fields = fields;
        this.idField = idField;
    }

    public String getEntityName() {
        return entityName;
    }

    public Collection<FieldDescriptor> getFields() {
        return fields;
    }

    public FieldDescriptor getIdField() {
        return idField;
    }
}
