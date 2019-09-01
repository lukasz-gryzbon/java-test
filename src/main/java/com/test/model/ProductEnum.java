package com.test.model;

public enum ProductEnum {
    SOUP(0.65), BREAD(0.80), MILK(1.30);

    private double price;

    ProductEnum(final double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
