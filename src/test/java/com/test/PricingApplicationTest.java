package com.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class PricingApplicationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfNoArgumentsPassed() throws IOException {

        // WHEN
        PricingApplication.main(new String[]{});
    }

    @Test
    public void shouldFailIfNoArgumentsPassedWitMeaningfulMessage() throws IOException {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Argument missing. Usage: $ gradle run --args=\"{}\"");

        // WHEN
        PricingApplication.main(new String[]{});
    }
}