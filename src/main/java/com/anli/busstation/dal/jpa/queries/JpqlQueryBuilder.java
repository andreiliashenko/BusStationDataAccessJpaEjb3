package com.anli.busstation.dal.jpa.queries;

import javax.inject.Singleton;

@Singleton
public class JpqlQueryBuilder {

    protected static final String ID = "id";

    protected void appendSelectAndFrom(StringBuilder clause, String entityName,
            String entityAlias, boolean full) {
        clause.append("select ").append(entityAlias);
        if (!full) {
            clause.append(".").append(ID);
        }
        clause.append(" from ").append(entityName).append(" ").append(entityAlias);
    }

    protected void appendBinaryOperatorCondition(StringBuilder clause, String entityAlias,
            String fieldName, String operator, int parameterNumber) {
        clause.append(entityAlias).append(".").append(fieldName).append(" ")
                .append(operator).append(" ?").append(parameterNumber);
    }

    protected void appendIsNullCondition(StringBuilder clause, String entityAlias,
            String fieldName) {
        clause.append(entityAlias).append(".").append(fieldName)
                .append(" is null");
    }

    protected void appendMemberOfCondition(StringBuilder clause, String entityAlias,
            String fieldName, int parameterNumber) {
        clause.append("?").append(parameterNumber).append(" member of ")
                .append(entityAlias).append(fieldName);
    }

    protected String buildSelectBySingleBinaryOperator(String entityName,
            String fieldName, String operator, boolean full) {
        StringBuilder query = new StringBuilder();
        String entityAlias = entityName.toLowerCase();
        appendSelectAndFrom(query, entityName, entityAlias, full);
        query.append(" where ");
        appendBinaryOperatorCondition(query, entityAlias, fieldName, operator, 1);
        return query.toString();
    }

    public String buildSelectAllQuery(String entityName, boolean full) {
        StringBuilder query = new StringBuilder();
        String entityAlias = entityName.toLowerCase();
        appendSelectAndFrom(query, entityName, entityAlias, full);
        return query.toString();
    }

    public String buildSelectByEqualsQuery(String entityName, String fieldName, boolean full) {
        return buildSelectBySingleBinaryOperator(entityName, fieldName, "=", full);
    }

    public String buildSelectByNullQuery(String entityName, String fieldName, boolean full) {
        StringBuilder query = new StringBuilder();
        String entityAlias = entityName.toLowerCase();
        appendSelectAndFrom(query, entityName, entityAlias, full);
        query.append(" where ");
        appendIsNullCondition(query, entityAlias, fieldName);
        return query.toString();
    }

    public String buildSelectByOpenRangeTemplate(String entityName, String fieldName, boolean full) {
        return buildSelectBySingleBinaryOperator(entityName, fieldName, "%s", full);
    }

    public String buildSelectByAnyQuery(String entityName, String fieldName, boolean full) {
        return buildSelectBySingleBinaryOperator(entityName, fieldName, "IN", full);
    }

    public String buildSelectByRegexpQuery(String entityName, String fieldName, boolean full) {
        return buildSelectBySingleBinaryOperator(entityName, fieldName, "REGEXP", full);
    }

    public String buildSelectByContainsQuery(String entityName, String fieldName, boolean full) {
        StringBuilder query = new StringBuilder();
        String entityAlias = entityName.toLowerCase();
        appendSelectAndFrom(query, entityName, entityAlias, full);
        query.append(" where ");
        appendMemberOfCondition(query, entityAlias, fieldName, 1);
        return query.toString();
    }

    public String buildSelectByClosedRangeTemplate(String entityName, String fieldName, boolean full) {
        StringBuilder query = new StringBuilder();
        String entityAlias = entityName.toLowerCase();
        appendSelectAndFrom(query, entityName, entityAlias, full);
        query.append(" where ");
        appendBinaryOperatorCondition(query, entityAlias, fieldName, "%s", 1);
        query.append(" and ");
        appendBinaryOperatorCondition(query, entityAlias, fieldName, "%s", 2);
        return query.toString();
    }

    public String formatOpenRangeTemplate(String template, boolean left, boolean strict) {
        return String.format(template, left ? buildGreaterOperator(strict)
                : buildLessOperator(strict));
    }

    public String formatClosedRangeTemplate(String template, boolean leftStrict, boolean rightStrict) {
        return String.format(template, buildGreaterOperator(leftStrict),
                buildLessOperator(rightStrict));
    }

    protected String buildGreaterOperator(boolean strict) {
        return ">" + (strict ? "" : "=");
    }

    protected String buildLessOperator(boolean strict) {
        return "<" + (strict ? "" : "=");
    }
}
