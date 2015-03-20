package com.anli.busstation.dal.jpa.queries;

import java.util.HashMap;
import java.util.Map;

public class EntityQueryHolder {

    protected final JpqlQueryBuilder builder;
    protected final String entityName;

    protected final Map<String, FieldQueryHolder> fieldHolders;
    protected String selectAll;
    protected String collectKeysAll;

    public EntityQueryHolder(JpqlQueryBuilder builder, String entityName) {
        this.builder = builder;
        this.entityName = entityName;
        this.fieldHolders = new HashMap<>();
    }

    public String getSelectAll() {
        if (selectAll == null) {
            selectAll = builder.buildSelectAllQuery(entityName, true);
        }
        return selectAll;
    }

    public String getCollectKeysAll() {
        if (collectKeysAll == null) {
            collectKeysAll = builder.buildSelectAllQuery(entityName, false);
        }
        return collectKeysAll;
    }

    public FieldQueryHolder getFieldHolder(String fieldName) {
        FieldQueryHolder holder = fieldHolders.get(fieldName);
        if (holder == null) {
            holder = new FieldQueryHolder(builder, entityName, fieldName);
            fieldHolders.put(fieldName, holder);
        }
        return holder;
    }
}
