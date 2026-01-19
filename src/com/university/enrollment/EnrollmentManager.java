package com.university.enrollment;

import com.university.courses.*;
import com.university.notifications.*;
import com.university.grades.*;
import java.util.*;

public class EnrollmentManager implements EnrollmentService {
    private CourseManager courseManager;
    private GradeManager gradeManager;
    private NotificationManager notificationManager;
    private List<Enrollment> enrollments;
    private static final int MAX_CREDITS = 18;
    
    public EnrollmentManager(CourseManager courseManager, GradeManager gradeManager, 
                           NotificationManager notificationManager) {
        this.courseManager = courseManager;
        this.gradeManager = gradeManager;
        this.notificationManager = notificationManager;
        this.enrollments = new ArrayList<>();
    }
    
    @Override
    public Enrollment enrollStudent(String studentId, String courseCode, String semester) {
        try {
           

        	validateEnrollment(studentId, courseCode, semester);
            
            Course course = courseManager.getCourseByCode(courseCode);
            if (course == null) {
                throw new EnrollmentException("Course not found: " + courseCode);
            }
            
           
            if (course.isFull()) {
                throw new EnrollmentException("Course " + courseCode + " is full");
            }
            
          
            if (!checkPrerequisites(studentId, course)) {
                throw new EnrollmentException("Prerequisites not met for " + courseCode);
            }
            
            if (getCurrentCredits(studentId, semester) + course.getCredits() > MAX_CREDITS) {
                throw new EnrollmentException("Credit limit exceeded. Maximum: " + MAX_CREDITS);
            }
            
       
            if (isAlreadyEnrolled(studentId, courseCode, semester)) {
                throw new EnrollmentException("Already enrolled in " + courseCode);
            }
            
      
            Enrollment enrollment = new Enrollment(
                generateEnrollmentId(),
                studentId,
                courseCode,
                semester,
                EnrollmentStatus.ACTIVE,
                new Date()
            );
            

            course.addStudent(studentId);
            
 
            enrollments.add(enrollment);
            
     
            notificationManager.sendEnrollmentNotification(studentId, courseCode, semester);
            
            System.out.println("Enrollment successful!");
            return enrollment;
            
        } catch (Exception e) {
            System.out.println("Enrollment failed: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public boolean dropCourse(String studentId, String courseCode, String semester) {
        try {
      
            Enrollment enrollment = findEnrollment(studentId, courseCode, semester);
            
            if (enrollment == null) {
                throw new EnrollmentException("Enrollment not found");
            }
            
            if (enrollment.getStatus() != EnrollmentStatus.ACTIVE) {
                throw new EnrollmentException("Cannot drop non-active enrollment");
            }
            
 
            enrollment.setStatus(EnrollmentStatus.DROPPED);
            enrollment.setDropDate(new Date());
            
         
            Course course = courseManager.getCourseByCode(courseCode);
            if (course != null) {
                course.removeStudent(studentId);
            }
            
       
            notificationManager.sendDropNotification(studentId, courseCode, semester);
            
            System.out.println("Course drop successful!");
            return true;
            
        } catch (Exception e) {
            System.out.println("Drop failed: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public EnrollmentStatus getEnrollmentStatus(String studentId, String courseCode, String semester) {
        Enrollment enrollment = findEnrollment(studentId, courseCode, semester);
        return (enrollment != null) ? enrollment.getStatus() : null;
    }
    
    @Override
    public List<Course> getEnrolledCourses(String studentId, String semester) {
        List<Course> courses = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId().equals(studentId) && 
                enrollment.getSemester().equals(semester) && 
                enrollment.getStatus() == EnrollmentStatus.ACTIVE) {
                Course course = courseManager.getCourseByCode(enrollment.getCourseCode());
                if (course != null) {
                    courses.add(course);
                }
            }
        }
        return courses;
    }
    
    @Override
    public List<String> getEnrolledStudents(String courseCode, String semester) {
        List<String> students = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseCode().equals(courseCode) && 
                enrollment.getSemester().equals(semester) && 
                enrollment.getStatus() == EnrollmentStatus.ACTIVE) {
                students.add(enrollment.getStudentId());
            }
        }
        return students;
    }
    

    private void validateEnrollment(String studentId, String courseCode, String semester) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID is required");
        }
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code is required");
        }
        if (semester == null || semester.trim().isEmpty()) {
            throw new IllegalArgumentException("Semester is required");
        }
    }
    
    private boolean checkPrerequisites(String studentId, Course course) {
        List<String> prerequisites = course.getPrerequisites();
        if (prerequisites == null || prerequisites.isEmpty()) {
            return true; // No prerequisites
        }
        
        for (String prereq : prerequisites) {
            if (!gradeManager.hasPassedCourse(studentId, prereq)) {
                return false;
            }
        }
        return true;
    }
    
    private int getCurrentCredits(String studentId, String semester) {
        int totalCredits = 0;
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId().equals(studentId) && 
                enrollment.getSemester().equals(semester) && 
                enrollment.getStatus() == EnrollmentStatus.ACTIVE) {
                Course course = courseManager.getCourseByCode(enrollment.getCourseCode());
                if (course != null) {
                    totalCredits += course.getCredits();
                }
            }
        }
        return totalCredits;
    }
    
    private boolean isAlreadyEnrolled(String studentId, String courseCode, String semester) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId().equals(studentId) && 
                enrollment.getCourseCode().equals(courseCode) && 
                enrollment.getSemester().equals(semester) && 
                enrollment.getStatus() == EnrollmentStatus.ACTIVE) {
                return true;
            }
        }
        return false;
    }
    
    private Enrollment findEnrollment(String studentId, String courseCode, String semester) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId().equals(studentId) && 
                enrollment.getCourseCode().equals(courseCode) && 
                enrollment.getSemester().equals(semester)) {
                return enrollment;
            }
        }
        return null;
    }
    
    private String generateEnrollmentId() {
        return "ENR" + System.currentTimeMillis() + (int)(Math.random() * 1000);
    }
    
    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }
    
    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}