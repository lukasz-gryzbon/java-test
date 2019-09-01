package com.test.service.discount;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import java.util.HashMap;

public class BreadDiscountTest {

    private BreadDiscount breadDiscount = new BreadDiscount();

    @Test
    public void shouldNotGenerateDiscountForEmptyCart() {
        // WHEN
        final double discountValue = breadDiscount.calculateDiscountValue(new HashMap<>());

        // THEN
        assertThat(discountValue, equalTo(0));
    }

}