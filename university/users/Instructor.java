package com.university.users;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {

    private String instructorId;
    private String department;
    private String officeHours;
    private List<String> coursesTaught;
    

    public Instructor(String id, String username, String password, String name, String email,
                      String instructorId, String department) {
        super(id, username, password, name, email);
        this.instructorId = instructorId;
        this.department = department;
        this.officeHours = "Not set";
        this.coursesTaught = new ArrayList<>();
    }
    
  
    @Override
    public void displayInfo() {
        System.out.println(" Instructor Information");
        System.out.println("Instructor ID: " + instructorId);
        System.out.println("Name: " + getName());
        System.out.println("Department: " + department);
        System.out.println("Office Hours: " + officeHours);
        System.out.println("Email: " + getEmail());
        System.out.println("Courses Teaching: " + coursesTaught.size());
    }
    

    @Override
    public String getRole() {
        return "INSTRUCTOR";
    }
    
 
    public void addCourse(String courseCode) {
        if (!coursesTaught.contains(courseCode)) {
            coursesTaught.add(courseCode);
            System.out.println("Course " + courseCode + " added to teaching schedule.");
        }
    }
    
  
    public void removeCourse(String courseCode) {
        if (coursesTaught.contains(courseCode)) {
            coursesTaught.remove(courseCode);
            System.out.println("Course " + courseCode + " removed from teaching schedule.");
        }
    }
    
    public String getInstructorId() {
        return instructorId;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public String getOfficeHours() {
        return officeHours;
    }
    
    public List<String> getCoursesTaught() {
        return new ArrayList<>(coursesTaught);
    }
    

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }
    
  
    public int getTeachingLoad() {
        return coursesTaught.size();
    }
}