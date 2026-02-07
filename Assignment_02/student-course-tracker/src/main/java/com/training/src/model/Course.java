package com.training.src.model;

import java.util.Objects;

public class Course {
    private final String courseId;
    private final String courseName;
    private final String instructor;
    private final int credits;

    public Course(String courseId, String courseName, String instructor, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.credits = credits;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return credits == course.credits && Objects.equals(courseId, course.courseId) && Objects.equals(courseName, course.courseName) && Objects.equals(instructor, course.instructor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, instructor, credits);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", instructor='" + instructor + '\'' +
                ", credits=" + credits +
                '}';
    }
}
