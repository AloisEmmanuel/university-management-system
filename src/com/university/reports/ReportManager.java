package com.university.reports;

import com.university.users.*;
import com.university.courses.*;
import com.university.enrollment.*;
import com.university.grades.*;
import java.util.*;

public class ReportManager {
    private UserManager userManager;
    private CourseManager courseManager;
    private EnrollmentManager enrollmentManager;
    private GradeManager gradeManager;
    
    public ReportManager(UserManager userManager, CourseManager courseManager,
                        EnrollmentManager enrollmentManager, GradeManager gradeManager) {
        this.userManager = userManager;
        this.courseManager = courseManager;
        this.enrollmentManager = enrollmentManager;
        this.gradeManager = gradeManager;
    }
    
    public void generateTranscript(String studentId) {
        Student student = (Student) userManager.getUserById(studentId);
        if (student != null) {
            TranscriptReport report = new TranscriptReport(student, gradeManager, courseManager);
            report.generate();
        } else {
            System.out.println("Student not found: " + studentId);
        }
    }

    public void generateTranscript(Student student) {
        TranscriptReport report = new TranscriptReport(student, gradeManager, courseManager);
        report.generate();
    }

    public void generateCourseRoster(String courseCode) {
        Course course = courseManager.getCourseByCode(courseCode);
        if (course != null) {
            CourseRosterReport report = new CourseRosterReport(course, enrollmentManager);
            report.generate();
        } else {
            System.out.println("Course not found: " + courseCode);
        }
    }

    public void generateTeachingLoad(String instructorId) {
        Instructor instructor = (Instructor) userManager.getUserById(instructorId);
        if (instructor != null) {
            TeachingLoadReport report = new TeachingLoadReport(instructor, courseManager);
            report.generate();
        } else {
            System.out.println("Instructor not found: " + instructorId);
        }
    }
    
    public void generateDepartmentSummary(String department) {
        DepartmentSummaryReport report = new DepartmentSummaryReport(
            department, userManager, courseManager);
        report.generate();
    }
    
    public void generateSystemReport() {
        System.out.println("SYSTEM WIDE REPORT");
        System.out.println("Generated: " + new Date());
        
        System.out.println("User Statistics:");
        System.out.println("- Total Users: " + userManager.getAllUsers().size());
        System.out.println("- Students: " + userManager.getAllStudents().size());
        System.out.println("- Instructors: " + userManager.getAllInstructors().size());
        System.out.println("- Admins: " + userManager.getAllAdmins().size());
        
        System.out.println("\nCourse Statistics:");
        System.out.println("- Total Courses: " + courseManager.getAllCourses().size());
        System.out.println("- Total Enrollment: " + courseManager.getTotalEnrollmentCount());
        
        System.out.println("\nGrade Statistics:");
        gradeManager.printGradeStatistics();
    }

    public void listReportTypes() {
        System.out.println("AVAILABLE REPORTS");
        System.out.println("1. Student Transcript");
        System.out.println("2. Course Roster");
        System.out.println("3. Instructor Teaching Load");
        System.out.println("4. Department Summary");
        System.out.println("5. System Report");
    }
}