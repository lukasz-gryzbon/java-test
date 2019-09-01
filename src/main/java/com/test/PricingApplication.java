package com.test;

import static java.time.LocalDate.now;
import static java.time.format.DateTimeFormatter.ISO_DATE;
import static java.util.Arrays.asList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.Result;
import com.test.service.PricingService;
import com.test.service.ShoppingCartParser;
import com.test.service.discount.AppleDiscount;
import com.test.service.discount.BreadDiscount;
import com.test.service.discount.Discount;
import static java.time.temporal.TemporalAdjusters.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PricingApplication {

    private static ShoppingCartParser shoppingCartParser = new ShoppingCartParser();
    private static Discount breadDiscount = new BreadDiscount(now().minusDays(1), now().plusDays(7));
    private static Discount appleDiscount = new AppleDiscount(now().plusDays(3), now().plusMonths(1).with(lastDayOfMonth()));
    private static PricingService pricingService = new PricingService(asList(breadDiscount, appleDiscount));

    private static final String VALIDATION_FAILURE_MESSAGE = "Argument missing. Usage: $ ./java-test '{}'";

    public static void main(final String[] args) {
        final List<String> argList = asList(args);
        validateArgument(argList);
        writeResult(new Result(getValue(args, argList)));
    }

    private static double getValue(String[] args, List<String> argList) {
        final double value;
        if (argList.size() < 2) {
            value = pricingService.calculateValue(shoppingCartParser.parse(argList.get(0)));
        } else {
            value = pricingService.calculateValue(shoppingCartParser.parse(argList.get(0)), LocalDate.parse(args[1], ISO_DATE));
        }
        return value;
    }

    private static void writeResult(Result result) {
        final String resultString = serialiseResult(result);
        System.out.println(resultString);
    }

    private static String serialiseResult(Result result) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String resultString;
        try {
            resultString = objectMapper.writeValueAsString(result);
        } catch (IOException e) {
            throw new RuntimeException("Problem during result generation");
        }
        return resultString;
    }

    private static void validateArgument(List<String> argList) {
        if (argList.size() < 1) {
            throw new IllegalArgumentException(VALIDATION_FAILURE_MESSAGE);
        }
    }
}