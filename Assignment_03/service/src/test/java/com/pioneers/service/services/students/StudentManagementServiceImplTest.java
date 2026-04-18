package com.pioneers.service.services.students;

import com.pioneers.service.error.exceptions.NotFoundException;
import com.pioneers.service.model.dtos.requests.StudentUpdate;
import com.pioneers.service.model.dtos.responses.StudentInfo;
import com.pioneers.service.model.entities.Student;
import com.pioneers.service.repositories.students.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentManagementServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentManagementServiceImpl studentManagementService;

    // Shared test fixtures
    private Student student;
    private StudentInfo expectedStudentInfo;

    @BeforeEach
    void setUp() {
        student = Student.builder()
                .id("12345")
                .fullName("Mahmoud Ismail")
                .email("test@test.com")
                .phone("+201001234567")
                .password("hashedPassword123")
                .isLogin(false)
                .build();

        expectedStudentInfo = new StudentInfo(
                "Mahmoud Ismail",
                "test@test.com",
                "+201001234567"
        );
    }

    // ================ findAll ================
    // ---------------- SUCCESS CASE ----------------

    @Test
    void testFindAllWhenStudentsExistThenReturnStudentInfoList() {
        when(studentRepository.findAll())
                .thenReturn(List.of(student));

        List<StudentInfo> result = studentManagementService.findAll();

        assertEquals(1, result.size());
        assertEquals(expectedStudentInfo, result.getFirst());
        verify(studentRepository).findAll();
    }

    // ---------------- EMPTY LIST ----------------

    @Test
    void testFindAllWhenNoStudentsExistThenReturnEmptyList() {
        when(studentRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<StudentInfo> result = studentManagementService.findAll();

        assertTrue(result.isEmpty());
        verify(studentRepository).findAll();
    }

    // ================ findFirstRegisteredStudent ================
    // ---------------- SUCCESS CASE ----------------

    @Test
    void testFindFirstRegisteredStudentWhenExistsThenReturnStudentInfo() {
        when(studentRepository.findFirstRegistered())
                .thenReturn(Optional.of(student));

        StudentInfo result = studentManagementService.findFirstRegisteredStudent();

        assertEquals(expectedStudentInfo, result);
        verify(studentRepository).findFirstRegistered();
    }

    // ---------------- NOT FOUND ----------------

    @Test
    void testFindFirstRegisteredStudentWhenNotExistsThenThrowNotFoundException() {
        when(studentRepository.findFirstRegistered())
                .thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> studentManagementService.findFirstRegisteredStudent()
        );

        assertEquals("There is no registered student", exception.getMessage());
        verify(studentRepository).findFirstRegistered();
    }

    // ================ findLastLoginStudentsByMinute ================
    // ---------------- SUCCESS CASE ----------------

    @Test
    void testFindLastLoginStudentsByMinuteWhenStudentsFoundThenReturnList() {
        when(studentRepository.findLastLoggedInWithMin(10)).thenReturn(List.of(student));

        List<StudentInfo> result = studentManagementService.findLastLoginStudentsByMinute(10);

        assertEquals(1, result.size());
        assertEquals(expectedStudentInfo, result.getFirst());
        verify(studentRepository).findLastLoggedInWithMin(10);
    }

    // ---------------- EMPTY LIST ----------------

    @Test
    void testFindLastLoginStudentsByMinuteWhenNoStudentsFoundThenReturnEmptyList() {
        when(studentRepository.findLastLoggedInWithMin(10))
                .thenReturn(Collections.emptyList());

        List<StudentInfo> result = studentManagementService.findLastLoginStudentsByMinute(10);

        assertTrue(result.isEmpty());
        verify(studentRepository).findLastLoggedInWithMin(10);
    }

    // ================ findLastLoginStudent ================
    // ---------------- SUCCESS CASE ----------------

    @Test
    void testFindLastLoginStudentWhenExistsThenReturnStudentInfo() {
        when(studentRepository.findLastLoggedIn())
                .thenReturn(Optional.of(student));

        StudentInfo result = studentManagementService.findLastLoginStudent();

        assertEquals(expectedStudentInfo, result);
        verify(studentRepository).findLastLoggedIn();
    }

    // ---------------- NOT FOUND ----------------

    @Test
    void testFindLastLoginStudentWhenNotExistsThenThrowNotFoundException() {
        when(studentRepository.findLastLoggedIn())
                .thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> studentManagementService.findLastLoginStudent()
        );

        assertEquals("There is no logged-in student", exception.getMessage());
        verify(studentRepository).findLastLoggedIn();
    }

    // ================ deleteAll ================

    @Test
    void testDeleteAllWhenCalledThenRepositoryDeleteAllInvoked() {
        doNothing().when(studentRepository).deleteAll();

        studentManagementService.deleteAll();

        verify(studentRepository).deleteAll();
    }

    // ================ delete ================
    // ---------------- SUCCESS CASE ----------------

    @Test
    void testDeleteWhenValidIdThenRepositoryDeleteByIdInvoked() {
        String studentId = "12345";
        doNothing().when(studentRepository).deleteById(studentId);

        studentManagementService.delete(studentId);

        verify(studentRepository).deleteById(studentId);
    }

    // ================ update ================
    // ---------------- SUCCESS CASE ----------------

    @Test
    void testUpdateWhenValidIdThenStudentUpdatedSuccessfully() {
        StudentUpdate studentUpdate = new StudentUpdate(
                "Ahmed",
                "Ali",
                "ahmed@test.com",
                "+201109876543"
        );

        when(studentRepository.findById("12345"))
                .thenReturn(Optional.of(student));
        doNothing().when(studentRepository).update(any(Student.class));

        studentManagementService.update("12345", studentUpdate);

        // Verify side effects on the student object
        assertEquals("Ahmed Ali", student.getFullName());
        assertEquals("ahmed@test.com", student.getEmail());
        assertEquals("+201109876543", student.getPhone());

        verify(studentRepository).findById("12345");
        verify(studentRepository).update(student);
    }

    // ---------------- NOT FOUND ----------------

    @Test
    void testUpdateWhenStudentNotFoundThenThrowNotFoundException() {
        String studentId = "99999";
        StudentUpdate studentUpdate = new StudentUpdate(
                "Ahmed",
                "Ali",
                "ahmed@test.com",
                "+201109876543"
        );

        when(studentRepository.findById(studentId))
                .thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> studentManagementService.update(studentId, studentUpdate)
        );

        assertEquals("Student with id 99999 not found", exception.getMessage());

        verify(studentRepository).findById(studentId);
        verify(studentRepository, never()).update(any());
    }
}
