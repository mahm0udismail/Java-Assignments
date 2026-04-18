package com.pioneers.service.services.utils.mappers;

import com.pioneers.service.error.exceptions.CredentialsException;
import com.pioneers.service.model.dtos.requests.StudentSignup;
import com.pioneers.service.model.entities.Student;
import com.pioneers.service.utils.mappers.StudentMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    private final StudentSignup signup = new StudentSignup(
            "Mahmoud",
            "Ismail",
            "test@test.com",
            20,
            "+201001234567",
            "Password@123"
    );

    @Test
    void testToStudentWhenValidSignupThenIdIsGenerated() throws CredentialsException {
        Student student = StudentMapper.toStudent(signup);
        assertNotNull(student.getId());
    }

    @Test
    void testToStudentWhenValidSignupThenReturnsStudentNotNull() throws CredentialsException {
        final var student = StudentMapper.toStudent(signup);
        assertNotNull(student);
    }

    @Test
    void testToStudentWhenValidSignupThenFullNameIsCorrect() throws CredentialsException {
        Student student = StudentMapper.toStudent(signup);
        assertEquals("Mahmoud Ismail", student.getFullName());
    }

    @Test
    void testToStudentWhenValidSignupThenEmailMatches() throws CredentialsException {
        Student student = StudentMapper.toStudent(signup);
        assertEquals("test@test.com", student.getEmail());
    }

    @Test
    void testToStudentWhenValidSignupThenPhoneMatches() throws CredentialsException {
        Student student = StudentMapper.toStudent(signup);
        assertEquals("+201001234567", student.getPhone());
    }

    @Test
    void testToStudentWhenValidSignupThenPasswordIsHashed() throws CredentialsException {
        Student student = StudentMapper.toStudent(signup);
        assertNotEquals("Password@123", student.getPassword());
        assertNotNull(student.getPassword());
    }

    @Test
    void testToStudentWhenValidSignupThenIsLoginIsFalse() throws CredentialsException {
        Student student = StudentMapper.toStudent(signup);
        assertFalse(student.isLogin());
    }
}
