package com.henry.employeestatemachine.util;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
@Convert
@Primary
@Component
public class StringDataConverter implements AttributeConverter<List<String>, String> {

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return null;
        return Arrays.stream(dbData.split(",")).collect(Collectors.toList());
    }

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        StringBuilder builder = new StringBuilder();
        if (attribute == null || attribute.isEmpty()) return null;
        Iterator<String> statesIterator = attribute.iterator();
        while (statesIterator.hasNext()) {
            builder.append(statesIterator.next());
            if (statesIterator.hasNext()) builder.append(",");
        }
        return builder.toString();
    }
}