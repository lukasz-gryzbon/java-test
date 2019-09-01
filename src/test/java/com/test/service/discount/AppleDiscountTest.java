package com.test.service.discount;

import static java.time.LocalDate.now;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import java.util.HashMap;

public class AppleDiscountTest {

    private AppleDiscount appleDiscount = new AppleDiscount(now().minusDays(1), now().plusDays(1));

    @Test
    public void shouldNotGenerateDiscountForEmptyCart() {
        // WHEN
        final double discountValue = appleDiscount.calculateDiscountValue(new HashMap<>(), now());

        // THEN
        assertThat(discountValue, equalTo(0.0));
    }

}