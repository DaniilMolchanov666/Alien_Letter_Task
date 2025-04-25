package com.example.alien_letter_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность, представляющая должность автора.
 *
 * @author Daniil Molchanov
 * @version 1.0
 */
@Data
@Entity
@Table(name = "position")
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    @Id
    private String id;

    @Column(name = "position_name", nullable = false)
    private String positionName;
}