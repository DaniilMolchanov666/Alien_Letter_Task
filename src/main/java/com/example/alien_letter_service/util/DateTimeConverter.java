package com.example.alien_letter_service.util;

import lombok.experimental.UtilityClass;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Утилитарный класс для конвертации и изменения времени.
 *
 * @author Daniil Molchanov
 * @version 1.0
 */
@UtilityClass
public class DateTimeConverter {

    /**
     * Формат даты из исходного письма.
     */
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

    /**
     * Приведенный формат для отредактированного письма.
     */
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");

    /**
     * Преобразует дату и увеличивает на 1 год, 1 месяц и 1 день
     * @param inputDate строка с датой в формате yyyy-MM-dd'T'HH:mm:ssXXX
     * @return строка с датой в формате yyyy-MM-dd_HH:mm (+1 год, +1 месяц, +1 день)
     * @throws DateTimeParseException если входная строка имеет неверный формат
     */
    public static String convertAndAddTime(String inputDate) throws DateTimeParseException {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(inputDate, INPUT_FORMATTER);

        ZonedDateTime modifiedDateTime = zonedDateTime.plusYears(1).plusMonths(1).plusDays(1);

        return modifiedDateTime.format(OUTPUT_FORMATTER);
    }
}
