package com.university.courses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
  
	private static final long serialVersionUID = -6551107503060077324L;
	private String code;          
    private String title;          
    private int credits;           
    private String instructorId;   
    private int capacity;
    private List<String> prerequisites = new ArrayList<>();
    private List<String> enrolledStudents = new ArrayList<>(); 
    
    public Course(String code, String title, int credits, String instructorId, int capacity) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.instructorId = instructorId;
        this.capacity = capacity;
    }


    public String getCode() { return code; }          
    public String getTitle() { return title; }       
    public int getCredits() { return credits; }       
    public String getInstructorId() { 
    	return instructorId;
    	}
    public int getCapacity() { return capacity; }
    public List<String> getPrerequisites() { 
    	return new ArrayList<>(prerequisites);
    	}
    public List<String> getEnrolledStudents() { return new ArrayList<>(enrolledStudents); } 


    public void setTitle(String title) { this.title = title; } 
    public void setCredits(int credits) { this.credits = credits; } 
    public void setInstructorId(String instructorId) { this.instructorId = instructorId; } 
    public void setCapacity(int capacity) { this.capacity = capacity; }


    public void addPrerequisite(String courseCode) { 
        if (!prerequisites.contains(courseCode)) {
            prerequisites.add(courseCode);
        }
    }
    
    public void removePrerequisite(String courseCode) {
        prerequisites.remove(courseCode);
    }
    
    public boolean addStudent(String studentId) {
        if (enrolledStudents.size() < capacity && !enrolledStudents.contains(studentId)) {
            enrolledStudents.add(studentId);
            return true;
        }
        return false;
    }
    
    public boolean removeStudent(String studentId) {
        return enrolledStudents.remove(studentId);
    }
    
    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }
    
    public int getAvailableSeats() {
        return capacity - enrolledStudents.size();
    }
    
    public boolean hasPrerequisite(String courseCode) {
        return prerequisites.contains(courseCode);
    }
    
    public void clearPrerequisites() {
        prerequisites.clear();
    }
    
    @Override
    public String toString() {
        return "Course{" +
               "code='" + code + '\'' +
               ", title='" + title + '\'' +
               ", credits=" + credits +
               ", instructorId='" + instructorId + '\'' +
               ", capacity=" + capacity +
               ", enrolled=" + enrolledStudents.size() +
               ", prerequisites=" + prerequisites.size() +
               '}';
    }
}