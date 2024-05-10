package com.clinic.demo.configs;

import org.springframework.core.convert.converter.Converter;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringToTimeConverter implements Converter<String, Time> {

    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    @Override
    public Time convert(String source) {
        try {
            java.util.Date utilDate = timeFormat.parse(source);
            return new Time(utilDate.getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid time format. Please use HH:mm format.", e);
        }
    }
}