package com.pioneers.designpatterns.functionalprogramming.discount.rules;

import com.pioneers.designpatterns.functionalprogramming.discount.models.dtos.requests.OrderReceipt;

import java.math.BigDecimal;

public interface DiscountRule {

    BigDecimal applyDiscount(OrderReceipt orderReceipt, BigDecimal totalPrice);

//    int order();
}
