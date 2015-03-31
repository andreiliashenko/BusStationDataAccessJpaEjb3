package com.anli.busstation.dal.jpa.extractors;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.eclipse.persistence.descriptors.ClassExtractor;
import org.eclipse.persistence.sessions.Record;
import org.eclipse.persistence.sessions.Session;

public abstract class BaseJoinExtractor extends ClassExtractor {

    protected Map<Class, String> rowNames;

    public BaseJoinExtractor() {
        rowNames = null;
    }

    @Override
    public Class extractClassFromRow(Record databaseRow, Session session) {
        for (Map.Entry<Class, String> rowName : getRowNames().entrySet()) {
            if (databaseRow.get(rowName.getValue()) != null) {
                return rowName.getKey();
            }
        }
        throw new RuntimeException("Could not determine entity type for record :"
                + databaseRow.toString());
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
            names.put(clazz, rowName);
        }
        return names;
    }

    protected abstract Class[] getClasses();
}
