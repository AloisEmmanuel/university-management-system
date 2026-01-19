package com.university.reports;

import com.university.courses.Course;
import com.university.enrollment.EnrollmentManager;
import java.io.Serializable;
import java.util.List;

public class CourseRosterReport extends Report implements Serializable {
    private static final long serialVersionUID = 1L; // ✅ ADDED
    
    private Course course;
    private EnrollmentManager enrollmentManager;

    public CourseRosterReport(Course course, EnrollmentManager enrollmentManager) {
        super("Course Roster: " + course.getTitle());
        this.course = course;
        this.enrollmentManager = enrollmentManager;
    }

    @Override
    public void generate() {
        System.out.println(" " + getTitle());
        System.out.println("Course Code: " + course.getCode());
        System.out.println("Course Title: " + course.getTitle());
        System.out.println("Instructor ID: " + course.getInstructorId());
        System.out.println("Credits: " + course.getCredits());
        System.out.println("Capacity: " + course.getCapacity() + " students");
        System.out.println("Currently Enrolled: " + course.getEnrolledStudents().size() + " students");
        System.out.println("Available Seats: " + course.getAvailableSeats());

        System.out.println("Enrolled Students:");
        System.out.println("------------------");
        

        List<String> students = enrollmentManager.getEnrolledStudents(
            course.getCode(), "Fall2024");
        
        if (students.isEmpty()) {
            System.out.println("No students enrolled in this course.");
        } else {
            for (int i = 0; i < students.size(); i++) {
                System.out.println((i + 1) + ". " + students.get(i));
            }
        }
        
    }
}