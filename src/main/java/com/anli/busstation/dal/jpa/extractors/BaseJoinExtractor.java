package com.anli.busstation.dal.jpa.extractors;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.eclipse.persistence.descriptors.ClassExtractor;
import org.eclipse.persistence.sessions.Record;
import org.eclipse.persistence.sessions.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseJoinExtractor extends ClassExtractor {

    private static final Logger LOG = LoggerFactory.getLogger(BaseJoinExtractor.class);

    protected Map<Class, String> rowNames;
    protected Map<Class, BaseJoinExtractor> childrenExtractors;

    public BaseJoinExtractor() {
        rowNames = null;
        childrenExtractors = null;
    }

    @Override
    public Class extractClassFromRow(Record databaseRow, Session session) {
        for (Map.Entry<Class, String> rowName : getRowNames().entrySet()) {
            if (databaseRow.get(rowName.getValue()) != null) {
                Class clazz = rowName.getKey();
                BaseJoinExtractor extractor = getExtractor(clazz);
                return extractor != null ? extractor.extractClassFromRow(databaseRow, session)
                        : clazz;
            }
        }
        throw new RuntimeException("Could not determine entity type for record :"
                + databaseRow.toString());
    }

    protected void addChildrenExtractor(Class clazz) {
        org.eclipse.persistence.annotations.ClassExtractor extractor =
                (org.eclipse.persistence.annotations.ClassExtractor) clazz
                .getAnnotation(org.eclipse.persistence.annotations.ClassExtractor.class);
        if (extractor == null) {
            return;
        }
        Class extractorClass = extractor.value();
        if (!BaseJoinExtractor.class.isAssignableFrom(extractorClass)) {
            return;
        }
        BaseJoinExtractor extractorInstance;
        try {
            extractorInstance = (BaseJoinExtractor) extractorClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            LOG.error("Could not instantiate extractor " + extractorClass, ex);
            return;
        }
        if (childrenExtractors == null) {
            childrenExtractors = new HashMap<>();
        }
        childrenExtractors.put(clazz, extractorInstance);
    }

    protected BaseJoinExtractor getExtractor(Class clazz) {
        if (childrenExtractors == null) {
            return null;
        }
        return childrenExtractors.get(clazz);
    }

    protected Map<Class, String> getRowNames() {
        if (rowNames == null) {
            rowNames = buildRowNames();
        }
        return rowNames;
    }

    protected Map<Class, String> buildRowNames() {
        Class[] classes = getClasses();
        Map<Class, String> names = new HashMap<>();
        for (Class clazz : classes) {
            Table table = (Table) clazz.getAnnotation(Table.class);
            PrimaryKeyJoinColumn joinColumn =
                    (PrimaryKeyJoinColumn) clazz.getAnnotation(PrimaryKeyJoinColumn.class);
            String rowName = table.name() + "." + joinColumn.name();
            addChildrenExtractor(clazz);
            names.put(clazz, rowName);
        }
        return names;
    }

    protected abstract Class[] getClasses();
}
