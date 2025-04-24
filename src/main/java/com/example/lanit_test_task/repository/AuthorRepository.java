package com.example.lanit_test_task.repository;

import com.example.lanit_test_task.entity.Author;
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
     * @Query Использует JPQL для запроса к связанной сущности {@link com.example.lanit_test_task.entity.Position}
     */
    @Query("SELECT a.position.positionName FROM Author a WHERE a.id = :authorId")
    String findPositionNameByAuthorId(@Param("authorId") String authorId);
}