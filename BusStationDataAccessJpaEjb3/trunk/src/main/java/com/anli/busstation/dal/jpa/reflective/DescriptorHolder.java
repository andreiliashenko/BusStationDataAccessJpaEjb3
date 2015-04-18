package com.anli.busstation.dal.jpa.reflective;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DescriptorHolder {

    protected final Map<Class, EntityDescriptor> relationHolders;
    protected final EntityDescriptorBuilder relationBuilder;

    @Inject
    public DescriptorHolder(EntityDescriptorBuilder relationBuilder) {
        this.relationBuilder = relationBuilder;
        this.relationHolders = new HashMap<>();
    }

    public EntityDescriptor getHolder(Class entityClass) {
        EntityDescriptor holder = relationHolders.get(entityClass);
        if (holder == null) {
            holder = relationBuilder.buildRelation(entityClass);
            relationHolders.put(entityClass, holder);
        }
        return holder;
    }
}
