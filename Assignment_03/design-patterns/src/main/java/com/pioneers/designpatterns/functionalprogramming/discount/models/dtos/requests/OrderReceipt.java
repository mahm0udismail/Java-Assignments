package com.pioneers.designpatterns.functionalprogramming.discount.models.dtos.requests;

import java.math.BigDecimal;
import java.util.List;

public record OrderReceipt(List<ProductItem> productItems) {

    public BigDecimal calculateTotalPrice() {
        return productItems.stream()
                .map(ProductItem::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
