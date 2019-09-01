package com.test.service;

import static java.time.LocalDate.now;

import com.test.model.ProductEnum;
import com.test.service.discount.Discount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class PricingService {

    private static final int DECIMAL_PLACES = 2;

    private final List<Discount> discounts;

    public PricingService(final List<Discount> discounts) {
        this.discounts = discounts;
    }

    public double calculateValue(final Map<ProductEnum, Integer> shoppingCart) {
        return calculateValue(shoppingCart, now());
    }

    public double calculateValue(final Map<ProductEnum, Integer> shoppingCart, final LocalDate localDate) {
        validate(shoppingCart);
        final double discountValue = discounts.stream().mapToDouble(discount -> discount.calculateDiscountValue(shoppingCart, localDate)).sum();
        final double valueBeforeDiscount = shoppingCart.entrySet().stream().mapToDouble(entry -> entry.getValue() * entry.getKey().getPrice()).sum();
        return round(valueBeforeDiscount - discountValue);
    }

    private void validate(Map<ProductEnum, Integer> shoppingCart) {
        if (shoppingCart == null) {
            throw new IllegalArgumentException("Missing shopping cart!");
        }
    }

    private double round(double value) {
        return BigDecimal.valueOf(value).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP).doubleValue();
    }
}
