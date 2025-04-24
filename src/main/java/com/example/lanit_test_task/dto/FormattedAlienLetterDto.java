package com.example.lanit_test_task.dto;

import com.example.lanit_test_task.entity.Author;
import com.example.lanit_test_task.entity.Title;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO для представления отформатированного письма пришельцам.
 * Содержит все необходимые данные для генерации XML-письма.
 *
 * <p>Структура данных:
 * <ul>
 *   <li>Заголовок письма с темой</li>
 *   <li>Основной текст, разбитый на параграфы</li>
 *   <li>Контактная информация отправителя</li>
 *   <li>Метаданные письма</li>
 * </ul>
 *
 * @author Daniil Molchanov
 * @version 1.0
 * @since 2024-07-20
 */
@Data
public class FormattedAlienLetterDto {

    private Title title;

    private String uid;

    private LocalDateTime createdAt;

    private List<Author> authors;

    private List<String> listOfParagraphs;

    private String address;

    private String phone;
}