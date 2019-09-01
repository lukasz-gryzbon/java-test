package com.test;

import static java.time.LocalDate.now;
import static java.time.format.DateTimeFormatter.ISO_DATE;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.util.Arrays.asList;

import com.test.model.Result;
import com.test.service.PricingService;
import com.test.service.ResultSerialiser;
import com.test.service.ShoppingCartParser;
import com.test.service.discount.AppleDiscount;
import com.test.service.discount.BreadDiscount;
import com.test.service.discount.Discount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class PricingApplication {

    private static final Logger logger = LoggerFactory.getLogger(PricingApplication.class);
    private static final String VALIDATION_FAILURE_MESSAGE = "Argument missing. Usage: $ ./java-test '{}'";

    private final ShoppingCartParser shoppingCartParser;
    private final PricingService pricingService;
    private final ResultSerialiser resultSerialiser;

    private PricingApplication() {
        shoppingCartParser = new ShoppingCartParser();
        final Discount breadDiscount = new BreadDiscount(now().minusDays(1), now().plusDays(7));
        final Discount appleDiscount = new AppleDiscount(now().plusDays(3), now().plusMonths(1).with(lastDayOfMonth()));
        pricingService = new PricingService(asList(breadDiscount, appleDiscount));
        resultSerialiser = new ResultSerialiser();
    }

    public static void main(final String[] args) {
        new PricingApplication().process(args);
    }

    private void process(final String[] args) {
        logger.info("Processing:{}", (Object) args);
        final List<String> argList = asList(args);
        validateArgument(argList);
        writeResult(new Result(getValue(argList)));
    }

    private double getValue(final List<String> argList) {
        if (argList.size() < 2) {
            return pricingService.calculateValue(shoppingCartParser.parse(argList.get(0)));
        } else {
            return pricingService.calculateValue(shoppingCartParser.parse(argList.get(0)), LocalDate.parse(argList.get(1), ISO_DATE));
        }
    }

    private void writeResult(final Result result) {
        logger.debug("Result received:{}", result);
        final String resultString = resultSerialiser.serialiseResult(result);
        System.out.println(resultString);
    }

    private void validateArgument(final List<String> argList) {
        if (argList.size() < 1) {
            throw new IllegalArgumentException(VALIDATION_FAILURE_MESSAGE);
        }
    }
}