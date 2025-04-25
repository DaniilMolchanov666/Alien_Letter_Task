package com.example.alien_letter_service.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

/**
 * Класс, представляющий письмо для инопланетных рас в XML-формате.
 *
 * <p>Содержит структуру письма, включая:
 * <ul>
 *   <li>Код расы получателя</li>
 *   <li>Дату создания письма</li>
 *   <li>Уникальный идентификатор</li>
 *   <li>Список авторов письма</li>
 *   <li>Основное содержимое документа (текст, адрес, телефон)</li>
 * </ul>
 *
 * <p>Аннотации Jackson XML обеспечивают корректную сериализацию/десериализацию
 * в XML-формат с сохранением структуры и русскоязычных названий элементов.
 *
 * @author DaniilMolchanov
 * @version 1.0
 * @see com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
 * @see com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
 */
@JacksonXmlRootElement(localName = "Письмо_инопланетянам")
@Data
public class AlienLetter {

    @JacksonXmlProperty(localName = "код_расы")
    private RaceCode raceCode;

    @JacksonXmlProperty(localName = "created")
    private Created created;

    @JacksonXmlProperty(localName = "uid")
    private Uid uid;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "author")
    private List<AuthorId> authors;

    @JacksonXmlProperty(localName = "document")
    private Document document;

    @JacksonXmlRootElement(localName = "код_расы")
    @Data
    public static class RaceCode {
        @JacksonXmlProperty(localName = "value")
        private String value;
    }

    @JacksonXmlRootElement(localName = "created")
    @Data
    public static class Created {
        @JacksonXmlProperty(isAttribute = true, localName = "date_time")
        private String dateTime;
    }

    @JacksonXmlRootElement(localName = "uid")
    @Data
    public static class Uid {
        @JacksonXmlProperty(localName = "code")
        private Code code;
    }

    @JacksonXmlRootElement(localName = "code")
    @Data
    public static class Code {
        @JacksonXmlProperty(localName = "value")
        private String value;
    }

    @JacksonXmlRootElement(localName = "author")
    @Data
    public static class AuthorId {
        @JacksonXmlProperty(localName = "id")
        private Id id;
    }

    @JacksonXmlRootElement(localName = "id")
    @Data
    public static class Id {
        @JacksonXmlProperty(localName = "value")
        private String value;
    }

    @JacksonXmlRootElement(localName = "document")
    @Data
    public static class Document {
        @JacksonXmlProperty(localName = "text")
        private String text;

        @JacksonXmlProperty(localName = "address")
        private Address address;

        @JacksonXmlProperty(localName = "tel")
        private Telephone telephone;
    }

    @JacksonXmlRootElement(localName = "address")
    @Data
    public static class Address {
        @JacksonXmlProperty(localName = "value")
        private String value;
    }

    @JacksonXmlRootElement(localName = "tel")
    @Data
    public static class Telephone {
        @JacksonXmlProperty(localName = "value")
        private String value;
    }
}

