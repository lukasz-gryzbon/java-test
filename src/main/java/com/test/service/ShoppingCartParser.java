package com.test.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.ProductEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartParser {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartParser.class);
    private final TypeReference<HashMap<ProductEnum, Integer>> typeRef = new TypeReference<HashMap<ProductEnum, Integer>>() {
    };

    public Map<ProductEnum, Integer> parse(final String shoppingCartJson) {
        logger.debug("Parsing:{}", shoppingCartJson);
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(shoppingCartJson.toUpperCase(), typeRef);
        } catch (final JsonMappingException e) {
            throw new IllegalArgumentException("Unknown item in te shopping cart");
        } catch (final JsonParseException e) {
            throw new IllegalArgumentException("Wrong JSON");
        } catch (final IOException e) {
            throw new RuntimeException("Problem while parsing JSON");
        }
    }
}
