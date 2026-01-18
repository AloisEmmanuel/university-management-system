package com.university.users;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {

    private String studentId;
    private String program;
    private int year;
    private double gpa;
    private List<String> completedCourses;
    

    public Student(String id, String username, String password, String name, String email,
                   String studentId, String program, int year) {
        super(id, username, password, name, email);
        this.studentId = studentId;
        this.program = program;
        this.year = year;
        this.gpa = 0.0;
        this.completedCourses = new ArrayList<>();
    }
    

    @Override
    public void displayInfo() {
        System.out.println("Student Information");
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + getName());
        System.out.println("Program: " + program);
        System.out.println("Year: " + year);
        System.out.println("GPA: " + String.format("%.2f", gpa));
        System.out.println("Email: " + getEmail());
        System.out.println("Completed Courses: " + completedCourses.size());
    }
    
 
    @Override
    public String getRole() {
        return "STUDENT";
    }
    
 
    public void calculateGPA(List<Double> grades) {
        if (grades == null || grades.isEmpty()) {
            gpa = 0.0;
            return;
        }
        
        double total = 0.0;
        for (Double grade : grades) {
            total += grade;
        }
        gpa = total / grades.size();
        
   
        if (gpa > 4.0 && gpa <= 100.0) {
            if (gpa >= 90) gpa = 4.0;
            else if (gpa >= 80) gpa = 3.0;
            else if (gpa >= 70) gpa = 2.0;
            else if (gpa >= 60) gpa = 1.0;
            else gpa = 0.0;
        }
    }
    

    public void addCompletedCourse(String courseCode) {
        if (!completedCourses.contains(courseCode)) {
            completedCourses.add(courseCode);
            System.out.println("Course " + courseCode + " added to completed courses.");
        }
    }
    
  
    public String getStudentId() {
        return studentId;
    }
    
    public String getProgram() {
        return program;
    }
    
    public int getYear() {
        return year;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    public List<String> getCompletedCourses() {
        return new ArrayList<>(completedCourses);
    }
    
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public void setProgram(String program) {
        this.program = program;
    }
    
    public void setYear(int year) {
        if (year >= 1 && year <= 5) {
            this.year = year;
        }
    }
    
    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        }
    }
}