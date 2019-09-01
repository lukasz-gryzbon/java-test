package com.test.service.discount;

import static com.test.model.ProductEnum.BREAD;
import static com.test.model.ProductEnum.SOUP;

import com.test.model.ProductEnum;

import java.time.LocalDate;
import java.util.Map;

public class BreadDiscount extends TimeLimitedDiscount implements Discount {

    private static final double DISCOUNT = 0.5;

    public BreadDiscount(final LocalDate startDate, final LocalDate endDate) {
        super(startDate, endDate);
    }

    @Override
    public double calculateDiscountValue(final Map<ProductEnum, Integer> shoppingCart, final LocalDate localDate) {
        if (isDiscountApplicable(localDate)) {
            final int maxDiscountedLoaves = getDiscountedLoavesOfBread(shoppingCart);
            if (maxDiscountedLoaves > 0 && shoppingCart.containsKey(BREAD)) {
                final Integer loavesOfBread = shoppingCart.get(BREAD);
                final int fullPriceLoaves = Math.max(loavesOfBread - maxDiscountedLoaves, 0);
                final int discountedLoaves = loavesOfBread - fullPriceLoaves;
                return discountedLoaves * BREAD.getPrice() * DISCOUNT;
            }
        }
        return 0.0;
    }

    private int getDiscountedLoavesOfBread(Map<ProductEnum, Integer> shoppingCart) {
        if (shoppingCart.containsKey(SOUP) && shoppingCart.get(SOUP) > 1) {
            return shoppingCart.get(SOUP) / 2;
        }
        return 0;
    }
}
