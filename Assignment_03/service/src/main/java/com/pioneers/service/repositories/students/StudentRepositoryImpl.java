package com.pioneers.service.repositories.students;

import com.pioneers.service.db.DbContainer;
import com.pioneers.service.error.exceptions.NotFoundException;
import com.pioneers.service.model.entities.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.pioneers.service.utils.time.TimeHelper.timeSinceMinutesAgo;

/**
 * Repository class for interacting with the InMemoryDb.
 *
 * @author abdelaziz
 * @see StudentRepository
 */
@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {
    private final DbContainer dbContainer;

    @Override
    public void save(final Student student) {
        dbContainer.getStudentsMap().put(student.getId(), student);
    }

    @Override
    public Collection<Student> findAll() {
        return dbContainer.getStudentsMap().values();
    }

    @Override
    public Optional<Student> findById(final String id) {
        return Optional.ofNullable(dbContainer.getStudentsMap().get(id));
    }

    @Override
    public Optional<Student> findByEmail(final String email) {
        return dbContainer.getStudentsMap()
                .values()
                .stream()
                .filter(student -> student.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public Optional<Student> findByPhone(String phone) {
        return dbContainer.getStudentsMap()
                .values()
                .stream()
                .filter(student -> phone.equals(student.getPhone()))
                .findFirst();
    }

    @Override
    public List<Student> findLastLoggedInWithMin(final int minutes) {
        return dbContainer.getStudentsMap()
                .values()
                .stream()
                .filter(Student::isLogin)
                .filter(student -> isStudentLoggedLastMin(student, minutes))
                .toList();
    }

    private static boolean isStudentLoggedLastMin(final Student student, final int minutes) {
        return timeSinceMinutesAgo(minutes).before(student.getLastLoginAt());
    }

    @Override
    public void deleteAll() {
        dbContainer.getStudentsMap().clear();
    }

    @Override
    public void deleteById(final String id) throws NotFoundException {
        if (!isStudentExist(id)) {
            throw new NotFoundException("Student with id:[" + id + "] not found");
        }

        removeById(id);
    }

    private void removeById(final String id) {
        dbContainer.getStudentsMap().remove(id);
    }

    private boolean isStudentExist(final String id) {
        return dbContainer.getStudentsMap().containsKey(id);
    }

    @Override
    public Optional<Student> findFirstRegistered() {
        return dbContainer.getStudentsMap().values()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Student> findLastLoggedIn() {
        return dbContainer.getStudentsMap()
                .values()
                .stream()
                .filter(Student::isLogin)
                .max(Comparator.comparing(Student::getLastLoginAt));
    }

    @Override
    public void update(final Student student) throws NotFoundException {
        if (!isStudentExist(student.getId())) {
            throw new NotFoundException("Student with id:[" + student.getId() + "] not found");
        }
        dbContainer.getStudentsMap().put(student.getId(), student);
    }
}
