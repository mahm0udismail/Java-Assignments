package com.pioneers.designpatterns.functionalprogramming.discount.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private static Calculator calculator;

    @BeforeAll
    static void setup() {
        calculator = new Calculator();
    }

    @Test
    void testSumTwoPositiveNumbers() {
        // Arrange
        int num1 = 4, num2 = 2;

        // Act
        final int actualValue = calculator.sum(num1, num2);

        // Assert
        assertEquals(6, actualValue);
        assertTrue(actualValue > 0);
    }

    @Test
    void testSumTwoNegativeNumbers() {
        // Arrange
        int num1 = -4, num2 = -2;

        // Act
        final int actualValue = calculator.sum(num1, num2);

        // Assert
        assertEquals(-6, actualValue);
    }

    @Test
    void testSumPositiveAndNegativeNumbers() {
        // Arrange
        int num1 = 4, num2 = -2;

        // Act
        final int actualValue = calculator.sum(num1, num2);

        // Assert
        assertEquals(2, actualValue);
    }

    @Test
    void testSubtractTwoPositiveNumbers() {
        // Arrange
        int num1 = 4, num2 = 2;

        // Act
        final int actualValue = calculator.subtract(num1, num2);

        // Assert
        assertEquals(2, actualValue);
    }

    @Test
    void testSubtractTwoNegativeNumbers() {
        // Arrange
        int num1 = -4, num2 = -2;

        // Act
        final int actualValue = calculator.subtract(num1, num2);

        // Assert
        assertEquals(-2, actualValue);
    }

    @Test
    void testSubtractPositiveAndNegativeNumbers() {
        // Arrange
        int num1 = 4, num2 = -2;

        // Act
        final int actualValue = calculator.subtract(num1, num2);

        // Assert
        assertEquals(6, actualValue);
    }

    @Test
    void testMultiplyTwoPositiveNumbers() {
        // Arrange
        int num1 = 4, num2 = 2;

        // Act
        final int actualValue = calculator.multiply(num1, num2);

        // Assert
        assertEquals(8, actualValue);
    }

    @Test
    void testMultiplyTwoNegativeNumbers() {
        // Arrange
        int num1 = -4, num2 = -2;

        // Act
        final int actualValue = calculator.multiply(num1, num2);

        // Assert
        assertEquals(8, actualValue);
    }

    @Test
    void testMultiplyPositiveAndNegativeNumbers() {
        // Arrange
        int num1 = 4, num2 = -2;

        // Act
        final int actualValue = calculator.multiply(num1, num2);

        // Assert
        assertEquals(-8, actualValue);
    }

    @Test
    void testDivideTwoPositiveNumbers() {
        // Arrange
        int num1 = 4, num2 = 2;

        // Act
        final int actualValue = calculator.divide(num1, num2);

        // Assert
        assertEquals(2, actualValue);
    }

    @Test
    void testDivideTwoNegativeNumbers() {
        // Arrange
        int num1 = -4, num2 = -2;

        // Act
        final int actualValue = calculator.divide(num1, num2);

        // Assert
        assertEquals(2, actualValue);
    }

    @Test
    void testDividePositiveAndNegativeNumbers() {
        // Arrange
        int num1 = 4, num2 = -2;

        // Act
        final int actualValue = calculator.divide(num1, num2);

        // Assert
        assertEquals(-2, actualValue);
    }

    @Test
    void testDivideByZeroNumbers() {
        // Arrange
        int num1 = 4, num2 = 0;

        // Assert
        assertThrows(ArithmeticException.class, () -> calculator.divide(num1, num2));
    }
}
