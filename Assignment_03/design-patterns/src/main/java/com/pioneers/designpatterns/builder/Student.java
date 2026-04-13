package com.pioneers.designpatterns.builder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
    private String id;
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private boolean isLogin;
    /*public Student(StudentBuilder studentBuilder) {
        this.id = studentBuilder.id;
        this.fullName = studentBuilder.fullName;
        this.email = studentBuilder.email;
        this.phone = studentBuilder.phone;
        this.password = studentBuilder.password;
        this.isLogin = studentBuilder.isLogin;
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public static class StudentBuilder {
        private String id;
        private String fullName;
        private String email;
        private String phone;
        private String password;
        private boolean isLogin;

        public StudentBuilder id(String id) {
            this.id = id;
            return this;
        }

        public StudentBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public StudentBuilder email(String email) {
            this.email = email;
            return this;
        }

        public StudentBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public StudentBuilder password(String password) {
            this.password = password;
            return this;
        }

        public StudentBuilder isLogin(boolean isLogin) {
            this.isLogin = isLogin;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }*/
}
