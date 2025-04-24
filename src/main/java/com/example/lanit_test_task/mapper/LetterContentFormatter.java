package com.example.lanit_test_task.mapper;

import com.example.lanit_test_task.model.AlienLetter;
import com.example.lanit_test_task.entity.Author;
import com.example.lanit_test_task.entity.Title;
import com.example.lanit_test_task.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
            пожалуйста, не стесняйтесь спрашивать. С уважением, Земляне!"
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
     * Форматирует номер телефона (удаляет скобки и дефисы)
     *
     * @param phone исходный номер телефона
     * @return отформатированный номер
     */
    public String getFormattedPhoneNumber(String phone) {
        return phone.replaceAll("[()-]", "").trim();
    }

    /**
     * Разбивает текст на параграфы и заменяет приветствие
     *
     * @param text исходный текст
     * @return список отформатированных параграфов
     */
    public List<String> toFormattedParagraphs(String text) {
        List<String> paragraphs = Arrays.stream(text.split("\n"))
                .filter(StringUtils::isNotEmpty)
                .map(this::replaceGreeting)
                .collect(Collectors.toCollection(ArrayList::new));

        paragraphs.add(FAREWELL_MESSAGE.trim());
        return paragraphs;
    }

    /**
     * Заменяет стандартное приветствие человека на пришельцев
     * @return приветствие распознанной расы
     */
    private String replaceGreeting(String line) {
        if (line.trim().startsWith("Здравствуйте")) {
            return LANGUAGE_GREETINGS.keySet().stream()
                    .filter(keyword -> StringUtils.containsIgnoreCase(line, keyword))
                    .findFirst()
                    .map(keyword -> line.replace("Здравствуйте", LANGUAGE_GREETINGS.get(keyword)))
                    .orElse(line);
        }
        return line;
    }
}
