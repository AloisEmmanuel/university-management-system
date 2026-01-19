package com.university.courses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseManager {
    private Map<String, Course> courses;
    
    public CourseManager() {
        courses = new HashMap<>();
    }
    
    
    public boolean addCourse(Course course) {
        if (!courses.containsKey(course.getCode())) {
            courses.put(course.getCode(), course);
            return true;
        }
        return false;
    }
    
    public Course getCourseByCode(String code) {
        return courses.get(code);
    }
    
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }
    
    public List<Course> getAvailableCourses(String semester) {
       
        return getAllCourses();
    }
    
 
    public boolean updateCourse(String code, String title, int credits, 
                               String instructorId, int capacity) {
        Course course = getCourseByCode(code);
        if (course != null) {
            course.setTitle(title);
            course.setCredits(credits);
            course.setInstructorId(instructorId);
            course.setCapacity(capacity);
            return true;
        }
        return false;
    }
    
 
    public boolean removeCourse(String code) {
        return courses.remove(code) != null;
    }
    
  
    public boolean assignInstructor(String courseCode, String instructorId) {
        Course course = getCourseByCode(courseCode);
        if (course != null) {
            course.setInstructorId(instructorId);
            return true;
        }
        return false;
    }
    
    public boolean addPrerequisite(String courseCode, String prerequisiteCode) {
        Course course = getCourseByCode(courseCode);
        if (course != null) {
            course.addPrerequisite(prerequisiteCode);
            return true;
        }
        return false;
    }
    
    public boolean enrollStudent(String courseCode, String studentId) {
        Course course = getCourseByCode(courseCode);
        if (course != null && !course.isFull()) {
            return course.addStudent(studentId);
        }
        return false;
    }
    
    public boolean dropStudent(String courseCode, String studentId) {
        Course course = getCourseByCode(courseCode);
        if (course != null) {
            return course.removeStudent(studentId);
        }
        return false;
    }
    

    public boolean courseExists(String code) {
        return courses.containsKey(code);
    }
    
    public boolean hasAvailableSeats(String courseCode) {
        Course course = getCourseByCode(courseCode);
        return course != null && !course.isFull();
    }
    
    public List<String> getPrerequisites(String courseCode) {
        Course course = getCourseByCode(courseCode);
        return course != null ? course.getPrerequisites() : new ArrayList<>();
    }
    

    public List<Course> searchCoursesByName(String name) {
        List<Course> results = new ArrayList<>();
        String searchTerm = name.toLowerCase();
        
        for (Course course : courses.values()) {
            if (course.getTitle().toLowerCase().contains(searchTerm)) {
                results.add(course);
            }
        }
        return results;
    }
    
    public List<Course> getCoursesByInstructor(String instructorId) {
        List<Course> results = new ArrayList<>();
        
        for (Course course : courses.values()) {
            if (instructorId.equals(course.getInstructorId())) {
                results.add(course);
            }
        }
        return results;
    }
    

    public int getTotalCourseCount() {
        return courses.size();
    }
    
    public int getTotalEnrollmentCount() {
        int count = 0;
        for (Course course : courses.values()) {
            count += course.getEnrolledStudents().size();
        }
        return count;
    }
    
    public void printCourseStatistics() {
        System.out.println("=== Course Statistics ===");
        System.out.println("Total Courses: " + getTotalCourseCount());
        System.out.println("Total Enrollments: " + getTotalEnrollmentCount());
        
        int fullCourses = 0;
        for (Course course : courses.values()) {
            if (course.isFull()) {
                fullCourses++;
            }
        }
        System.out.println("Full Courses: " + fullCourses);
        System.out.println("Available Courses: " + (courses.size() - fullCourses));
    }
    

    public Map<String, Course> getAllCoursesMap() {
        return new HashMap<>(courses);
    }
    
    public void setAllCourses(Map<String, Course> courses) {
        this.courses = new HashMap<>(courses);
    }
}