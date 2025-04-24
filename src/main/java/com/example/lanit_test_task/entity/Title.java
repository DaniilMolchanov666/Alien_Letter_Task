package com.example.lanit_test_task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность, представляющая заголовок письма.
 * Содержит код, описание и тему письма.
 *
 * @author Daniil Molchanov
 * @version 1.0
 */
@Data
@Entity
@Table(name = "title")
@NoArgsConstructor
public class Title {

    @Id
    @Column(name = "code")
    private String code;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String theme;
}
