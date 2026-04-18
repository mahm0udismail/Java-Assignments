package com.pioneers.service.services.auth;

import com.pioneers.service.error.exceptions.LoginException;
import com.pioneers.service.error.exceptions.LogoutException;
import com.pioneers.service.error.exceptions.NotFoundException;
import com.pioneers.service.error.exceptions.RegisterException;
import com.pioneers.service.model.dtos.requests.StudentLogin;
import com.pioneers.service.model.dtos.requests.StudentSignup;
import com.pioneers.service.model.entities.Student;
import com.pioneers.service.repositories.students.StudentRepository;
import com.pioneers.service.utils.CredentialsHelper;
import com.pioneers.service.utils.time.TimeHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    private StudentSignup signup;
    private Student loggedOutStudent;
    private Student loggedInStudent;

    @BeforeEach
    void setUp() {
        signup = new StudentSignup(
                "Mahmoud",
                "Ismail",
                "test@test.com",
                20,
                "+201001234567",
                "Password@123"
        );

        loggedOutStudent = Student.builder()
                .id("12345")
                .fullName("Mahmoud Ismail")
                .email("test@test.com")
                .phone("+201001234567")
                .password("hashedPassword123")
                .isLogin(false)
                .lastLoginAt(null)
                .build();

        loggedInStudent = Student.builder()
                .id("12345")
                .fullName("Mahmoud Ismail")
                .email("test@test.com")
                .phone("+201001234567")
                .password("hashedPassword123")
                .isLogin(true)
                .lastLoginAt(TimeHelper.currentTimestamp())
                .build();
    }

    // ---------------- RegisterStudent ----------------
    // ---------------- SUCCESS CASE ----------------

    @Test
    void testRegisterStudentWhenEmailAndPhoneNotExistThenStudentRegisteredSuccessfully() {

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

        when(studentRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(mock(Student.class)));

        RegisterException ex = assertThrows(
                RegisterException.class,
                () -> authService.registerStudent(signup)
        );

        assertEquals("Email is already used in system", ex.getMessage());
        verify(studentRepository).findByEmail("test@test.com");
        verify(studentRepository, never()).save(any());
    }

    // ---------------- PHONE EXISTS ----------------
    @Test
    void testRegisterStudentWhenPhoneAlreadyExistsThenThrowRegisterException() {

        when(studentRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.empty());

        when(studentRepository.findByPhone("+201001234567"))
                .thenReturn(Optional.of(mock(Student.class)));

        RegisterException ex = assertThrows(
                RegisterException.class,
                () -> authService.registerStudent(signup)
        );

        assertEquals("Phone is already used in system", ex.getMessage());
        verify(studentRepository).findByEmail("test@test.com");
        verify(studentRepository).findByPhone("+201001234567");
        verify(studentRepository, never()).save(any());
    }

    // ================ loginStudent ================
    // ---------------- SUCCESS CASE ----------------
    @Test
    void testLoginStudentWhenValidCredentialsThenLoginSuccessful() {
        StudentLogin studentLogin = new StudentLogin("test@test.com", "Password@123");

        when(studentRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(loggedOutStudent));

        try (MockedStatic<CredentialsHelper> mocked = mockStatic(CredentialsHelper.class)) {
            mocked.when(() ->
                    CredentialsHelper.verifyPassword("Password@123", "hashedPassword123")
            ).thenReturn(true);

            String result = authService.loginStudent(studentLogin);

            assertEquals("Login successful", result);
            assertTrue(loggedOutStudent.isLogin());
            assertNotNull(loggedOutStudent.getLastLoginAt());
            verify(studentRepository).findByEmail("test@test.com");
        }
    }

    // ---------------- EMAIL NOT REGISTERED ----------------

    @Test
    void testLoginStudentWhenEmailNotRegisteredThrowsLoginException() {
        StudentLogin studentLogin = new StudentLogin("notfound@test.com", "Password@123");

        when(studentRepository.findByEmail("notfound@test.com"))
                .thenReturn(Optional.empty());

        LoginException exception = assertThrows(
                LoginException.class,
                () -> authService.loginStudent(studentLogin)
        );

        assertEquals("Student with email notfound@test.com is not registered", exception.getMessage());
        verify(studentRepository).findByEmail("notfound@test.com");
    }

    // ---------------- PASSWORD INCORRECT ----------------

    @Test
    void testLoginStudentWhenPasswordIncorrectThrowsLoginException() {
        StudentLogin studentLogin = new StudentLogin("test@test.com", "WrongPassword@123");

        when(studentRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(loggedOutStudent));

        try (MockedStatic<CredentialsHelper> mocked = mockStatic(CredentialsHelper.class)) {
            mocked.when(() ->
                    CredentialsHelper.verifyPassword(
                            "WrongPassword@123",
                            "hashedPassword123"
                    )
            ).thenReturn(false);

            LoginException exception = assertThrows(
                    LoginException.class,
                    () -> authService.loginStudent(studentLogin)
            );

            assertEquals("Email or password incorrect", exception.getMessage());
            verify(studentRepository).findByEmail("test@test.com");
        }
    }

    // ---------------- ALREADY LOGGED IN ----------------

    @Test
    void testLoginStudentWhenAlreadyLoggedInThenThrowsLoginException() {
        StudentLogin studentLogin = new StudentLogin("test@test.com", "Password@123");

        when(studentRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(loggedInStudent));

        LoginException exception = assertThrows(
                LoginException.class,
                () -> authService.loginStudent(studentLogin)
        );

        assertEquals("Student with email: test@test.com is already login", exception.getMessage());
        verify(studentRepository).findByEmail("test@test.com");
    }

    // ================ logoutStudent ================
    // ---------------- SUCCESS CASE ----------------

    @Test
    void testLogoutStudentWhenValidIdThenLogoutSuccessful() {
        String studentId = "12345";

        when(studentRepository.findById(studentId))
                .thenReturn(Optional.of(loggedInStudent));

        authService.logoutStudent(studentId);

        assertFalse(loggedInStudent.isLogin());
        verify(studentRepository).findById(studentId);
    }

    // ---------------- STUDENT NOT FOUND ----------------

    @Test
    void testLogoutStudentWhenStudentNotFoundThrowsNotFoundException() {
        String studentId = "99999";

        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> authService.logoutStudent(studentId)
        );

        assertEquals("Student with id: 99999 not found", exception.getMessage());
        verify(studentRepository).findById(studentId);
    }

    // ---------------- ALREADY LOGGED OUT ----------------

    @Test
    void testLogoutStudentWhenAlreadyLoggedOutThrowsLogoutException() {
        String studentId = "12345";

        when(studentRepository.findById(studentId))
                .thenReturn(Optional.of(loggedOutStudent));

        LogoutException exception = assertThrows(
                LogoutException.class,
                () -> authService.logoutStudent(studentId)
        );

        assertEquals("Student with id: 12345 is not login", exception.getMessage());
        verify(studentRepository).findById(studentId);
    }
}
