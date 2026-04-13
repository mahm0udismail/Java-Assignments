package com.pioneers.designpatterns.functionalprogramming.utils;

public class Calculator {

    public static int sum(final int num1, final int num2) {
        return num1 + num2;
    }

    public static int sum(final int num1, final int num2, final int num3) {
        return num1 + num2 + num3;
    }

    public static int subtract(final int num1, final int num2) {
        return num1 - num2;
    }

    public static int multiply(final int num1, final int num2) {
        return num1 * num2;
    }

    public static int divide(final int num1, final int num2) {
        return num1 / num2;
    }
}
