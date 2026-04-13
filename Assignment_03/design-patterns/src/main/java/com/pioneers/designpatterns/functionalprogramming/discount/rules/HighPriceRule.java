package com.pioneers.designpatterns.functionalprogramming.discount.rules;

import com.pioneers.designpatterns.functionalprogramming.discount.models.dtos.requests.OrderReceipt;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order(value = 3)
public class HighPriceRule implements DiscountRule {
    @Override
    public BigDecimal applyDiscount(OrderReceipt orderReceipt, BigDecimal totalPrice) {
        if (totalPrice.compareTo(new BigDecimal(50000)) < 0) {
            return totalPrice;
        }

        return totalPrice.multiply(BigDecimal.valueOf(0.9));
    }

    /*@Override
    public int order() {
        return 1;
    }*/
}
