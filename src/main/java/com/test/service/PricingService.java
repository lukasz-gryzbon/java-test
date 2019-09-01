package com.test.service;

import java.util.Map;

public class PricingService {

    public double calculateValue(Map<String, Integer> shoppingCart) {
        if (shoppingCart == null || shoppingCart.size() == 0) {
            return 0;
        }
        return shoppingCart.values().iterator().next() * 0.65;
    }
}
