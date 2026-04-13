package com.pioneers.service.model.entities;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class Student {
    private String id;
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private boolean isLogin;
    private Timestamp lastLoginAt;
}
