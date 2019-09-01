package com.test.service;

import static com.test.model.ProductEnum.APPLE;
import static com.test.model.ProductEnum.BREAD;
import static com.test.model.ProductEnum.MILK;
import static com.test.model.ProductEnum.SOUP;
import static java.time.LocalDate.now;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.test.model.ProductEnum;
import com.test.service.discount.Discount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class PricingServiceTest {

    private final Discount discount = Mockito.mock(Discount.class);
    private final Map<ProductEnum, Integer> shoppingCart = new HashMap<>();
    private final PricingService underTest = new PricingService(asList(discount));

    @Test
    public void shouldReturn0ForEmptyShoppingCart() {
        // WHEN
        double value = underTest.calculateValue(new HashMap<>());

        // THEN
        assertThat(value, equalTo(0.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfShoppingCartMissing() {
        // WHEN
        underTest.calculateValue(null);
    }

    @Test
    public void shouldReturnCorrectValueForOneTinOfSoup() {
        // GIVEN
        shoppingCart.put(SOUP, 1);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(0.65));
    }

    @Test
    public void shouldReturnCorrectValueForTwoTinsOfSoup() {
        // GIVEN
        shoppingCart.put(SOUP, 2);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(1.30));
    }

    @Test
    public void shouldReturnCorrectValueForOneBread() {
        // GIVEN
        shoppingCart.put(BREAD, 1);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(0.80));
    }

    @Test
    public void shouldReturnCorrectValueForTwoLoavesOfBread() {
        // GIVEN
        shoppingCart.put(BREAD, 2);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(1.60));
    }

    @Test
    public void shouldReturnCorrectValueForOneTinOfSoupAndOneBread() {
        // GIVEN
        shoppingCart.put(SOUP, 1);
        shoppingCart.put(BREAD, 1);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(1.45));
    }

    @Test
    public void shouldReturnCorrectValueForMultipleTinsOfSoupAndLoavesOfBread() {
        // GIVEN
        shoppingCart.put(SOUP, 2);
        shoppingCart.put(BREAD, 2);
        when(discount.calculateDiscountValue(shoppingCart, now())).thenReturn(0.4);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(2.50));
    }

    @Test
    public void shouldReturnCorrectValueForOneMilk() {
        // GIVEN
        shoppingCart.put(MILK, 1);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(1.30));
    }

    @Test
    public void shouldReturnCorrectValueForTwoBottlesOfMilk() {
        // GIVEN
        shoppingCart.put(MILK, 2);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(2.60));
    }

    @Test
    public void shouldReturnCorrectValueForOneTinOfSoupOneBreadAndOneMilk() {
        // GIVEN
        shoppingCart.put(SOUP, 1);
        shoppingCart.put(BREAD, 1);
        shoppingCart.put(MILK, 1);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(2.75));
    }

    @Test
    public void shouldReturnCorrectValueForMultipleTinsOfSoupLoavesOfBreadAndBottlesOfMilk() {
        // GIVEN
        shoppingCart.put(SOUP, 2);
        shoppingCart.put(BREAD, 2);
        shoppingCart.put(MILK, 2);
        when(discount.calculateDiscountValue(shoppingCart, now())).thenReturn(0.4);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(5.10));
    }

    @Test
    public void shouldReturnCorrectValueForOneApple() {
        // GIVEN
        shoppingCart.put(APPLE, 1);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(0.10));
    }

    @Test
    public void shouldReturnCorrectValueForTwoApples() {
        // GIVEN
        shoppingCart.put(APPLE, 2);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(0.20));
    }

    @Test
    public void shouldReturnCorrectValueForOneTinOfSoupOneBreadOneMilkAndAnApple() {
        // GIVEN
        shoppingCart.put(SOUP, 1);
        shoppingCart.put(BREAD, 1);
        shoppingCart.put(MILK, 1);
        shoppingCart.put(APPLE, 1);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(2.85));
    }

    @Test
    public void shouldReturnCorrectValueForMultipleTinsOfSoupLoavesOfBreadBottlesOfMilkAndTwoApples() {
        // GIVEN
        shoppingCart.put(SOUP, 2);
        shoppingCart.put(BREAD, 2);
        shoppingCart.put(MILK, 2);
        shoppingCart.put(APPLE, 2);
        when(discount.calculateDiscountValue(shoppingCart, now())).thenReturn(0.4);

        // WHEN
        double value = underTest.calculateValue(shoppingCart);

        // THEN
        assertThat(value, equalTo(5.30));
    }

    @Test
    public void shouldExecuteDiscountCalculation() {
        // GIVEN
        shoppingCart.put(SOUP, 1);

        // WHEN
        underTest.calculateValue(shoppingCart);

        // THEN
        verify(discount).calculateDiscountValue(shoppingCart, now());
    }
}