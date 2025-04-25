package com.example.alien_letter_service.repository;

import com.example.alien_letter_service.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Репозиторий для работы с авторами писем ({@link Author}).
 *
 * @author Daniil Molchanov
 * @version 1.0
 * @see Author
 */
public interface AuthorRepository extends JpaRepository<Author, String> {

    /**
     * Находит название должности автора по его ID.
     *
     * @param authorId идентификатор автора
     * @return название должности
     *
     * @Query Использует JPQL для запроса к связанной сущности {@link com.example.alien_letter_service.entity.Position}
     */
    @Query("SELECT a.position.positionName FROM Author a WHERE a.id = :authorId")
    String findPositionNameByAuthorId(@Param("authorId") String authorId);
}