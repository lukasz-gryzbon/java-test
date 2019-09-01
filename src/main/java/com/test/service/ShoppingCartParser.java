package com.test.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.ProductEnum;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartParser {
    private TypeReference<HashMap<ProductEnum, Integer>> typeRef = new TypeReference<HashMap<ProductEnum, Integer>>() {};

    public Map<ProductEnum, Integer> parse(String shoppingCartJson) throws IOException {

        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(shoppingCartJson.toUpperCase(), typeRef);
    }
}
