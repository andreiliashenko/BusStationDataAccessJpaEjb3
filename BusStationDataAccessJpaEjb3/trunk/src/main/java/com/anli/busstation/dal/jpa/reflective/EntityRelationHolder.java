package com.anli.busstation.dal.jpa.reflective;

import java.util.Collection;

public class EntityRelationHolder {

    protected final String entityName;
    protected final Collection<FieldDescriptor> fields;

    public EntityRelationHolder(String entityName, Collection<FieldDescriptor> fields) {
        this.entityName = entityName;
        this.fields = fields;
    }

    public String getEntityName() {
        return entityName;
    }

    public Collection<FieldDescriptor> getFields() {
        return fields;
    }
}
