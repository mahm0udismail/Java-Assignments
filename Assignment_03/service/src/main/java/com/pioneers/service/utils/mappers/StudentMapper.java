package com.pioneers.service.utils.mappers;

import com.pioneers.service.error.exceptions.CredentialsException;
import com.pioneers.service.model.dtos.requests.StudentSignup;
import com.pioneers.service.model.entities.Student;

import java.security.NoSuchAlgorithmException;

import static com.pioneers.service.utils.CredentialsHelper.hashPassword;
import static com.pioneers.service.utils.NameHelper.buildFullName;
import static com.pioneers.service.utils.StringUtils.generateRandomId;

/**
 * Mapper class to transform to/from students.
 *
 * @author abdelaziz
 */
// TODO: Create all unit tests for all methods in that class.
public final class StudentMapper {

    private StudentMapper() {
        throw new AssertionError("Cannot instantiate the StudentMapper");
    }

    /**
     * Trasfor the StudentSignup to a Student object.
     *
     * @param studentSignup is the target request need to transform from it.
     * @return a new student object from the request.
     * @throws CredentialsException is the returned exception during the hashing password process
     */
    public static Student toStudent(final StudentSignup studentSignup) throws CredentialsException {
        final String id = generateRandomId();
        final String fullName = buildFullName(studentSignup.firstName(), studentSignup.lastName());
        final String hashedPassword = hashPassword(studentSignup.password());

        return Student.builder()
                .id(id)
                .fullName(fullName)
                .email(studentSignup.email())
                .phone(studentSignup.phoneNumber())
                .password(hashedPassword)
                .isLogin(false)
                .build();
    }
}
