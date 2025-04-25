package com.example.alien_letter_service.mapper;

import com.example.alien_letter_service.entity.Author;
import com.example.alien_letter_service.entity.Title;
import com.example.alien_letter_service.model.AlienLetter;
import com.example.alien_letter_service.service.AuthorService;
import com.example.alien_letter_service.util.DateTimeConverter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервис для форматирования контента письма.
 * Обрабатывает приветствия, номера телефонов и разбивает текст на параграфы.
 *
 * @author Daniil Molchanov
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class LetterContentFormatter {
    private final AuthorService authorService;

    private static final Map<String, String> LANGUAGE_GREETINGS = Map.of(
            "татуин", "Dif-tor heh smusma",
            "марс", "こんにちは",
            "воган", "안녕하세요"
    );

    private static final String FAREWELL_MESSAGE = """
            Надеюсь, это поможет Вам. Если у Вас есть какие-либо дополнительные вопросы,
            пожалуйста, не стесняйтесь спрашивать. С уважением, Земляне!
            """;

    /**
     * Получает заголовок по коду расы
     *
     * @param code код расы
     * @return объект {@link Title}
     */
    public Title getTitleByCodeRace(String code) {
        return authorService.getTitleByCode(code);
    }

    /**
     * Преобразует список AuthorId в список Author
     *
     * @param authorIds список идентификаторов авторов
     * @return список {@link Author}
     */
    public List<Author> getAuthorsByAuthorIds(List<AlienLetter.AuthorId> authorIds) {
        return authorIds.stream()
                .map(authorId -> authorService.getAuthorById(authorId.getId().getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Форматирует номер телефона (удаляет все символы кроме цифр)
     *
     * @param phone исходный номер телефона
     * @return отформатированный номер
     */
    @Named("formatPhone")
    public String getFormattedPhoneNumber(String phone) {
        return phone.replaceAll("[^0-9]", "").trim();
    }

    /**
     * Приводит дату к формату yyyy-MM-dd_HH:mm и увеличивает год, месяц и день на 1
     *
     * @param date - дата из источника
     * @return - отформатированная дата
     */
    @Named("formatAndIncrementDate")
    public String getIncrementedFormattedDate(String date) {
        return DateTimeConverter.convertAndAddTime(date);
    }

    /**
     * Разбивает текст на параграфы и заменяет приветствие
     *
     * @param text исходный текст
     * @return список отформатированных параграфов
     */
    @Named("formatParagraphs")
    public List<String> toFormattedParagraphs(String text) {
        String textWithFarewellMessage = text + FAREWELL_MESSAGE.replace("\n", "");
        return Arrays.stream(textWithFarewellMessage.trim().split("\n"))
                .filter(StringUtils::isNotEmpty)
                .map(this::replaceGreeting)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Заменяет стандартное приветствие человека на пришельцев
     * @return приветствие распознанной расы
     */
    private String replaceGreeting(String line) {
        if (line.startsWith("Здравствуйте")) {
            return LANGUAGE_GREETINGS.keySet().stream()
                    .filter(keyword -> StringUtils.containsIgnoreCase(line, keyword))
                    .findFirst()
                    .map(keyword -> line.replace("Здравствуйте", LANGUAGE_GREETINGS.get(keyword)))
                    .orElse(line);
        }
        return line;
    }
}
