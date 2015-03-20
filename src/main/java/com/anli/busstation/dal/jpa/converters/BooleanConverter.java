package com.anli.busstation.dal.jpa.converters;

import javax.persistence.AttributeConverter;

public class BooleanConverter implements AttributeConverter<Boolean, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Boolean x) {
        return Boolean.TRUE.equals(x) ? 1 : 0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer y) {
        return y != null && !y.equals(0);
    }
}
