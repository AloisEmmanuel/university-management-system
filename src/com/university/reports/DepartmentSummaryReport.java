package com.university.reports;

import com.university.users.UserManager;
import com.university.courses.CourseManager;
import java.io.Serializable;
import java.util.List;

public class DepartmentSummaryReport extends Report implements Serializable {
    private static final long serialVersionUID = 1L; // ✅ ADDED
    
    private String department;
    private UserManager userManager;
    private CourseManager courseManager;

    public DepartmentSummaryReport(String department, UserManager userManager, 
                                   CourseManager courseManager) {
        super("Department Summary: " + department);
        this.department = department;
        this.userManager = userManager;
        this.courseManager = courseManager;
    }

    @Override
    public void generate() {
        System.out.println("\n" + getTitle());
        System.out.println("=========================================");
        System.out.println("Department: " + department);
        System.out.println("Report Date: " + new java.util.Date());
        System.out.println("=========================================");
        
 
        List<com.university.users.Student> students = userManager.getAllStudents();
        long deptStudents = students.stream()
            .filter(s -> s.getProgram() != null && s.getProgram().contains(department))
            .count();
        
        System.out.println("Student Statistics:");
        System.out.println("-------------------");
        System.out.println("Total Students: " + deptStudents);
        

        List<com.university.users.Instructor> instructors = userManager.getAllInstructors();
        long deptInstructors = instructors.stream()
            .filter(i -> i.getDepartment() != null && i.getDepartment().equals(department))
            .count();
        
        System.out.println("Total Instructors: " + deptInstructors);
        

        List<com.university.courses.Course> courses = courseManager.getAllCourses();
        long deptCourses = courses.stream()
            .filter(c -> c.getCode().startsWith(department.substring(0, 2).toUpperCase()))
            .count();
        
        System.out.println("Total Courses: " + deptCourses);
 
        int totalEnrollment = 0;
        for (com.university.courses.Course course : courses) {
            if (course.getCode().startsWith(department.substring(0, 2).toUpperCase())) {
                totalEnrollment += course.getEnrolledStudents().size();
            }
        }
        
        System.out.println("Total Enrollment: " + totalEnrollment);
        System.out.println("Average Class Size: " + 
                         (deptCourses > 0 ? 
                          String.format("%.1f", (double)totalEnrollment/deptCourses) : "0"));

        double avgGPA = students.stream()
            .filter(s -> s.getProgram() != null && s.getProgram().contains(department))
            .mapToDouble(s -> s.getGpa())
            .average()
            .orElse(0.0);
        
        System.out.println("Average Department GPA: " + String.format("%.2f", avgGPA));
        
     
    }
}