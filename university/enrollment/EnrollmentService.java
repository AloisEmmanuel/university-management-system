package com.university.enrollment;

import com.university.courses.Course;
import java.util.List;

public interface EnrollmentService {
    Enrollment enrollStudent(String studentId, String courseCode, String semester);
    boolean dropCourse(String studentId, String courseCode, String semester);
    EnrollmentStatus getEnrollmentStatus(String studentId, String courseCode, String semester);
    List<Course> getEnrolledCourses(String studentId, String semester);
    List<String> getEnrolledStudents(String courseCode, String semester);
}