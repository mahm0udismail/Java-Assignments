package com.pioneers.service.repositories.students;

import com.pioneers.service.model.entities.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Contract to interact with the CRUD operations.
 *
 * @author abdelaziz
 */
public interface StudentRepository {

    /**
     * Save the student to the Repository.
     *
     * @param student the student need to be saved.
     */
    void save(Student student);

    /**
     * Find all students from the Repository.
     *
     * @return all students in the Repository.
     */
    Collection<Student> findAll();

    /**
     * Find a specific student by his ID.
     *
     * @param id is the primary key of the student.
     * @return the found student from the repository.
     */
    Optional<Student> findById(String id);

    /**
     * Find a specific student by his email.
     *
     * @param email is the email of the student.
     * @return the found student from the repository.
     */
    Optional<Student> findByEmail(String email);

    /**
     * Find a specific student by his phone number.
     *
     * @param phone is the phone of the student.
     * @return the found student from the repository.
     */
    Optional<Student> findByPhone(String phone);

    /**
     * Find all students who logged in the last 10 minutes ago.
     *
     * @return the found logged in students from the repository.
     */
    List<Student> findLastLoggedInWithMin(int minutes);

    /**
     * Delete all students from the repository.
     */
    void deleteAll();

    /**
     * Delete a specific student from the repository.
     *
     * @param id is the primary key of the student.
     */
    void deleteById(String id);

    /**
     * Find the first registered student in the repository.
     *
     * @return optional of the find registered student.
     */
    Optional<Student> findFirstRegistered();

    /**
     * Find the first student who logged into the system.
     *
     * @return optional of the first logged in student.
     */
    Optional<Student> findLastLoggedIn();

    /**
     * Update a student into the repository.
     *
     * @param student the student required to be updated.
     */
    void update(Student student);
}
