package com.example.alien_letter_service.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Конфигурационный класс для настройки XML маппера.
 *
 * @author Daniil Molchanov
 * @version 1.0
 *
 * @see XmlMapper
 */
@Configuration
@EnableWebMvc
public class XmlMapperConfig {

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
}
