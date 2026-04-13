package com.pioneers.designpatterns.functionalprogramming.streams;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final int sum = numbers.stream()
                .filter(Main::isEven)
//                .reduce(0, Main::sum);
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println(sum);

    }

    public static boolean isEven(final int number) {
        return number % 2 == 0;
    }

    public static boolean isOdd(final int number) {
        return number % 2 != 0;
    }

    public static int sum(final int num1, final int num2) {
        return num1 + num2;
    }
}
