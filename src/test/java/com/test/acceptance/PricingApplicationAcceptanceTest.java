package com.test.acceptance;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import com.test.PricingApplication;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PricingApplicationAcceptanceTest {

    private ByteArrayOutputStream console = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(console));
    }

    @Test
    public void shouldCalculate3TinsOfSoupAnd2LoavesOfBreadBoughtToday() {
        // GIVEN
        final String shoppingCartItemsJson = "{\"soup\":3, \"bread\":2}";

        // WHEN
        PricingApplication.main(asArray(shoppingCartItemsJson));

        // THEN

        assertThat(console.toString().trim(), equalTo("{\"totalCost\":3.15}"));
    }

    @Test
    public void shouldCalculate6ApplesAndABottleOfMilkBoughtToday() {
        // GIVEN
        final String shoppingCartItemsJson = "{\"apple\":6, \"milk\":1}";

        // WHEN
        PricingApplication.main(asArray(shoppingCartItemsJson));

        // THEN

        assertThat(console.toString().trim(), equalTo("{\"totalCost\":1.90}"));
    }

    private String[] asArray(String shoppingCartItemsJson) {
        return new String[]{shoppingCartItemsJson};
    }
}