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

public class BreadDiscountTest {

    private final Map<ProductEnum, Integer> shoppingCart = new HashMap<>();
    private Discount underTest = new BreadDiscount(now().minusDays(1), now().plusDays(1));

    @Test
    public void shouldNotGenerateDiscountForEmptyCart() {
        // WHEN
        final double discountValue = underTest.calculateDiscountValue(new HashMap<>(), now());

        // THEN
        assertThat(discountValue, equalTo(0.0));
    }

    @Test
    public void shouldNotGenerateDiscountWithNoBread() {
        // GIVEN
        shoppingCart.put(SOUP, 5);
        shoppingCart.put(MILK, 5);
        shoppingCart.put(APPLE, 5);

        // WHEN
        final double discountValue = underTest.calculateDiscountValue(shoppingCart, now());

        // THEN
        assertThat(discountValue, equalTo(0.0));
    }

    @Test
    public void shouldNotGenerateDiscountWithNoSoup() {
        // GIVEN
        shoppingCart.put(BREAD, 5);

        // WHEN
        final double discountValue = underTest.calculateDiscountValue(shoppingCart, now());

        // THEN
        assertThat(discountValue, equalTo(0.0));
    }

    @Test
    public void shouldGenerateDiscountForCartWithOneSoup() {
        // GIVEN
        shoppingCart.put(SOUP, 1);
        shoppingCart.put(BREAD, 2);

        // WHEN
        final double discountValue = underTest.calculateDiscountValue(shoppingCart, now());

        // THEN
        assertThat(discountValue, equalTo(0.0));
    }

    @Test
    public void shouldGenerateDiscountForAllLoavesOfBread() {
        // GIVEN
        shoppingCart.put(SOUP, 4);
        shoppingCart.put(BREAD, 2);

        // WHEN
        final double discountValue = underTest.calculateDiscountValue(shoppingCart, now());

        // THEN
        assertThat(discountValue, equalTo(0.8));
    }

    @Test
    public void shouldGenerateDiscountForAllLoavesOfBreadButOne() {
        // GIVEN
        shoppingCart.put(SOUP, 4);
        shoppingCart.put(BREAD, 3);

        // WHEN
        final double discountValue = underTest.calculateDiscountValue(shoppingCart, now());

        // THEN
        assertThat(discountValue, equalTo(0.8));
    }

    @Test
    public void shouldGenerateDiscountForBasedOnEvenNumberOfSoups() {
        // GIVEN
        shoppingCart.put(SOUP, 5);
        shoppingCart.put(BREAD, 3);

        // WHEN
        final double discountValue = underTest.calculateDiscountValue(shoppingCart, now());

        // THEN
        assertThat(discountValue, equalTo(0.8));
    }

    @Test
    public void shouldNotGenerateDiscountForAllLoavesOfBreadOutsideApplicablePeriod() {
        // GIVEN
        shoppingCart.put(SOUP, 4);
        shoppingCart.put(BREAD, 2);

        // WHEN
        final double discountValue = underTest.calculateDiscountValue(shoppingCart, now().plusDays(8));

        // THEN
        assertThat(discountValue, equalTo(0.0));
    }
}