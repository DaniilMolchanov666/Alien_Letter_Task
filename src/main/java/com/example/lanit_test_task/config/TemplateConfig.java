package com.example.lanit_test_task.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * Конфигурационный класс для настройки XML-обработки в Spring MVC.
 *
 *
 * @author Daniil Molchanov
 * @version 1.0
 * @since 2024-07-20
 *
 * @see XmlMapper
 * @see SpringTemplateEngine
 * @see ClassLoaderTemplateResolver
 */
@Configuration
@EnableWebMvc
public class TemplateConfig {

    /**
     * Создает и настраивает XmlMapper для работы с XML.
     *
     * @return Настроенный экземпляр XmlMapper со следующими параметрами:
     * <ul>
     *   <li>Включено добавление XML-декларации</li>
     *   <li>Отключено форматирование вывода</li>
     *   <li>Включена проверка на пустые бины</li>
     * </ul>
     */
    @Bean
    public XmlMapper xmlMapper() {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
        xmlMapper.disable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
        return xmlMapper;
    }

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
    public SpringTemplateEngine templateEngine(ClassLoaderTemplateResolver classLoaderTemplateResolver) {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(classLoaderTemplateResolver);
        return springTemplateEngine;
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
