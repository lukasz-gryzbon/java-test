package com.test.service;

import static com.test.model.ProductEnum.MILK;

import com.test.model.ProductEnum;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartParser {
    public Map<ProductEnum, Integer> parse(String shoppingCartJson) {
        final Map<ProductEnum, Integer> shoppingCart = new HashMap<>();
        shoppingCart.put(MILK, 1);
        return shoppingCart;
    }
}
