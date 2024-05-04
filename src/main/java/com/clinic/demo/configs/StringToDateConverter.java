package com.clinic.demo.configs;

import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

    private final SimpleDateFormat dateFormat;

    public StringToDateConverter(String dateFormatPattern) {
        this.dateFormat = new SimpleDateFormat(dateFormatPattern);
        this.dateFormat.setLenient(false);
    }

    @Override
    public Date convert(String source) {
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use the format dd.MM.yyyy", e);
        }
    }
}

