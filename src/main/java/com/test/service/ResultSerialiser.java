package com.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.Result;

import java.io.IOException;

public class ResultSerialiser {

    private static final String ERROR_MESSAGE = "Problem during result generation";

    public String serialiseResult(final Result result) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(result);
        } catch (final IOException e) {
            throw new RuntimeException(ERROR_MESSAGE);
        }
    }
}
