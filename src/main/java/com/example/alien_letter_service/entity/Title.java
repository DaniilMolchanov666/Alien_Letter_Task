package com.example.alien_letter_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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
