package com.pioneers.designpatterns.singleton;

import com.pioneers.designpatterns.builder.Student;

import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseDoubleCheckedLock {

    private final Map<String, Student> students = new LinkedHashMap<>();

    private DatabaseDoubleCheckedLock() {
    }

    private static DatabaseDoubleCheckedLock instance;

    public static DatabaseDoubleCheckedLock getInstance() {
        if (instance == null) {
            synchronized (DatabaseDoubleCheckedLock.class) {
                if (instance == null) {
                    instance = new DatabaseDoubleCheckedLock();
                }
            }
        }

        return instance;
    }

    public Map<String, Student> getStudents() {
        return students;
    }
}
