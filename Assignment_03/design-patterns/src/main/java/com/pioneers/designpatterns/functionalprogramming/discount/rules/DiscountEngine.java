package com.pioneers.designpatterns.functionalprogramming.discount.rules;

import com.pioneers.designpatterns.functionalprogramming.discount.models.dtos.requests.OrderReceipt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Delegator class that apply the discount for all rules.
 *
 * @author abdelaziz
 */
// TODO: Create all unit tests for all methods in that class.
@Component
@RequiredArgsConstructor
public class DiscountEngine {

    private final List<DiscountRule> discountRules;

    public BigDecimal applyDiscount(final OrderReceipt orderReceipt) {
        final BigDecimal totalPrice = orderReceipt.calculateTotalPrice();

        return discountRules.stream()
//                .sorted(Comparator.comparingInt(DiscountRule::order).reversed())
                .reduce(
                        totalPrice,
                        (currentTotalPrice, discountRule) -> discountRule.applyDiscount(orderReceipt, currentTotalPrice),
                        BigDecimal::add
                );
    }
}
