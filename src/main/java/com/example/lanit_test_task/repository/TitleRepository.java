package com.example.lanit_test_task.repository;

import com.example.lanit_test_task.entity.Title;
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