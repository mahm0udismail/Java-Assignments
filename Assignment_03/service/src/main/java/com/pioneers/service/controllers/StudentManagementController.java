package com.pioneers.service.controllers;

import com.pioneers.service.error.exceptions.NotFoundException;
import com.pioneers.service.model.dtos.requests.StudentUpdate;
import com.pioneers.service.model.dtos.responses.StudentInfo;
import com.pioneers.service.services.students.StudentManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A Handler to accept the APIs requested related to student management from the client side.
 *
 * @author abdelaziz
 */
@RestController
@RequestMapping("studentManagement")
@RequiredArgsConstructor
public class StudentManagementController {

    private final StudentManagementService studentManagementService;

    /**
     * Find all students API.
     *
     * @return all students from our system.
     */
    @GetMapping("findAll")
    public List<StudentInfo> findAllApi() {
        return studentManagementService.findAll();
    }

    /**
     * Find the first registered student in our system.
     *
     * @return the first found registered student in our system.
     * @throws NotFoundException occurred when student is not found.
     */
    @GetMapping("firstRegisteredStudent")
    public StudentInfo findFirstRegisteredStudentApi() throws NotFoundException {
        return studentManagementService.findFirstRegisteredStudent();
    }

    /**
     * Find the first LoggedIn student in our system.
     *
     * @return the first found LoggedIn student in our system.
     * @throws NotFoundException occurred when student is not found.
     */
    @GetMapping("lastLoggedInStudent")
    public StudentInfo findLastLoggedInStudentApi() throws NotFoundException {
        return studentManagementService.findLastLoginStudent();
    }

    @GetMapping("lastLoggedInByMin/{minutes}")
    public List<StudentInfo> findLastLoggedInStudentWith10MinApi(@PathVariable int minutes) throws NotFoundException {
        return studentManagementService.findLastLoginStudentsByMinute(minutes);
    }

    /**
     * Deletes all students from our system.
     *
     * @return status of delete operation.
     */
    @DeleteMapping("deleteAll")
    public String deleteAllStudentsApi() {
        studentManagementService.deleteAll();
        return "All students deleted successfully";
    }

    /**
     * Delete particular student by the unique identifier.
     *
     * @param id is the primary key for the student.
     * @return status of delete operation.
     * @throws NotFoundException occurred when student is not found.
     */
    @DeleteMapping("delete")
    public ResponseEntity<String> deleteStudentByIdApi(@RequestParam String id) throws NotFoundException {
        studentManagementService.delete(id);

        return ResponseEntity.ok("Student with id " + id + " deleted successfully");
    }

    /**
     * Update particulat student in our system.
     *
     * @param id             is the primary key for the student.
     * @param updatedStudent includes the information need to be updated for the specific student.
     * @return status of update operation.
     * @throws NotFoundException occurred when student is not found.
     */
    @PutMapping("update/{id}")
    public String updateStudentApi(@Valid @PathVariable String id, @RequestBody StudentUpdate updatedStudent)
            throws NotFoundException {

        studentManagementService.update(id, updatedStudent);
        return "Student updated successfully";
    }
}
