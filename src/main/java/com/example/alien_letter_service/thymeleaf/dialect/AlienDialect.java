package com.example.alien_letter_service.thymeleaf.dialect;

import org.thymeleaf.dialect.IProcessorDialect;
import org.thymeleaf.processor.IProcessor;

import java.util.HashSet;
import java.util.Set;

/**
 * Кастомный диалект Thymeleaf для обработки специальных тегов и атрибутов.
 * <p>
 * Регистрирует процессоры для преобразования Thymeleaf-атрибутов в чистые XML-атрибуты
 * для определенных тегов. Поддерживает следующие преобразования:
 * <ul>
 *   <li>{@code <address th:description="...">} → {@code <address description="..."/>}</li>
 *   <li>{@code <theme th:text="...">} → {@code <theme text="..."/>}</li>
 * </ul>
 *
 * @author Daniil Molchanov
 * @version 1.0
 *
 * @see IProcessorDialect
 * @see IProcessor
 */
public class AlienDialect implements IProcessorDialect {

    /**
     * Возвращает префикс диалекта, используемый в атрибутах.
     *
     * @return строку с префиксом диалекта ("th")
     */
    @Override
    public String getPrefix() {
        return "th";
    }

    /**
     * Возвращает приоритет обработки диалекта.
     *
     * @return числовое значение приоритета (1000)
     */
    @Override
    public int getDialectProcessorPrecedence() {
        return 1000;
    }

    /**
     * Регистрирует и возвращает набор процессоров для данного диалекта.
     * <p>
     * Создает и настраивает процессоры для обработки конкретных тегов и атрибутов.
     *
     * @param dialectPrefix префикс диалекта
     * @return множество (Set) зарегистрированных процессоров
     */
    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        Set<IProcessor> processors = new HashSet<>();
        processors.add(new AlienAttributeProcessor("th", "address", "description"));
        processors.add(new AlienAttributeProcessor("th", "theme", "text"));
        return processors;
    }

    /**
     * Возвращает читаемое имя диалекта.
     *
     * @return строку с названием диалекта ("Theme Dialect")
     */
    @Override
    public String getName() {
        return "Theme Dialect";
    }
}
