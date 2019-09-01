package com.test.service;

import com.test.model.ProductEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class PricingService {

    private static final int DECIMAL_PLACES = 2;

    public double calculateValue(Map<ProductEnum, Integer> shoppingCart) {
        validate(shoppingCart);
        return round(shoppingCart.entrySet().stream().mapToDouble(entry -> entry.getValue() * entry.getKey().getPrice()).sum());
    }

    private void validate(Map<ProductEnum, Integer> shoppingCart) {
        if (shoppingCart == null) {
            throw new IllegalArgumentException("Missing shopping cart!");
        }
    }

    private double round(double totalValue) {
        return BigDecimal.valueOf(totalValue).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP).doubleValue();
    }
}
