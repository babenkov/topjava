package ru.javawebinar.topjava.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Home7 on 07.10.2015.
 */
@Converter(autoApply = true)
public class LocalDateTimePersistenceConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
        return Timestamp.valueOf(entityValue);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp databaseValue) {
        return databaseValue.toLocalDateTime();
    }
}
