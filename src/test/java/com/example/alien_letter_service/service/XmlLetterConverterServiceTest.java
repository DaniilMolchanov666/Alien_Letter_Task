package com.example.alien_letter_service.service;

import com.example.alien_letter_service.dto.FormattedAlienLetterDto;
import com.example.alien_letter_service.mapper.AlienLetterMapper;
import com.example.alien_letter_service.model.AlienLetter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование сервиса конвертации XML писем")
class XmlLetterConverterServiceTest {

    @Mock
    private AlienLetterMapper alienLetterMapper;

    @Mock
    private XmlMapper xmlMapper;

    @InjectMocks
    private XmlLetterConverterService letterConverterService;

    private final String testFilePath = "src/test/resources/alien_letter.xml";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(letterConverterService, "filePath", testFilePath);
    }

    @Test
    @DisplayName("Успешная генерация письма - когда файл существует")
    void generateFormattedLetterTest() throws IOException {
        // Arrange
        AlienLetter alienLetter = new AlienLetter();
        FormattedAlienLetterDto expectedDto = new FormattedAlienLetterDto();

        when(xmlMapper.readValue(any(File.class), eq(AlienLetter.class))).thenReturn(alienLetter);
        when(alienLetterMapper.toAlienLetterDto(alienLetter)).thenReturn(expectedDto);

        // Act
        FormattedAlienLetterDto result = letterConverterService.getFormattedAlienLetterDto();

        // Assert
        assertNotNull(result);
        assertEquals(expectedDto, result);
        verify(xmlMapper).readValue(any(File.class), eq(AlienLetter.class));
        verify(alienLetterMapper).toAlienLetterDto(alienLetter);
    }

    @Test
    @DisplayName("Ошибка генерации письма - когда чтение файла не удалось")
    void letterReadFailTest() throws IOException {
        // Arrange
        when(xmlMapper.readValue(any(File.class), eq(AlienLetter.class)))
                .thenThrow(new IOException("File read error"));
        // Act & Assert
        assertThrows(RuntimeException.class, () -> letterConverterService.getFormattedAlienLetterDto());
    }

    @Test
    @DisplayName("Ошибка генерации письма - когда файл не найден")
    void letterNotFoundFileTest() {
        // Arrange
        ReflectionTestUtils.setField(letterConverterService, "filePath", "");

        // Act & Assert
        assertThrows(NullPointerException.class, () -> letterConverterService.getFormattedAlienLetterDto());

        verifyNoInteractions(xmlMapper, alienLetterMapper);
    }

    @Test
    @DisplayName("Ошибка генерации письма - когда путь к файлу null")
    void nullPathToLetterFileTest() {
        // Arrange
        ReflectionTestUtils.setField(letterConverterService, "filePath", null);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> letterConverterService.getFormattedAlienLetterDto());

        verifyNoInteractions(xmlMapper, alienLetterMapper);
    }
}