package com.faisalyousaf777.notes.utils;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Converter {
    public static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @TypeConverter
    public static String fromLocalDateTime(final LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.format(formatter);
    }

    @TypeConverter
    public static LocalDateTime toLocalDateTime(final String dateTimeString) {
        return dateTimeString == null ? null : LocalDateTime.parse(dateTimeString, formatter);
    }
}
