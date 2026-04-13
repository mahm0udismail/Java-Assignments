package com.pioneers.service.services.auth;

import com.pioneers.service.error.exceptions.*;
import com.pioneers.service.model.dtos.requests.StudentLogin;
import com.pioneers.service.model.dtos.requests.StudentSignup;

import java.security.NoSuchAlgorithmException;

/**
 * Include the contracts for all authentication services.
 *
 * @author abdelaziz
 */
public interface AuthService {

    /**
     * Sign up a student into our system.
     *
     * @param studentSignup DTO that includes the information to sign up the student.
     * @return Messages indicating the validation status or request completion.
     */
    String registerStudent(StudentSignup studentSignup) throws CredentialsException, RegisterException;

    /**
     * Log in a student into our system.
     *
     * @param studentLogin DTO that includes the information to log in the student.
     * @return Messages indicating the validation status or request completion.
     * @throws NotFoundException if the student is not found in our system.
     */
    String loginStudent(StudentLogin studentLogin) throws CredentialsException, LoginException;

    /**
     * Log out a student from our system.
     *
     * @param id The unique identifier of the student to log out.
     * @throws NotFoundException if the student if not found in our system.
     * @throws LogoutException   if the flow failed to log out the student.
     */
    void logoutStudent(String id) throws NotFoundException, LogoutException;
}
