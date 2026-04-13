package com.pioneers.designpatterns.functionalprogramming.discount.models.dtos.requests;

import com.pioneers.designpatterns.functionalprogramming.discount.utils.categories.Category;

import java.math.BigDecimal;

public record ProductItem(Category category, String productName, int quantity, BigDecimal price) {

    public BigDecimal totalPrice() {
        return price.multiply(new BigDecimal(quantity));
    }
}
