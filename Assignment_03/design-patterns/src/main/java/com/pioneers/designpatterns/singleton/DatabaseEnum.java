package com.pioneers.designpatterns.singleton;

import com.pioneers.designpatterns.builder.Student;

import java.util.LinkedHashMap;
import java.util.Map;

public enum DatabaseEnum {

    INSTANCE;

    private final Map<String, Student> students = new LinkedHashMap<>();


    public static DatabaseEnum getInstance() {
        return INSTANCE;
    }

    public Map<String, Student> getStudents() {
        return students;
    }
}
