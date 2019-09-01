package com.test.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PricingServiceTest {

    private PricingService underTest = new PricingService();

    @Test
    public void shouldReturn0ForEmptyShoppingCart() {
        // WHEN
        double value = underTest.calculateValue(new HashMap<>());

        // THEN
        assertThat(value, equalTo(0.0));
    }

    @Test
    public void shouldReturnCorrectValueForOneTinOfSoup() {
        // GIVEN
        final Map<String, Integer> shoppingCart = new HashMap<>();
        shoppingCart.put("soup", 1);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(0.65));
    }

}