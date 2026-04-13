package com.pioneers.service.model.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record StudentUpdate(
        @Size(min = 2, max = 15, message = "First Name must be between 2 and 15 characters")
        String firstName,
        @Size(min = 2, max = 15, message = "Second Name must be between 2 and 15 characters")
        String secondName,
        @Email(message = "Email must be valid")
        String email,
        @Pattern(regexp = "^\\+20(10|11|12|15)\\d{8}$", message = "The phone number format is incorrect")
        String phone
) {
}
