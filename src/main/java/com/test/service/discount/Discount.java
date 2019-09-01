package com.test.service.discount;

import com.test.model.ProductEnum;

import java.time.LocalDate;
import java.util.Map;

public interface Discount {
    double calculateDiscountValue(Map<ProductEnum, Integer> shoppingCart, LocalDate localDate);
}
