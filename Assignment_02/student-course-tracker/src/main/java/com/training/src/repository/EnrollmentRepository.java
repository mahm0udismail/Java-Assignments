package com.training.src.repository;

import com.training.src.model.Course;
import com.training.src.model.Student;

import java.util.*;

public class EnrollmentRepository {
    private final Map<Student, Set<Course>> studentCourses;
    private final Map<Course, Set<Student>> courseStudents;

    public EnrollmentRepository() {
        this.studentCourses = new HashMap<>();
        this.courseStudents = new HashMap<>();
    }

    public EnrollmentRepository(Map<Student, Set<Course>> studentCourses, Map<Course, Set<Student>> courseStudents) {
        this.studentCourses = studentCourses;
        this.courseStudents = courseStudents;
    }

    // Enroll a student in a course
    public boolean enrollStudent(Student student, Course course) {
        studentCourses.computeIfAbsent(student, k -> new HashSet<>()).add(course);
        courseStudents.computeIfAbsent(course, k -> new HashSet<>()).add(student);
        return true;
    }

    // Unenroll a student from a course
    public boolean unenrollStudent(Student student, Course course) {
        if (studentCourses.containsKey(student) && studentCourses.get(student).contains(course)) {
            studentCourses.get(student).remove(course);
            courseStudents.get(course).remove(student);
            return true;
        }
        return false;
    }

    public Set<Course> getCoursesForStudent(Student student) {
        return Optional.ofNullable(studentCourses.get(student))
                .orElse(null);
    }

    public Set<Student> getStudentsInCourse(Course course){
        return Optional.ofNullable(courseStudents.get(course))
                .orElse(null);
    }

    public int getCourseCountForStudent(Student student) {
        return Optional.ofNullable(studentCourses.get(student))
                .map(Set::size)
                .orElse(0);
    }

    public int getStudentCountInCourse(Course course) {
        return Optional.ofNullable(courseStudents.get(course))
                .map(Set::size)
                .orElse(0);
    }

    public Set<Student> getAllStudents() {
        return studentCourses.keySet();
    }

    public Set<Course> getAllCourses(){
        return courseStudents.keySet();
    }

    public int getTotalEnrolledStudents() {
        return studentCourses.size();
    }

    public int getTotalCourses() {
        return courseStudents.size();
    }

    public boolean isStudentEnrolled(Student student, Course course) {
        return studentCourses.containsKey(student) && studentCourses.get(student).contains(course);
    }
}
