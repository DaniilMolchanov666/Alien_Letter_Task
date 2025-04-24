package com.example.lanit_test_task.model;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Письмо_инопланетянам")
@Data
public class AlienLetter {

    @JacksonXmlProperty(localName = "код_расы")
    private Code raceCode;

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
    public static class Code {
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

