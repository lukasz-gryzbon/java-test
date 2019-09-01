package com.test.service;

import static com.test.model.ProductEnum.BREAD;
import static com.test.model.ProductEnum.MILK;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.test.model.ProductEnum;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class ShoppingCartParserTest {

    private ShoppingCartParser shoppingCartParser = new ShoppingCartParser();

    @Test
    public void shouldJsonWithSingleItem() throws IOException {
        // GIVEN
        final String shoppingCartJson = "{\"milk\":1}";

        // WHEN
        Map<ProductEnum, Integer> shoppingCart = shoppingCartParser.parse(shoppingCartJson);

        // THEN
        assertThat(shoppingCart.size(), is(1));
        assertTrue(shoppingCart.containsKey(MILK));
        assertTrue(shoppingCart.containsValue(1));
    }

    @Test
    public void shouldJsonWithTwoItems() throws IOException {
        // GIVEN
        final String shoppingCartJson = "{\"milk\":1, \"bread\":1}";

        // WHEN
        Map<ProductEnum, Integer> shoppingCart = shoppingCartParser.parse(shoppingCartJson);

        // THEN
        assertThat(shoppingCart.size(), is(2));
        assertThat(shoppingCart.get(MILK), is(1));
        assertThat(shoppingCart.get(BREAD), is(1));
    }

}