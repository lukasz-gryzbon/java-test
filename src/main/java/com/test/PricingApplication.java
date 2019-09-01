package com.test;

import static java.util.Arrays.asList;

import com.test.service.PricingService;
import com.test.service.ShoppingCartParser;

import java.util.List;

public class PricingApplication {

    private static ShoppingCartParser shoppingCartParser = new ShoppingCartParser();
    private static PricingService pricingService = new PricingService();

    private static final String VALIDATION_FAILURE_MESSAGE = "Argument missing. Usage: $ gradle run --args=\"{}\"";

    public static void main(final String[] args) {
        final List<String> argList = asList(args);
        validateArgument(argList);
        final double value = pricingService.calculateValue(shoppingCartParser.parse(argList.get(0)));
        System.out.println("{\"totalCost\":" + value + "}");
    }

    private static void validateArgument(List<String> argList) {
        if (argList.size() < 1) {
            throw new IllegalArgumentException(VALIDATION_FAILURE_MESSAGE);
        }
    }
}