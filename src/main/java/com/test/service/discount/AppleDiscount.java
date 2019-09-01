package com.test.service.discount;

import static com.test.model.ProductEnum.APPLE;

import com.test.model.ProductEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

public class AppleDiscount implements Discount {

    private static final int DECIMAL_PLACES = 2;

    private final LocalDate startDate;
    private final LocalDate endDate;

    public AppleDiscount(final LocalDate startDate, final LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public double calculateDiscountValue(final Map<ProductEnum, Integer> shoppingCart, final LocalDate localDate) {
        if (shoppingCart.containsKey(APPLE) && isDiscountApplicable(localDate) ) {
            return round(shoppingCart.get(APPLE) * APPLE.getPrice() * 0.1);
        }
        return 0.0;
    }

    private boolean isDiscountApplicable(LocalDate localDate) {
        return (localDate.isEqual(startDate) || localDate.isAfter(startDate)) && (localDate.isBefore(endDate) || localDate.isEqual(endDate));
    }

    private double round(double value) {
        return BigDecimal.valueOf(value).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP).doubleValue();
    }
}
