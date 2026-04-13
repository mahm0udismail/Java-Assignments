package com.pioneers.designpatterns.functionalprogramming.utils;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;

public class FirstClassCitizen {
    public static void main(String[] args) {
        final int sum1 = processCalculator(Calculator::sum, 3, 2);
        System.out.println("sum1 = " + sum1);

        final int subtract = processCalculator(Calculator::subtract, 3, 2);
        System.out.println("subtract = " + subtract);

        final int multiply = processCalculator(Calculator::multiply, 3, 2);
        System.out.println("multiply = " + multiply);

        final int divide = processCalculator(Calculator::divide, 6, 3);
        System.out.println("divide = " + divide);

        int sum2 = processCalculator(Calculator::sum, 3, 2, 1);
        System.out.println("sum2 = " + sum2);

        final Student student = new Student("Bassem", "Asaad", "bassem.asaad@techpioneershub.com", 24);

        String fullName = returnStudentInfo(Student::retrieveStudentName, student);
        System.out.println("fullName = " + fullName);

        String email = returnStudentInfo(Student::retrieveStudentEmail, student);
        System.out.println("email = " + email);

        int age = returnStudentInfo(Student::retrieveStudentAge, student);
        System.out.println("age = " + age);

        Function<String, String> studentNameFunction = returnStudentInfoFunction(Student::retrieveStudentName, student);

        final String nameWithTitle = studentNameFunction.apply("Mr.");
        System.out.println(nameWithTitle);


        // returnStudentInfoFunction(Student::retrieveStudentEmail, student) is this result =====> t -> t + " " + "bassem.asaad@techpioneershub.com";
        Function<String, String> studentEmailFunction = returnStudentInfoFunction(Student::retrieveStudentEmail, student);
        final String emailWithPrefix = studentEmailFunction.apply("email =");
        System.out.println(emailWithPrefix);


        Function<String, String> studentAgeFunction = returnStudentInfoFunction(Student::retrieveStudentAge, student);
        final String ageWithPrefix = studentAgeFunction.apply("age =");
        System.out.println(ageWithPrefix);
    }

    public static <T> Function<String, String> returnStudentInfoFunction(
            final Function<Student, T> function,
            final Student student
    ) {
        T info = function.apply(student);

        return t -> t + " " + info;
    }

    public static int processCalculator(final IntBinaryOperator operator, final int num1, final int num2) {
        return operator.applyAsInt(num1, num2);
    }

    public static int processCalculator(
            final IntToIntTriFunction operator,
            final int num1,
            final int num2,
            final int num3
    ) {
        return operator.apply(num1, num2, num3);
    }

    public static <T> T returnStudentInfo(final Function<Student, T> function, final Student student) {
        return function.apply(student);
    }

}
