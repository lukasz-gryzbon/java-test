package com.test;

import static java.util.Arrays.asList;

import com.test.service.PricingService;
import com.test.service.ShoppingCartParser;
import com.test.service.discount.BreadDiscount;

import java.util.List;

public class PricingApplication {

    private static ShoppingCartParser shoppingCartParser = new ShoppingCartParser();
    private static PricingService pricingService = new PricingService(asList(new BreadDiscount()));

    private static final String VALIDATION_FAILURE_MESSAGE = "Argument missing. Usage: $ gradle run --args=\"{}\"";

    public static void main(final String[] args) {
        final List<String> argList = asList(args);
        validateArgument(argList);
        final double value = pricingService.calculateValue(shoppingCartParser.parse(argList.get(0)));
        System.out.println(String.format("{\"totalCost\":%.2f}", value));
    }

    private static void validateArgument(List<String> argList) {
        if (argList.size() < 1) {
            throw new IllegalArgumentException(VALIDATION_FAILURE_MESSAGE);
        }
    }
}