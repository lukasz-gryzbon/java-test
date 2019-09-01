package com.test.service;

import static com.test.model.ProductEnum.APPLE;
import static com.test.model.ProductEnum.BREAD;
import static com.test.model.ProductEnum.MILK;
import static com.test.model.ProductEnum.SOUP;
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
    public void shouldParseJsonWithSingleItem() throws IOException {
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
    public void shouldParseJsonWithMultipleItems() throws IOException {
        // GIVEN
        final String shoppingCartJson = "{\"soup\":1, \"bread\":2, \"milk\":3, \"apple\":4}";

        // WHEN
        Map<ProductEnum, Integer> shoppingCart = shoppingCartParser.parse(shoppingCartJson);

        // THEN
        assertThat(shoppingCart.size(), is(4));
        assertThat(shoppingCart.get(SOUP), is(1));
        assertThat(shoppingCart.get(BREAD), is(2));
        assertThat(shoppingCart.get(MILK), is(3));
        assertThat(shoppingCart.get(APPLE), is(4));
    }

}