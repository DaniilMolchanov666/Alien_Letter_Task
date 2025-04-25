package com.example.alien_letter_service.service;

import com.example.alien_letter_service.entity.Author;
import com.example.alien_letter_service.entity.Title;
import com.example.alien_letter_service.repository.AuthorRepository;
import com.example.alien_letter_service.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * Сервис для работы с авторами и заголовками.
 * Обеспечивает доступ к репозиториям {@link AuthorRepository} и {@link TitleRepository}.
 *
 * @author Daniil Molchanov
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final TitleRepository titleRepository;

    /**
     * Находит автора по ID
     *
     * @param id идентификатор автора
     * @return найденный {@link Author}
     * @throws NoSuchElementException если автор не найден
     */
    public Author getAuthorById(String id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Author not found"));
    }

    /**
     * Находит заголовок по коду
     *
     * @param code код заголовка
     * @return найденный {@link Title}
     * @throws NoSuchElementException если заголовок не найден
     */
    public Title getTitleByCode(String code) {
        return titleRepository.findById(code)
                .orElseThrow(() -> new NoSuchElementException("Title not found"));
    }
}
