package com.example.lanit_test_task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность, представляющая автора письма.
 * Содержит информацию об имени, фамилии, отчестве и должности автора.
 *
 * <p>Связана с сущностью {@link Position} отношением Many-to-One.
 *
 * @author Daniil Molchanov
 * @version 1.0
 */
@Data
@Entity
@Table(name = "author")
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    private String id;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surName;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;
}
