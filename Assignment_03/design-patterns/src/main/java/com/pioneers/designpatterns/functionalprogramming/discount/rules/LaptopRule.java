package com.pioneers.designpatterns.functionalprogramming.discount.rules;

import com.pioneers.designpatterns.functionalprogramming.discount.models.dtos.requests.OrderReceipt;
import com.pioneers.designpatterns.functionalprogramming.discount.models.dtos.requests.ProductItem;
import com.pioneers.designpatterns.functionalprogramming.discount.utils.categories.Category;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order(value = 1)
public class LaptopRule implements DiscountRule {
    @Override
    public BigDecimal applyDiscount(OrderReceipt orderReceipt, BigDecimal totalPrice) {
        boolean isDiscountMatched = orderReceipt.productItems()
                .stream()
                .anyMatch(LaptopRule::isProductLaptop);

        if (!isDiscountMatched) {
            return totalPrice;
        }

        return totalPrice.subtract(new BigDecimal(500));
    }

    /*@Override
    public int order() {
        return 3;
    }*/

    private static boolean isProductLaptop(final ProductItem productItem) {
        return Category.ELECTRONICS.equals(productItem.category())
                && productItem.productName().toLowerCase().contains("laptop");
    }
}
