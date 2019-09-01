package com.test.acceptance;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import com.test.PricingApplication;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

        assertThat(console.toString().trim(), equalTo("{\"totalCost\":1.9}"));
    }

    @Test
    public void shouldCalculate6ApplesAndABottleOfMilkBoughtIn5DaysTime() {
        // GIVEN
        final String shoppingCartItemsJson = "{\"apple\":6, \"milk\":1}";
        final String calculationDate = LocalDate.now().plusDays(5).format(ISO_DATE);

        // WHEN
        PricingApplication.main(asArray(shoppingCartItemsJson, calculationDate));

        // THEN

        assertThat(console.toString().trim(), equalTo("{\"totalCost\":1.84}"));
    }

    @Test
    public void shouldCalculate3Apples2TinsOfSoupAndALoafOfBreadBoughtIn5DaysTime() {
        // GIVEN
        final String shoppingCartItemsJson = "{\"apple\":3, \"soup\":2, \"bread\":1}";
        final String calculationDate = LocalDate.now().plusDays(5).format(ISO_DATE);

        // WHEN
        PricingApplication.main(asArray(shoppingCartItemsJson, calculationDate));

        // THEN

        assertThat(console.toString().trim(), equalTo("{\"totalCost\":1.97}"));
    }

    private String[] asArray(String...values) {
        return values;
    }
}