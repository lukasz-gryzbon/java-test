package com.test.service.discount;

import com.test.model.ProductEnum;

import java.time.LocalDate;
import java.util.Map;

public class AppleDiscount implements Discount {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public AppleDiscount(final LocalDate startDate, final LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public double calculateDiscountValue(final Map<ProductEnum, Integer> shoppingCart, final LocalDate localDate) {
        return 0.0;
    }
}
