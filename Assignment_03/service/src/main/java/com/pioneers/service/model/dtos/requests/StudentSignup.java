package com.pioneers.service.model.dtos.requests;

import jakarta.validation.constraints.*;

public record StudentSignup(
        @Size(min = 2, max = 15, message = "First Name must be between 2 and 15 characters")
        String firstName,
        @Size(min = 2, max = 15, message = "Last Name must be between 2 and 15 characters")
        String lastName,
        @Email(message = "Email must be valid")
        String email,
        @Min(value = 18, message = "Age must be at least 18 years old")
        @Max(value = 25, message = "Age must be at most 25 years old")
        int age,
        @Pattern(regexp = "^\\+20(10|11|12|15)\\d{8}$", message = "The phone number format is incorrect")
        String phoneNumber,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$", message = "Password doesn't meet our criteria")
        String password
) {
}
