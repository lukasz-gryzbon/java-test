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

    @Test
    public void shouldReturnCorrectValueForTwoTinsOfSoup() {
        // GIVEN
        final Map<String, Integer> shoppingCart = new HashMap<>();
        shoppingCart.put("soup", 2);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(1.30));
    }

    @Test
    public void shouldReturnCorrectValueForOneBread() {
        // GIVEN
        final Map<String, Integer> shoppingCart = new HashMap<>();
        shoppingCart.put("bread", 1);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(0.80));
    }

    @Test
    public void shouldReturnCorrectValueForTwoLoavesOfBread() {
        // GIVEN
        final Map<String, Integer> shoppingCart = new HashMap<>();
        shoppingCart.put("bread", 2);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(1.60));
    }

    @Test
    public void shouldReturnCorrectValueForOneTinOfSoupAndOneBread() {
        // GIVEN
        final Map<String, Integer> shoppingCart = new HashMap<>();
        shoppingCart.put("soup", 1);
        shoppingCart.put("bread", 1);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(1.45));
    }

    @Test
    public void shouldReturnCorrectValueForMultipleTinsOfSoupAndLoavesOfBread() {
        // GIVEN
        final Map<String, Integer> shoppingCart = new HashMap<>();
        shoppingCart.put("soup", 2);
        shoppingCart.put("bread", 2);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(2.90));
    }

    @Test
    public void shouldReturnCorrectValueForOneMilk() {
        // GIVEN
        final Map<String, Integer> shoppingCart = new HashMap<>();
        shoppingCart.put("milk", 1);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(1.30));
    }
}