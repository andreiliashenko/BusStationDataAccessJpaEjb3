package com.anli.busstation.dal.jpa.queries;

public class FieldQueryHolder {

    protected final JpqlQueryBuilder builder;
    protected final String entityName;
    protected final String fieldName;

    protected String selectByEquals;
    protected String collectByEquals;
    protected String selectByNull;
    protected String collectByNull;
    protected String selectByAny;
    protected String collectByAny;
    protected String selectByContains;
    protected String collectByContains;
    protected String selectByRegexp;
    protected String collectByRegexp;
    protected String selectByOpenRangeTemplate;
    protected String collectByOpenRangeTemplate;
    protected String selectByClosedRangeTemplate;
    protected String collectByClosedRangeTemplate;

    public FieldQueryHolder(JpqlQueryBuilder queryBuilder, String entityName, String fieldName) {
        this.builder = queryBuilder;
        this.entityName = entityName;
        this.fieldName = fieldName;
    }

    public String getSelectByEquals() {
        if (selectByEquals == null) {
            selectByEquals = builder.buildSelectByEqualsQuery(entityName, fieldName, true);
        }
        return selectByEquals;
    }

    public String getCollectByEquals() {
        if (collectByEquals == null) {
            collectByEquals = builder.buildSelectByEqualsQuery(entityName, fieldName, false);
        }
        return collectByEquals;
    }

    public String getSelectByNull() {
        if (selectByNull == null) {
            selectByNull = builder.buildSelectByNullQuery(entityName, fieldName, true);
        }
        return selectByNull;
    }

    public String getCollectByNull() {
        if (collectByNull == null) {
            collectByNull = builder.buildSelectByNullQuery(entityName, fieldName, false);
        }
        return collectByNull;
    }

    public String getSelectByAny() {
        if (selectByAny == null) {
            selectByAny = builder.buildSelectByAnyQuery(entityName, fieldName, true);
        }
        return selectByAny;
    }

    public String getCollectByAny() {
        if (collectByAny == null) {
            collectByAny = builder.buildSelectByAnyQuery(entityName, fieldName, false);
        }
        return collectByAny;
    }

    public String getSelectByContains() {
        if (selectByContains == null) {
            selectByContains = builder.buildSelectByContainsQuery(entityName, fieldName, true);
        }
        return selectByContains;
    }

    public String getCollectByContains() {
        if (collectByContains == null) {
            collectByContains = builder.buildSelectByContainsQuery(entityName, fieldName, false);
        }
        return collectByContains;
    }

    public String getSelectByRegexp() {
        if (selectByRegexp == null) {
            selectByRegexp = builder.buildSelectByRegexpQuery(entityName, fieldName, true);
        }
        return selectByRegexp;
    }

    public String getCollectByRegexp() {
        if (collectByRegexp == null) {
            collectByRegexp = builder.buildSelectByRegexpQuery(entityName, fieldName, false);
        }
        return collectByRegexp;
    }

    public String getSelectByOpenRange(boolean left, boolean strict) {
        if (selectByOpenRangeTemplate == null) {
            selectByOpenRangeTemplate = builder.buildSelectByOpenRangeTemplate(entityName,
                    fieldName, true);
        }
        return builder.formatOpenRangeTemplate(selectByOpenRangeTemplate, left, strict);
    }

    public String getCollectByOpenRange(boolean left, boolean strict) {
        if (collectByOpenRangeTemplate == null) {
            collectByOpenRangeTemplate = builder.buildSelectByOpenRangeTemplate(entityName,
                    fieldName, false);
        }
        return builder.formatOpenRangeTemplate(collectByOpenRangeTemplate, left, strict);
    }

    public String getSelectByClosedRange(boolean leftStrict, boolean rightStrict) {
        if (selectByClosedRangeTemplate == null) {
            selectByClosedRangeTemplate = builder.buildSelectByClosedRangeTemplate(entityName,
                    fieldName, true);
        }
        return builder.formatClosedRangeTemplate(selectByClosedRangeTemplate, leftStrict, rightStrict);
    }

    public String getCollectByClosedRange(boolean leftStrict, boolean rightStrict) {
        if (collectByClosedRangeTemplate == null) {
            collectByClosedRangeTemplate = builder.buildSelectByClosedRangeTemplate(entityName,
                    fieldName, false);
        }
        return builder.formatClosedRangeTemplate(collectByClosedRangeTemplate, leftStrict, rightStrict);
    }
}
