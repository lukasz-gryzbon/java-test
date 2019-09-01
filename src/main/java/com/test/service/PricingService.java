package com.test.service;

import java.util.Map;

public class PricingService {

    public double calculateValue(Map<String, Integer> shoppingCart) {
        if (shoppingCart == null || shoppingCart.size() == 0) {
            return 0;
        }
        final String itemName = shoppingCart.keySet().iterator().next();
        if (itemName.equalsIgnoreCase("soup")) {
            return shoppingCart.values().iterator().next() * 0.65;
        }
        return shoppingCart.values().iterator().next() * 0.80;
    }
}
