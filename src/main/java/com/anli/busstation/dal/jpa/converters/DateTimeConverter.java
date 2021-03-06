package com.anli.busstation.dal.jpa.converters;

import java.sql.Timestamp;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.joda.time.DateTime;

@Converter
public class DateTimeConverter implements AttributeConverter<DateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(DateTime x) {
        return x != null ? new Timestamp(x.getMillis()) : null;
    }

    @Override
    public DateTime convertToEntityAttribute(Timestamp y) {
        return y != null ? new DateTime(y.getTime()) : null;
    }
}
