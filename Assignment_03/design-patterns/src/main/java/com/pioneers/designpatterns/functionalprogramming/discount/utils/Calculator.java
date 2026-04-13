package com.pioneers.designpatterns.functionalprogramming.discount.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Calculator {
    public int sum(final int num1, final int num2) {
        final int sum = num1 + num2;
        log.debug("The sum value is: {}", sum);
        return sum;
    }

    public int subtract(final int num1, final int num2) {
        return num1 - num2;
    }

    public int multiply(final int num1, final int num2) {
        return num1 * num2;
    }

    public int divide(final int num1, final int num2) {
        return num1 / num2;
    }
}
