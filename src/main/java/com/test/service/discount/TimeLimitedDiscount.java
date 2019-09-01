package com.test.service.discount;

import java.time.LocalDate;

abstract class TimeLimitedDiscount {

    private final LocalDate startDate;
    private final LocalDate endDate;

    TimeLimitedDiscount(final LocalDate startDate, final LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    boolean isDiscountApplicable(LocalDate localDate) {
        return (localDate.isEqual(startDate) || localDate.isAfter(startDate)) && (localDate.isBefore(endDate) || localDate.isEqual(endDate));
    }
}
