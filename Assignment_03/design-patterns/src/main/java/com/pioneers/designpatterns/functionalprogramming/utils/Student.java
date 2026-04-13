package com.pioneers.designpatterns.functionalprogramming.utils;

public record Student(String firstName, String lastName, String email, int age) {

    public static String retrieveStudentName(final Student student) {
        return student.firstName() + " " + student.lastName();
    }

    public static String retrieveStudentEmail(final Student student) {
        return student.email();
    }

    public static int retrieveStudentAge(final Student student) {
        return student.age();
    }
}
