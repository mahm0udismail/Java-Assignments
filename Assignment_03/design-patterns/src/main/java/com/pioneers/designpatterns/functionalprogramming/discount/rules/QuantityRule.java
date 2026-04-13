package com.pioneers.designpatterns.functionalprogramming.discount.rules;

import com.pioneers.designpatterns.functionalprogramming.discount.models.dtos.requests.OrderReceipt;
import com.pioneers.designpatterns.functionalprogramming.discount.models.dtos.requests.ProductItem;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order(value = 2)
public class QuantityRule implements DiscountRule {
    @Override
    public BigDecimal applyDiscount(OrderReceipt orderReceipt, BigDecimal totalPrice) {
        boolean isDiscountMatched = orderReceipt.productItems()
                .stream()
                .anyMatch(QuantityRule::isItemsMoreThanFive);

        if (!isDiscountMatched) {
            return totalPrice;
        }

        return totalPrice.multiply(BigDecimal.valueOf(0.95));
    }

    /*@Override
    public int order() {
        return 2;
    }*/

    private static boolean isItemsMoreThanFive(ProductItem productItem) {
        return productItem.quantity() > 5;
    }
}
