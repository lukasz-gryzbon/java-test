package com.test.service.discount;

import static com.test.model.ProductEnum.BREAD;
import static com.test.model.ProductEnum.SOUP;

import com.test.model.ProductEnum;

import java.util.Map;

public class BreadDiscount implements Discount {

    @Override
    public double calculateDiscountValue(final Map<ProductEnum, Integer> shoppingCart) {
        final int maxDiscountedLoaves = getDiscountedLoavesOfBread(shoppingCart);
        if (maxDiscountedLoaves > 0 && shoppingCart.containsKey(BREAD)) {
            final Integer loavesOfBread = shoppingCart.get(BREAD);
            final int fullPriceLoaves = Math.max(loavesOfBread - maxDiscountedLoaves, 0);
            final int discountedLoaves = loavesOfBread - fullPriceLoaves;
            return discountedLoaves * BREAD.getPrice() / 2;
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
