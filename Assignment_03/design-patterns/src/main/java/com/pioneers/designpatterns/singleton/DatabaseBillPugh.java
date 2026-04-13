package com.pioneers.designpatterns.singleton;

import com.pioneers.designpatterns.builder.Student;

import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseBillPugh {

    private final Map<String, Student> students = new LinkedHashMap<>();

    private DatabaseBillPugh() {
    }

    public static class DatabaseBillPughHelper {
        public static final DatabaseBillPugh INSTANCE = new DatabaseBillPugh();
    }


    public static DatabaseBillPugh getInstance() {
        return DatabaseBillPughHelper.INSTANCE;
    }

    public Map<String, Student> getStudents() {
        return students;
    }
}
