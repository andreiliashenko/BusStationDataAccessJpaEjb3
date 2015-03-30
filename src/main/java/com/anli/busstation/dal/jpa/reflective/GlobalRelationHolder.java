package com.anli.busstation.dal.jpa.reflective;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GlobalRelationHolder {

    protected final Map<Class, EntityRelationHolder> relationHolders;
    protected final RelationBuilder relationBuilder;

    @Inject
    public GlobalRelationHolder(RelationBuilder relationBuilder) {
        this.relationBuilder = relationBuilder;
        this.relationHolders = new HashMap<>();
    }

    public EntityRelationHolder getHolder(Class entityClass) {
        EntityRelationHolder holder = relationHolders.get(entityClass);
        if (holder == null) {
            holder = relationBuilder.buildRelation(entityClass);
            relationHolders.put(entityClass, holder);
        }
        return holder;
    }
}
