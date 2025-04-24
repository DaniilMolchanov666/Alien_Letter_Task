package com.example.lanit_test_task.controller;

import com.example.lanit_test_task.dto.FormattedAlienLetterDto;
import com.example.lanit_test_task.service.XmlLetterConverterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Контроллер для генерации отформатированных XML-писем инопланетянам.
 *
 * @author Daniil Molchanov
 * @version 1.0
 * @since 2024-07-20
 *
 * @see XmlLetterConverterService
 * @see FormattedAlienLetterDto
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class XmlController {

    /**
     * Сервис для преобразования данных в формат письма
     */
    private final XmlLetterConverterService letterConverterService;

    /**
     * Обрабатывает POST-запрос для генерации XML-письма.
     *
     * @param model Модель Spring для передачи данных в представление
     * @return Шаблон Thymeleaf
     */
    @PostMapping(value = "/getLetter", produces = MediaType.APPLICATION_XML_VALUE)
    public String generateLetter(Model model) {
        FormattedAlienLetterDto alienLetterDto = letterConverterService.getFormattedAlienLetterDto();
        log.info("Получено отформатированное письмо для пришельцев! {}", alienLetterDto);
        model.addAttribute("data", alienLetterDto);
        return "formatted_letter_template.xml";
    }
}
