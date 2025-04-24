package com.example.lanit_test_task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@NoArgsConstructor
public class Position {

    @Id
    private String id;

    @Column(name = "position_name", nullable = false)
    private String positionName;
}