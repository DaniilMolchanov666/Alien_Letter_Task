package com.example.alien_letter_service.service;

import com.example.alien_letter_service.mapper.AlienLetterMapper;
import com.example.alien_letter_service.model.AlienLetter;
import com.example.alien_letter_service.dto.FormattedAlienLetterDto;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Сервис для конвертации XML-письма в форматированный DTO.
 *
 * @author Daniil Molchanov
 * @version 1.1
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class XmlLetterConverterService {

    private final AlienLetterMapper alienLetterMapper;

    private final XmlMapper xmlMapper;

    /**
    * Путь к не отформатированному файлу письма, указанному в application файле
     */
    @Value("${letter_file.path}")
    private String filePath;

    /**
     * Конвертирует XML-письмо в форматированный DTO.
     *
     * @return форматированный DTO письма
     * @throws RuntimeException если произошла ошибка при чтении или конвертации файла
     * @throws NullPointerException если файл не найден или указан неправильный путь
     */
    public FormattedAlienLetterDto getFormattedAlienLetterDto() {
        if (filePath == null || filePath.isBlank()) {
            throw new NullPointerException("Путь к файлу не указан");
        }

        File file = new File(filePath);

        if (!file.exists()) {
            throw new NullPointerException(String.format("Файл не найден: %s", filePath));
        }

        try {
            AlienLetter alienLetter = xmlMapper.readValue(file, AlienLetter.class);
            FormattedAlienLetterDto dto = alienLetterMapper.toAlienLetterDto(alienLetter);

            log.info("Успешная конвертация письма. Содержание письма: {}", dto);

            return dto;

        } catch (IOException e) {
            throw new RuntimeException(String.format("Ошибка чтения файла с письмом: %s", e.getMessage()));
        }
    }
}
