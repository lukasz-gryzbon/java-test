package com.test.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class PricingService {

    public double calculateValue(Map<String, Integer> shoppingCart) {
        if (shoppingCart == null || shoppingCart.size() == 0) {
            return 0;
        }
        double totalValue = 0.0;

        for (String itemName : shoppingCart.keySet()) {
            if (itemName.equalsIgnoreCase("soup")) {
                totalValue += shoppingCart.get(itemName) * 0.65;
            } else {
                totalValue += shoppingCart.get(itemName) * 0.80;
            }
        }

        return BigDecimal.valueOf(totalValue).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
