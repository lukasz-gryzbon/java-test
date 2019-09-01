package com.test.service.discount;

import static com.test.model.ProductEnum.BREAD;
import static com.test.model.ProductEnum.SOUP;

import com.test.model.ProductEnum;

import java.time.LocalDate;
import java.util.Map;

public class BreadDiscount implements Discount {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public BreadDiscount(final LocalDate startDate, final LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public double calculateDiscountValue(final Map<ProductEnum, Integer> shoppingCart, final LocalDate localDate) {
        if (isDiscountApplicable(localDate)) {
            final int maxDiscountedLoaves = getDiscountedLoavesOfBread(shoppingCart);
            if (maxDiscountedLoaves > 0 && shoppingCart.containsKey(BREAD)) {
                final Integer loavesOfBread = shoppingCart.get(BREAD);
                final int fullPriceLoaves = Math.max(loavesOfBread - maxDiscountedLoaves, 0);
                final int discountedLoaves = loavesOfBread - fullPriceLoaves;
                return discountedLoaves * BREAD.getPrice() / 2;
            }
        }
        return 0.0;
    }

    private boolean isDiscountApplicable(LocalDate localDate) {
        return (localDate.isEqual(startDate) || localDate.isAfter(startDate)) && (localDate.isBefore(endDate) || localDate.isEqual(endDate));
    }

    private int getDiscountedLoavesOfBread(Map<ProductEnum, Integer> shoppingCart) {
        if (shoppingCart.containsKey(SOUP) && shoppingCart.get(SOUP) > 1) {
            return shoppingCart.get(SOUP) / 2;
        }
        return 0;
    }
}
