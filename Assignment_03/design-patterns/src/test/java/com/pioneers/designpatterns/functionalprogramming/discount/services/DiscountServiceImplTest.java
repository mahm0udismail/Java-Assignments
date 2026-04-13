package com.pioneers.designpatterns.functionalprogramming.discount.services;

import com.pioneers.designpatterns.functionalprogramming.discount.models.dtos.requests.OrderReceipt;
import com.pioneers.designpatterns.functionalprogramming.discount.models.dtos.requests.ProductItem;
import com.pioneers.designpatterns.functionalprogramming.discount.rules.DiscountEngine;
import com.pioneers.designpatterns.functionalprogramming.discount.utils.categories.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountServiceImplTest {

    @Mock
    private DiscountEngine discountEngine;

    @InjectMocks
    private DiscountServiceImpl discountService;

    private static OrderReceipt orderReceipt;

    @BeforeAll
    static void setup() {
        orderReceipt = new OrderReceipt(List.of(
                new ProductItem(Category.ELECTRONICS, "Laptop Dell", 1, BigDecimal.valueOf(30000)),
                new ProductItem(Category.CLOTHING, "Defacto", 6, BigDecimal.valueOf(200)),
                new ProductItem(Category.ELECTRONICS, "Mouse", 1, BigDecimal.valueOf(150)),
                new ProductItem(Category.CLOTHING, "Jacket", 2, BigDecimal.valueOf(20000))
        ));
    }

    @Test
    void testApplyDiscountWhenLaptopRuleAppliedThen500DiscountApplied() {
        // Arrange
        BigDecimal totalPrice = orderReceipt.calculateTotalPrice();
        BigDecimal amountAfterLaptopDiscount = totalPrice.subtract(BigDecimal.valueOf(500));

        when(discountEngine.applyDiscount(orderReceipt))
                .thenReturn(amountAfterLaptopDiscount);

        // Ack
        BigDecimal amountAfterDiscount = discountService.applyDiscount(orderReceipt);

        // Assert
        assertEquals(amountAfterLaptopDiscount, amountAfterDiscount);
        verify(discountEngine).applyDiscount(orderReceipt);
    }

    @Test
    void testApplyDiscountWhenLaptopRuleAppliedAndQuantityRuleAppliedThen500And5PercentDiscountApplied() {
        // Arrange
        BigDecimal totalPrice = orderReceipt.calculateTotalPrice();                        // 31350
        BigDecimal laptopDiscount = totalPrice.subtract(BigDecimal.valueOf(500));          // 30850
        BigDecimal quantityDiscount = laptopDiscount.multiply(BigDecimal.valueOf(0.95));   // 29307

        when(discountEngine.applyDiscount(orderReceipt))
                .thenReturn(quantityDiscount);

        // Ack
        BigDecimal amountAfterDiscount = discountService.applyDiscount(orderReceipt);

        // Assert
        assertEquals(quantityDiscount, amountAfterDiscount);
        verify(discountEngine).applyDiscount(orderReceipt);
    }

    @Test
    void testApplyDiscountWhenLaptopAndQuantityAndHighPriceRulesAppliedThen500And5And10PercentDiscountApplied() {
        // Arrange
        BigDecimal totalPrice = orderReceipt.calculateTotalPrice();                        // 71350
        BigDecimal laptopDiscount = totalPrice.subtract(BigDecimal.valueOf(500));          // 70850
        BigDecimal quantityDiscount = laptopDiscount.multiply(BigDecimal.valueOf(0.95));   // 67307.5
        BigDecimal highPriceDiscount = quantityDiscount.multiply(BigDecimal.valueOf(0.9)); // 60576.75


        when(discountEngine.applyDiscount(orderReceipt))
                .thenReturn(highPriceDiscount);

        // Ack
        BigDecimal amountAfterDiscount = discountService.applyDiscount(orderReceipt);

        // Assert
        assertEquals(highPriceDiscount, amountAfterDiscount);
        verify(discountEngine).applyDiscount(orderReceipt);
    }

    @Test
    void testApplyDiscountWhenNoRuleAppliedThenNoDiscountApplied() {
        // Arrange
        BigDecimal totalPrice = orderReceipt.calculateTotalPrice();                        // 71350


        when(discountEngine.applyDiscount(orderReceipt))
                .thenReturn(totalPrice);

        // Ack
        BigDecimal amountAfterDiscount = discountService.applyDiscount(orderReceipt);

        // Assert
        assertEquals(totalPrice, amountAfterDiscount);
        verify(discountEngine).applyDiscount(orderReceipt);
    }
}
