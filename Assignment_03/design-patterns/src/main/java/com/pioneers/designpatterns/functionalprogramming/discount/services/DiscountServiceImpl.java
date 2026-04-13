package com.pioneers.designpatterns.functionalprogramming.discount.services;

import com.pioneers.designpatterns.functionalprogramming.discount.models.dtos.requests.OrderReceipt;
import com.pioneers.designpatterns.functionalprogramming.discount.rules.DiscountEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountEngine discountEngine;

    @Override
    public BigDecimal applyDiscount(final OrderReceipt orderReceipt) {
        return discountEngine.applyDiscount(orderReceipt);
    }
}
