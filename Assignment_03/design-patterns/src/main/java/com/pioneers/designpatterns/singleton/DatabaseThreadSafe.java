package com.pioneers.designpatterns.singleton;

import com.pioneers.designpatterns.builder.Student;

import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseThreadSafe {

    private final Map<String, Student> students = new LinkedHashMap<>();

    private DatabaseThreadSafe() {
    }

    private static DatabaseThreadSafe instance;

    public static synchronized DatabaseThreadSafe getInstance() {
        if (instance == null) {
            instance = new DatabaseThreadSafe();
        }
        return instance;
    }

    public Map<String, Student> getStudents() {
        return students;
    }
}
