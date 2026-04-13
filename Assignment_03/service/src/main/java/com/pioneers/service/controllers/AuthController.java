package com.pioneers.service.controllers;

import com.pioneers.service.error.exceptions.CredentialsException;
import com.pioneers.service.model.dtos.requests.StudentLogin;
import com.pioneers.service.model.dtos.requests.StudentSignup;
import com.pioneers.service.services.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

/**
 * Contains APIs for Authentications for all users in our system.
 *
 * @author abdelaziz
 */
@Slf4j
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * Register a student to our system.
     *
     * @param studentSignup DTO that includes the information to sign up the student.
     * @return Messages indicating the validation status or request completion.
     */
    @PostMapping("register")
    public String registerStudentApi(@Valid @RequestBody final StudentSignup studentSignup)
            throws CredentialsException {
        return authService.registerStudent(studentSignup);
    }

    /**
     * Login a student into our system.
     *
     * @param studentLogin DTO that includes the information to log in the student.
     * @return Messages indicating the validation status or request completion.
     */
    @PostMapping("login")
    public String loginApi(@Valid @RequestBody final StudentLogin studentLogin) {
        return authService.loginStudent(studentLogin);
    }

    /**
     * Logout a student from our system.
     *
     * @param id The unique identifier of the student to log out.
     */
    @PatchMapping("logout/{id}")
    public void logoutApi(@PathVariable final String id) {
        authService.logoutStudent(id);
    }
}
