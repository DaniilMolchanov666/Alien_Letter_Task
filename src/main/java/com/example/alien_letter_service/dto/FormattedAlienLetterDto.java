package com.example.alien_letter_service.dto;

import com.example.alien_letter_service.entity.Author;
import com.example.alien_letter_service.entity.Title;
import lombok.Builder;

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
 */
@Builder
public record FormattedAlienLetterDto(
        Title title,
        String uid,
        String createdAt,
        List<Author> authors,
        List<String> listOfParagraphs,
        String address,
        String phone
) { }