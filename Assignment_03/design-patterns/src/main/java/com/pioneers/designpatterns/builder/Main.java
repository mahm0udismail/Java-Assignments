package com.pioneers.designpatterns.builder;

public class Main {
    public static void main(String[] args) {
        Student student = Student.builder()
                .id("1")
                .fullName("Ahmed Mahmoud")
                .email("ahmed.mahmoud")
                .phone("123")
                .password("123")
                .isLogin(true)
                .build();

        System.out.println(student);
    }
}
