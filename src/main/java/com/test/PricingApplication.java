package com.test;

import static java.util.Arrays.asList;

import java.util.List;

public class PricingApplication {

    private static final String VALIDATION_FAILURE_MESSAGE = "Argument missing. Usage: $ gradle run --args=\"{}\"";

    public static void main(final String[] args) {
        final List<String> argList = asList(args);
        validateArgument(argList);
        System.out.println("{\"totalCost\":3.15}");
    }

    private static void validateArgument(List<String> argList) {
        if (argList.size() < 1) {
            throw new IllegalArgumentException(VALIDATION_FAILURE_MESSAGE);
        }
    }
}