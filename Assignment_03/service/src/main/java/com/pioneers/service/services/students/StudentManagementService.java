package com.pioneers.service.services.students;

import com.pioneers.service.error.exceptions.NotFoundException;
import com.pioneers.service.model.dtos.requests.StudentUpdate;
import com.pioneers.service.model.dtos.responses.StudentInfo;

import java.util.List;

/**
 * Includes management methods for the student flows.
 *
 * @author abdelaziz
 */
public interface StudentManagementService {

    /**
     * Find all student from the repository.
     *
     * @return all found students from the repository.
     */
    List<StudentInfo> findAll();

    /**
     * Fetch the first registered student in our repository.
     *
     * @return the first registered student information message or a default message if not listed in the repository.
     */
    StudentInfo findFirstRegisteredStudent() throws NotFoundException;

    List<StudentInfo> findLastLoginStudentsByMinute(int minutes);

    /**
     * Fetch the first student who logged into the system.
     *
     * @return the first login student information.
     */
    StudentInfo findLastLoginStudent();

    /**
     * Delete all students from the repository.
     */
    void deleteAll();

    /**
     * Delete particular student by his ID.
     *
     * @param id is the primary key of the student.
     */
    void delete(String id);

    /**
     * Update or insert student in our repository.
     *
     * @param id            is the primary key of the student.
     * @param studentUpdate DTO class sent from the UI.
     * @throws NotFoundException if the student is not found in our system.
     */
    void update(final String id, final StudentUpdate studentUpdate) throws NotFoundException;
}
