package com.training.src;


import com.training.src.model.Course;
import com.training.src.model.Student;
import com.training.src.service.EnrollmentService;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        EnrollmentService service = new EnrollmentService();

        // Create students
        Student alice = new Student("S001", 20, "Alice.com");
        Student bob = new Student("S002", 25, "bob@email.com");
        Student charlie = new Student("S003", 22, "charlie@email.com");

        // Create courses
        Course java = new Course("CS101", "Java Programming", "Dr. Smith", 3);
        Course python = new Course("CS102", "Python for Data Science", "Dr. Johnson", 4);
        Course webDev = new Course("CS201", "Web Development", "Prof. Lee", 3);
        Course database = new Course("CS202", "Database Systems", "Dr. Martinez", 4);
        Course algorithms = new Course("CS301", "Algorithms", "Prof. Chen", 4);
        Course ai = new Course("CS401", "Artificial Intelligence", "Dr. Kumar", 4);

        // Enroll students in courses
        System.out.println(">>> ENROLLING STUDENTS IN COURSES <<<\n");

        // Alice - most courses (5)
        service.registerStudentForCourse(alice, java);
        service.registerStudentForCourse(alice, python);
        service.registerStudentForCourse(alice, webDev);
        service.registerStudentForCourse(alice, database);
        service.registerStudentForCourse(alice, algorithms);

        // Display Alice's courses
        service.displayStudentCourses(alice);

        // Bob - 3 courses
        service.registerStudentForCourse(bob, java);
        service.registerStudentForCourse(bob, webDev);
        service.registerStudentForCourse(bob, ai);

        // Display bob's courses
        service.displayStudentCourses(bob);

        // Charlie - 2 courses
        service.registerStudentForCourse(charlie, python);
        service.registerStudentForCourse(charlie, database);

        // Display charlie's courses
        service.displayStudentCourses(charlie);

        // Test duplicate enrollment
        System.out.println("duplicate enrollment test:");
        service.registerStudentForCourse(alice, java);

        // Display students in a course
        System.out.println("display students in a course:");
        service.displayCourseStudents(java);
        service.displayCourseStudents(python);

    }
}