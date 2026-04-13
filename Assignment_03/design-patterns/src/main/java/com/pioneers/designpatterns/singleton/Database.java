package com.pioneers.designpatterns.singleton;

import com.pioneers.designpatterns.builder.Student;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
//@Lazy
@Component
@Value
public class Database {

    public Database() {
        log.trace("Creating the {} bean!!", Database.class.getSimpleName());
    }

    Map<String, Student> students = new LinkedHashMap<>();
}
