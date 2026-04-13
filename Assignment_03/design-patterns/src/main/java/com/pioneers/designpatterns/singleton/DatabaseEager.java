package com.pioneers.designpatterns.singleton;

import com.pioneers.designpatterns.builder.Student;

import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseEager {

    private final Map<String, Student> students;

    private DatabaseEager() {
        students = new LinkedHashMap<>();
    }

    private final static DatabaseEager DATABASE_INSTANCE = new DatabaseEager();

    public static DatabaseEager getInstance() {
        return DATABASE_INSTANCE;
    }

    public Map<String, Student> getStudents() {
        return students;
    }
}
