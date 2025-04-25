package com.example.alien_letter_service.controller;

import com.example.alien_letter_service.dto.FormattedAlienLetterDto;
import com.example.alien_letter_service.entity.Title;
import com.example.alien_letter_service.service.XmlLetterConverterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование XmlController")
class XmlControllerTest {

    @Mock
    private XmlLetterConverterService letterConverterService;

    @Mock
    private Model model;

    @InjectMocks
    private XmlController xmlController;

    @Test
    @DisplayName("Успешная генерация письма - должен вернуть имя шаблона и добавить атрибуты в модель")
    void getLetterTest() {
        // Arrange
        FormattedAlienLetterDto expectedDto = FormattedAlienLetterDto.builder()
                .address("Address")
                .uid("Uid")
                .title(new Title())
                .authors(List.of())
                .createdAt("now")
                .listOfParagraphs(List.of())
                .phone("123456789")
                .build();

        when(letterConverterService.getFormattedAlienLetterDto()).thenReturn(expectedDto);

        // Act
        String result = xmlController.generateLetter(model);

        // Assert
        assertEquals("formatted_letter_template.xml", result);
        verify(model).addAttribute("data", expectedDto);
        verify(letterConverterService).getFormattedAlienLetterDto();
    }

    @Test
    @DisplayName("Ошибка при генерации письма - должен пробросить исключение")
    void getLetterFailTest() {
        // Arrange
        when(letterConverterService.getFormattedAlienLetterDto())
                .thenThrow(new RuntimeException());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> xmlController.generateLetter(model));
        verify(letterConverterService).getFormattedAlienLetterDto();
        verifyNoInteractions(model);
    }
}