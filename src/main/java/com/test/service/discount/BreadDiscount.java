package com.test.service.discount;

import com.test.model.ProductEnum;

import java.util.Map;

public class BreadDiscount implements Discount {

    @Override
    public double calculateDiscountValue(final Map<ProductEnum, Integer> shoppingCart) {
        return 0.0;
    }
}
