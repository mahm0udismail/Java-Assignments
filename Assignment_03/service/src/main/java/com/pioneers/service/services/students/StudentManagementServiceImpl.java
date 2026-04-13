package com.pioneers.service.services.students;

import com.pioneers.service.error.exceptions.NotFoundException;
import com.pioneers.service.model.dtos.requests.StudentUpdate;
import com.pioneers.service.model.dtos.responses.StudentInfo;
import com.pioneers.service.model.entities.Student;
import com.pioneers.service.repositories.students.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pioneers.service.utils.NameHelper.buildFullName;

/**
 * Implements all management methods for the student flows.
 *
 * @author abdelaziz
 */
// TODO: Create all unit tests for all methods in that class.
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentManagementServiceImpl implements StudentManagementService {

    private final StudentRepository studentRepository;

    private static StudentInfo toStudentInfo(final Student student) {
        return new StudentInfo(student.getFullName(), student.getEmail(), student.getPhone());
    }

    @Override
    public List<StudentInfo> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(StudentManagementServiceImpl::toStudentInfo)
                .toList();
    }

    @Override
    public StudentInfo findFirstRegisteredStudent() {
        return studentRepository.findFirstRegistered()
                .map(student -> new StudentInfo(student.getFullName(), student.getEmail(), student.getPhone()))
                .orElseThrow(() -> new NotFoundException("There is no registered student"));
    }

    @Override
    public List<StudentInfo> findLastLoginStudentsByMinute(final int minutes) {
        return studentRepository.findLastLoggedInWithMin(minutes)
                .stream()
                .map(StudentManagementServiceImpl::toStudentInfo)
                .toList();
    }

    @Override
    public StudentInfo findLastLoginStudent() {
        final Student student = studentRepository.findLastLoggedIn()
                .orElseThrow(() -> new NotFoundException("There is no logged-in student"));

        return toStudentInfo(student);
    }

    @Override
    public void deleteAll() {
        studentRepository.deleteAll();
    }

    @Override
    public void delete(final String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void update(final String id, final StudentUpdate studentUpdate) {
        final Student foundStudent = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Student with id %s not found", id)));

        final String fullName = buildFullName(studentUpdate.firstName(), studentUpdate.secondName());

        foundStudent.setFullName(fullName);
        foundStudent.setEmail(studentUpdate.email());
        foundStudent.setPhone(studentUpdate.phone());

        log.info("Successfully updated student with name = [{}], email = [{}], phone = [{}] ",
                fullName, foundStudent.getEmail(), foundStudent.getPhone());

        studentRepository.update(foundStudent);
    }
}
