package com.example.sentimentanalyzer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

    public static <T> T safeParseJSON(ObjectMapper objectMapper, String json, Class<T> targetType) {
        try {
            return objectMapper.readValue(json, targetType);
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Encountered error while parsing JSON - %s", ex.getMessage()), ex);
        }
    }

}
