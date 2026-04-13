package com.pioneers.designpatterns.functionalprogramming.discount.services;

import com.pioneers.designpatterns.functionalprogramming.discount.models.dtos.requests.OrderReceipt;

import java.math.BigDecimal;

public interface DiscountService {

    BigDecimal applyDiscount(OrderReceipt orderReceipt);
}
