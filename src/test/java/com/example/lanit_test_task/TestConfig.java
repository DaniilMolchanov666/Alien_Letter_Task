package com.example.lanit_test_task;

import com.example.lanit_test_task.mapper.AlienLetterMapper;
import com.example.lanit_test_task.mapper.AlienLetterMapperImpl;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public XmlMapper xmlMapper() {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
        xmlMapper.disable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
        return xmlMapper;
    }

    @Bean
    public AlienLetterMapper alienLetterMapper() {
        return new AlienLetterMapperImpl(); // Генерируется MapStruct
    }
}
