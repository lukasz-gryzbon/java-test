package com.test.service;

import static com.test.model.ProductEnum.BREAD;
import static com.test.model.ProductEnum.SOUP;

import com.test.model.ProductEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class PricingService {

    private static final int DECIMAL_PLACES = 2;

    public double calculateValue(Map<ProductEnum, Integer> shoppingCart) {
        validate(shoppingCart);
        final int discountedLoavesOfBread = getDiscountedLoavesOfBread(shoppingCart);
        return round(shoppingCart.entrySet().stream().mapToDouble(entry -> {
            if (discountedLoavesOfBread > 0 && entry.getKey().equals(BREAD)) {
                int fullPriceLoaves = Math.max(entry.getValue().intValue() - discountedLoavesOfBread, 0);
                final double breadPrice = entry.getKey().getPrice();
                return fullPriceLoaves * breadPrice + discountedLoavesOfBread * breadPrice / 2.0;
            } else {
                return entry.getValue() * entry.getKey().getPrice();
            }
        }).sum());
    }

    private int getDiscountedLoavesOfBread(Map<ProductEnum, Integer> shoppingCart) {
        if (shoppingCart.containsKey(SOUP) && shoppingCart.get(SOUP) > 1) {
            return shoppingCart.get(SOUP) / 2;
        }
        return 0;
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
