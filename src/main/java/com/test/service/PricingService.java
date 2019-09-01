package com.test.service;

import com.test.model.ProductEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class PricingService {

    private static final int DECIMAL_PLACES = 2;

    public double calculateValue(Map<String, Integer> shoppingCart) {
        if (shoppingCart == null || shoppingCart.size() == 0) {
            return 0;
        }
        double totalValue = 0.0;

        for (String itemName : shoppingCart.keySet()) {
            final ProductEnum product = ProductEnum.valueOf(itemName.toUpperCase());
            totalValue += shoppingCart.get(itemName) * product.getPrice();
        }

        return round(totalValue);
    }

    private double round(double totalValue) {
        return BigDecimal.valueOf(totalValue).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP).doubleValue();
    }
}
