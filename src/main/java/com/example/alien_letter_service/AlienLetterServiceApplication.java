package com.example.alien_letter_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс Spring Boot приложения.
 *
 * @author Daniil Molchanov
 * @version 1.0
 */
@SpringBootApplication
public class AlienLetterServiceApplication {

    /**
     * Основной main метод для запуска приложения.
     * @param args - массив аргументов
     */
    public static void main(String[] args) {
        SpringApplication.run(AlienLetterServiceApplication.class, args);
    }
}