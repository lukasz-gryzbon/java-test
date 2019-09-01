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
        PricingApplication.main(new String[]{shoppingCartItemsJson});

        // THEN

        assertThat(console.toString().trim(), equalTo("{\"totalCost\":3.15}"));
    }
}