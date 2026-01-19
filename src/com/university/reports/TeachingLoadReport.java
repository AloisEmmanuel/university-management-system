package com.university.reports;

import com.university.users.Instructor;
import com.university.courses.CourseManager;
import java.io.Serializable;
import java.util.List;

public class TeachingLoadReport extends Report implements Serializable {
    private static final long serialVersionUID = 1L; 
    
    private Instructor instructor;
    private CourseManager courseManager;

    public TeachingLoadReport(Instructor instructor, CourseManager courseManager) {
        super("Teaching Load Report: " + instructor.getName());
        this.instructor = instructor;
        this.courseManager = courseManager;
    }

    @Override
    public void generate() {
        System.out.println("\n" + getTitle());
        System.out.println("=========================================");
        System.out.println("Instructor ID: " + instructor.getInstructorId());
        System.out.println("Name: " + instructor.getName());
        System.out.println("Department: " + instructor.getDepartment());
        System.out.println("Email: " + instructor.getEmail());
        System.out.println("Office Hours: " + instructor.getOfficeHours());
        System.out.println("=========================================");
        System.out.println("Courses Currently Teaching:");
        System.out.println("---------------------------");
        
        List<String> coursesTaught = instructor.getCoursesTaught();
        
        if (coursesTaught.isEmpty()) {
            System.out.println("Not currently teaching any courses.");
        } else {
            int totalStudents = 0;
            for (String courseCode : coursesTaught) {
                com.university.courses.Course course = courseManager.getCourseByCode(courseCode);
                if (course != null) {
                    int enrolled = course.getEnrolledStudents().size();
                    totalStudents += enrolled;
                    System.out.printf("- %s: %s (%d students)\n", 
                                    courseCode, course.getTitle(), enrolled);
                } else {
                    System.out.printf("- %s: Course not found in system\n", courseCode);
                }
            }
            
            System.out.println("\nSummary:");
            System.out.println("--------");
            System.out.println("Total Courses: " + coursesTaught.size());
            System.out.println("Total Students: " + totalStudents);
            System.out.println("Average per Course: " + 
                             (coursesTaught.size() > 0 ? 
                              String.format("%.1f", (double)totalStudents/coursesTaught.size()) : "0"));
        }
        
    }
}