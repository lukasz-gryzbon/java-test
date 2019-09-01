package com.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PricingApplicationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfNoArgumentsPassed() {

        // WHEN
        PricingApplication.main(new String[]{});
    }

    @Test
    public void shouldFailIfNoArgumentsPassedWitMeaningfulMessage() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Argument missing. Usage: $ ./java-test '{}'");

        // WHEN
        PricingApplication.main(new String[]{});
    }
}