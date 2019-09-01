package com.test.service.discount;

import static com.test.model.ProductEnum.APPLE;
import static com.test.model.ProductEnum.BREAD;
import static com.test.model.ProductEnum.MILK;
import static com.test.model.ProductEnum.SOUP;
import static java.time.LocalDate.now;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.test.model.ProductEnum;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AppleDiscountTest {

    private final Map<ProductEnum, Integer> shoppingCart = new HashMap<>();
    private Discount underTest = new AppleDiscount(now().minusDays(1), now().plusDays(1));

    @Test
    public void shouldNotGenerateDiscountForEmptyCart() {
        // WHEN
        final double discountValue = underTest.calculateDiscountValue(new HashMap<>(), now());

        // THEN
        assertThat(discountValue, equalTo(0.0));
    }

    @Test
    public void shouldNotGenerateDiscountWithNoApple() {
        // GIVEN
        shoppingCart.put(SOUP, 5);
        shoppingCart.put(MILK, 5);
        shoppingCart.put(BREAD, 5);

        // WHEN
        final double discountValue = underTest.calculateDiscountValue(shoppingCart, now());

        // THEN
        assertThat(discountValue, equalTo(0.0));
    }

    @Test
    public void shouldGenerateDiscountWithOneApple() {
        // GIVEN
        shoppingCart.put(APPLE, 1);

        // WHEN
        final double discountValue = underTest.calculateDiscountValue(shoppingCart, now());

        // THEN
        assertThat(discountValue, equalTo(0.01));
    }

    @Test
    public void shouldGenerateDiscountWithMultipleApple() {
        // GIVEN
        shoppingCart.put(APPLE, 10);

        // WHEN
        final double discountValue = underTest.calculateDiscountValue(shoppingCart, now());

        // THEN
        assertThat(discountValue, equalTo(0.1));
    }

}