package com.pioneers.service.services.auth;

import com.pioneers.service.error.exceptions.*;
import com.pioneers.service.model.dtos.requests.StudentLogin;
import com.pioneers.service.model.dtos.requests.StudentSignup;
import com.pioneers.service.model.entities.Student;
import com.pioneers.service.repositories.students.StudentRepository;
import com.pioneers.service.utils.CredentialsHelper;
import com.pioneers.service.utils.time.TimeHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import static com.pioneers.service.utils.CredentialsHelper.verifyPassword;
import static com.pioneers.service.utils.NameHelper.buildFullName;
import static com.pioneers.service.utils.mappers.StudentMapper.toStudent;

/**
 * Implement all authentication methods for our In-memory Db.
 *
 * @author abdelaziz
 * @see AuthService
 */
// TODO: Create all unit tests for all methods in that class.
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final StudentRepository studentRepository;

    @Override
    public String registerStudent(final StudentSignup studentSignup) throws CredentialsException {
        final String methodName = "registerStudent";
        studentRepository.findByEmail(studentSignup.email())
                .ifPresent(student -> throwRegisterException(methodName, "Email is already used in system"));
        studentRepository.findByPhone(studentSignup.phoneNumber())
                .ifPresent(student -> throwRegisterException(methodName, "Phone is already used in system"));

        final Student student = toStudent(studentSignup);
        log.debug("Converted studentSignup to studentSignup entity");

        studentRepository.save(student);
        log.debug("Successfully save student with id: {}", student.getId());
        return "Student registered successfully";
    }

    private static void throwRegisterException(final String methodName, final String errorMessage) {
        Object[] args = new Object[]{methodName, errorMessage};
        log.error("{}/ {}", args);
        throw new RegisterException(errorMessage);
    }

    @Override
    public String loginStudent(final StudentLogin studentLogin) {

        final String methodName = "loginStudent";
        Object[] args;

        final Student foundStudent = studentRepository.findByEmail(studentLogin.email())
                .orElseThrow(() -> new LoginException(
                        String.format("Student with email %s is not registered", studentLogin.email()))
                );

        if (foundStudent.isLogin()) {
            final String errorDetails = "Student with email: " + studentLogin.email() + " is already login";
            args = new Object[]{methodName, errorDetails};
            log.error("{}/ {}", args);

            throw new LoginException(errorDetails);
        }

        try {
            final boolean isPasswordMatched = CredentialsHelper.verifyPassword(studentLogin.password(), foundStudent.getPassword());
            log.debug(String.valueOf(isPasswordMatched));

            if (!isPasswordMatched) {
                args = new Object[]{methodName, "Password is incorrect"};
                log.error("{}/ {}", args);
                throw new LoginException("Email or password incorrect");
            }
        } catch (CredentialsException e) {
            throw new LoginException("Cannot hash the plain text password");
        }

        foundStudent.setLogin(true);
        foundStudent.setLastLoginAt(TimeHelper.currentTimestamp());
        return "Login successful";
    }

    @Override
    public void logoutStudent(final String id) {
        final Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id: " + id + " not found"));

        if (!student.isLogin()) {
            throw new LogoutException("Student with id: " + id + " is not login");
        }

        student.setLogin(false);
    }
}
