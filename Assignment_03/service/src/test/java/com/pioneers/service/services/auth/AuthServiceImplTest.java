package com.pioneers.service.services.auth;

import com.pioneers.service.error.exceptions.CredentialsException;
import com.pioneers.service.error.exceptions.LoginException;
import com.pioneers.service.error.exceptions.RegisterException;
import com.pioneers.service.model.dtos.requests.StudentLogin;
import com.pioneers.service.model.dtos.requests.StudentSignup;
import com.pioneers.service.model.entities.Student;
import com.pioneers.service.repositories.students.StudentRepository;
import com.pioneers.service.utils.CredentialsHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    // ---------------- RegisterStudent ----------------
    // ---------------- SUCCESS CASE ----------------

    @Test
    void testRegisterStudentWhenEmailAndPhoneNotExistThenStudentRegisteredSuccessfully() {

        StudentSignup signup = new StudentSignup(
                "Mahmoud",
                "Ahmed",
                "test@test.com",
                20,
                "+201001234567",
                "Password@123"
        );

        when(studentRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.empty());

        when(studentRepository.findByPhone("+201001234567"))
                .thenReturn(Optional.empty());

        doNothing().when(studentRepository).save(any(Student.class));

        String result = authService.registerStudent(signup);

        assertEquals("Student registered successfully", result);

        verify(studentRepository).findByEmail("test@test.com");
        verify(studentRepository).findByPhone("+201001234567");
        verify(studentRepository).save(any(Student.class));
    }

    // ---------------- EMAIL EXISTS ----------------
    @Test
    void testRegisterStudentWhenEmailAlreadyExistsThenThrowRegisterException() {

        StudentSignup signup = new StudentSignup(
                "Mahmoud",
                "Ahmed",
                "test@test.com",
                20,
                "+201001234567",
                "Password@123"
        );

        when(studentRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(mock(Student.class)));

        RegisterException ex = assertThrows(
                RegisterException.class,
                () -> authService.registerStudent(signup)
        );

        assertNotNull(ex);
        verify(studentRepository, never()).save(any());
    }

    // ---------------- PHONE EXISTS ----------------
    @Test
    void testRegisterStudentWhenPhoneAlreadyExistsThenThrowRegisterException() {

        StudentSignup signup = new StudentSignup(
                "Mahmoud",
                "Ahmed",
                "test@test.com",
                20,
                "+201001234567",
                "Password@123"
        );

        when(studentRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.empty());

        when(studentRepository.findByPhone("+201001234567"))
                .thenReturn(Optional.of(mock(Student.class)));

        assertThrows(
                RegisterException.class,
                () -> authService.registerStudent(signup)
        );

        verify(studentRepository, never()).save(any());
    }

    // ---------------- loginStudent ----------------
    // ---------------- SUCCESS CASE ----------------
    @Test
    void testLoginStudentWhenValidCredentialsThenLoginSuccessful(){
        StudentLogin studentLogin = new StudentLogin(
                "test@test.com",
                "Password@123"
        );
        Student student = Student.builder()
                .id("12345")
                .fullName("Mahmoud Ahmed")
                .email("test@test.com")
                .phone("+201001234567")
                .password("hashedPassword123")
                .isLogin(false)
                .lastLoginAt(new Timestamp(System.currentTimeMillis()))
                .build();

        when(studentRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(student));

        try (MockedStatic<CredentialsHelper> mocked = mockStatic(CredentialsHelper.class)) {

            mocked.when(() ->
                    CredentialsHelper.verifyPassword("Password@123", "hashedPassword123")
            ).thenReturn(true);

            String result = authService.loginStudent(studentLogin);

            assertEquals("Login successful", result);
        }

    }
}