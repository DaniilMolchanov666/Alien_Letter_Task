package com.example.lanit_test_task.service;

import com.example.lanit_test_task.mapper.AlienLetterMapper;
import com.example.lanit_test_task.model.AlienLetter;
import com.example.lanit_test_task.dto.FormattedAlienLetterDto;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Сервис для конвертации XML-письма в форматированный DTO.
 *
 * @author Daniil Molchanov
 * @version 1.1
 * @since 2024-07-20
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
     * @throws NullPointerException если файл не найден
     */
    public FormattedAlienLetterDto getFormattedAlienLetterDto() {
        try {
            AlienLetter alienLetter = xmlMapper.readValue(new File(filePath), AlienLetter.class);
            FormattedAlienLetterDto dto = alienLetterMapper.toAlienLetterDto(alienLetter);

            log.info("Успешная конвертация письма. Содержание письма: {}", dto);

            return dto;

        } catch (IOException e) {
            throw new RuntimeException(String.format("Ошибка чтения файла с письмом: %s", e.getMessage()));
        } catch (NullPointerException e) {
            throw new NullPointerException(String.format("Файл не найден или недоступен:%s", filePath));
        }
    }
}
