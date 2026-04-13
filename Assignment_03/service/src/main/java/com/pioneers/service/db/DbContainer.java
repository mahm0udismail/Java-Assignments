package com.pioneers.service.db;

import com.pioneers.service.model.entities.Student;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Value
@Component
public class DbContainer {
    Map<String, Student> studentsMap = new LinkedHashMap<>();
}
