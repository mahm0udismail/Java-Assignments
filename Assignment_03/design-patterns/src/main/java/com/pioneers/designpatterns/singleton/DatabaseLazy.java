package com.pioneers.designpatterns.singleton;

import com.pioneers.designpatterns.builder.Student;

import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseLazy {

    private final Map<String, Student> students = new LinkedHashMap<>();

    private DatabaseLazy() {
    }

    private static DatabaseLazy instance;

    public static DatabaseLazy getInstance() {
        if (instance == null) {
            instance = new DatabaseLazy();
        }
        return instance;
    }

    public Map<String, Student> getStudents() {
        return students;
    }
}
