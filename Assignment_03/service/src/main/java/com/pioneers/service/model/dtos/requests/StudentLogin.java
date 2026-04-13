package com.pioneers.service.model.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentLogin(
        @Email(message = "Email must be valid")
        String email,
        @NotBlank(message = "Password cannot be blank or null")
        String password
) {
}
