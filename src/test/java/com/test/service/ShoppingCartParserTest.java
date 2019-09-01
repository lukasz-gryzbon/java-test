package com.test.service;

import static com.test.model.ProductEnum.MILK;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import com.test.model.ProductEnum;
import org.junit.Test;

import java.util.Map;

public class ShoppingCartParserTest {

    private ShoppingCartParser shoppingCartParser = new ShoppingCartParser();

    @Test
    public void shouldJsonWithSingleItem() {
        // GIVEN
        final String shoppingCartJson = "{\"milk\":1}";

        // WHEN
        Map<ProductEnum, Integer> shoppingCart = shoppingCartParser.parse(shoppingCartJson);

        // THEN
        assertThat(shoppingCart.size(), is(1));
        assertTrue(shoppingCart.containsKey(MILK));
        assertTrue(shoppingCart.containsValue(1));
    }

}