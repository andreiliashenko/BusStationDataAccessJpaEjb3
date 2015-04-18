package com.anli.busstation.dal.jpa.queries;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class QueryHolder {

    protected final JpqlQueryBuilder builder;
    protected final Map<String, EntityQueryHolder> holders;

    @Inject
    public QueryHolder(JpqlQueryBuilder builder) {
        this.builder = builder;
        this.holders = new HashMap<>();
    }

    public EntityQueryHolder getHolder(String entityName) {
        EntityQueryHolder holder = holders.get(entityName);
        if (holder == null) {
            holder = new EntityQueryHolder(builder, entityName);
            holders.put(entityName, holder);
        }
        return holder;
    }
}
