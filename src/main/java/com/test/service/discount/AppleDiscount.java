package com.test.service.discount;

import static com.test.model.ProductEnum.APPLE;

import com.test.model.ProductEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

public class AppleDiscount extends TimeLimitedDiscount implements Discount {

    private static final double DISCOUNT = 0.1;
    private static final int DECIMAL_PLACES = 2;

    public AppleDiscount(final LocalDate startDate, final LocalDate endDate) {
        super(startDate, endDate);
    }

    @Override
    public double calculateDiscountValue(final Map<ProductEnum, Integer> shoppingCart, final LocalDate localDate) {
        if (shoppingCart.containsKey(APPLE) && isDiscountApplicable(localDate) ) {
            return round(shoppingCart.get(APPLE) * APPLE.getPrice() * DISCOUNT);
        }
        return 0.0;
    }

    private double round(double value) {
        return BigDecimal.valueOf(value).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP).doubleValue();
    }
}
