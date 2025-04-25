package com.example.alien_letter_service.repository;

import com.example.alien_letter_service.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с заголовками писем ({@link Title}).
 *
 * @author Daniil Molchanov
 * @version 1.0
 * @see Title
 */
public interface TitleRepository extends JpaRepository<Title, String> {
}