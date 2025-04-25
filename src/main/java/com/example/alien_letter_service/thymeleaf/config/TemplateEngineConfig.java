package com.example.alien_letter_service.thymeleaf.config;

import com.example.alien_letter_service.thymeleaf.dialect.AlienDialect;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * Конфигурационный класс для настройки XML обработки Thymeleaf шаблона.
 *
 * @author Daniil Molchanov
 * @version 1.0
 *
 * @see XmlMapper
 * @see SpringTemplateEngine
 * @see ClassLoaderTemplateResolver
 */
@Configuration
public class TemplateEngineConfig {

    /**
     * Создает и настраивает движок шаблонов Thymeleaf.
     *
     * @param classLoaderTemplateResolver резолвер шаблонов
     * @return Настроенный SpringTemplateEngine со следующими параметрами:
     * <ul>
     *   <li>Включен компилятор Spring EL</li>
     *   <li>Установлен переданный резолвер шаблонов</li>
     *   <li>Включено отображение скрытых маркеров перед чекбоксами</li>
     * </ul>
     */
    @Bean
    public SpringTemplateEngine templateEngine(ClassLoaderTemplateResolver classLoaderTemplateResolver, AlienDialect themeDialect) {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setEnableSpringELCompiler(true);
        springTemplateEngine.addDialect(themeDialect);
        springTemplateEngine.setRenderHiddenMarkersBeforeCheckboxes(true);
        springTemplateEngine.setTemplateResolver(classLoaderTemplateResolver);
        return springTemplateEngine;
    }

    /**
     * Создает кастомный диалект для обработки специальных тегов и атрибутов.
     * @return оъьект AlienDialect
     */
    @Bean
    public AlienDialect themeDialect() {
        return new AlienDialect();
    }

    /**
     * Создает и настраивает резолвер шаблонов.
     *
     * @return Настроенный ClassLoaderTemplateResolver со следующими параметрами:
     */
    @Bean
    public ClassLoaderTemplateResolver templateResolver() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".xml");
        resolver.setTemplateMode(TemplateMode.XML);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }
}
