package com.training.src.service;

import com.training.src.model.Course;
import com.training.src.model.Student;
import com.training.src.repository.EnrollmentRepository;

import java.util.Set;

public class EnrollmentService {
    private final EnrollmentRepository repository;

    public EnrollmentService() {
        this.repository = new EnrollmentRepository();
    }

    public EnrollmentService(EnrollmentRepository repository) {
        this.repository = repository;
    }

    public void registerStudentForCourse(Student student, Course course) {
        if (student == null) {
            System.out.println("Error: Student cannot be null");
            return;
        }
        if (course == null) {
            System.out.println("Error: Course cannot be null");
            return;
        }
        if (repository.isStudentEnrolled(student, course)) {
            System.out.println("Info: Student " + student.getName() +
                    " is already enrolled in " + course.getCourseName());
            return;
        }
        repository.enrollStudent(student, course);
    }

    public void unregisterStudentFromCourse(Student student, Course course) {
        if (student == null) {
            System.out.println("Error: Student cannot be null");
            return;
        }
        if (course == null) {
            System.out.println("Error: Course cannot be null");
            return;
        }
        if (!repository.isStudentEnrolled(student, course)) {
            System.out.println("Info: Student " + student.getName() +
                    " is not enrolled in " + course.getCourseName());
            return;
        }
        repository.unenrollStudent(student, course);
    }

    public void displayStudentCourses(Student student) {
        if (student == null) {
            System.out.println("Error: Student cannot be null");
            return;
        }
        Set<Course> courses = repository.getCoursesForStudent(student);
        if (courses == null || courses.isEmpty()) {
            System.out.println("Info: Student " + student.getName() + " is not enrolled in any courses.");
            return;
        }
        System.out.println("Courses for student " + student.getName() + ":");
        courses.forEach(course -> System.out.println("- " + course.getCourseName() +
                " (" + course.getInstructor() + " instructor )"+
                " (" + course.getCredits() + " credits)"));
    }

    public void displayCourseStudents(Course course) {
        if (course == null) {
            System.out.println("Error: Course cannot be null");
            return;
        }
        Set<Student> students = repository.getStudentsInCourse(course);
        if (students == null || students.isEmpty()) {
            System.out.println("Info: No students are enrolled in " + course.getCourseName());
            return;
        }
        System.out.println("Students in course " + course.getCourseName() + ":");
        students.forEach(student -> System.out.println("- " + student.getName()
        + " (age: " + student.getAge() + ", email: " + student.getEmail() + ")"));
    }
}
