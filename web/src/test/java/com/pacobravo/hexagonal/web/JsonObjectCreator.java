package com.pacobravo.hexagonal.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonObjectCreator {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public <T> T createObjectFromJson(String filePath, Class<T> clazz) throws IOException {
        File file = new File("src/test/resources/json/" + filePath);
        return objectMapper.readValue(file, clazz);
    }

    public <T> List<T> createListFromJson(String filePath, Class<T> clazz) throws IOException {
        File file = new File("src/test/resources/json/" + filePath);
        return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}
