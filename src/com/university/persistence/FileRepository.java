package com.university.persistence;

import com.university.users.User;
import com.university.courses.Course;
import com.university.enrollment.Enrollment;
import com.university.grades.Grade;

import java.io.*;
import java.util.*;

public class FileRepository implements DataRepository {
    
    private static final String DATA_DIR = "data/";
    private static final String USERS_FILE = DATA_DIR + "users.dat";
    private static final String COURSES_FILE = DATA_DIR + "courses.dat";
    private static final String ENROLLMENTS_FILE = DATA_DIR + "enrollments.dat";
    private static final String GRADES_FILE = DATA_DIR + "grades.dat";

    private Map<String, User> users;
    private Map<String, Course> courses;
    private List<Enrollment> enrollments;
    private List<Grade> grades;
    
    public FileRepository() {
   
        users = new HashMap<>();
        courses = new HashMap<>();
        enrollments = new ArrayList<>();
        grades = new ArrayList<>();

        SerializationHelper.createDirectory(DATA_DIR);
    }
    
    @Override
    public void saveUser(User user) {
        users.put(user.getId(), user);
        saveUsersToFile();
    }
    
    @Override
    public User getUserById(String userId) {
        return users.get(userId);
    }
    
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    

    
    @Override
    public void saveCourse(Course course) {
        courses.put(course.getCode(), course);
        saveCoursesToFile();
    }
    
    @Override
    public Course getCourseByCode(String courseCode) {
        return courses.get(courseCode);
    }
    
    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }
    
 
    
    @Override
    public void saveEnrollment(Enrollment enrollment) {
      
        enrollments.removeIf(e -> 
            e.getStudentId().equals(enrollment.getStudentId()) &&
            e.getCourseCode().equals(enrollment.getCourseCode()) &&
            e.getSemester().equals(enrollment.getSemester())
        );
        
        enrollments.add(enrollment);
        saveEnrollmentsToFile();
    }
    
    @Override
    public List<Enrollment> getEnrollmentsByStudent(String studentId) {
        List<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId().equals(studentId)) {
                studentEnrollments.add(enrollment);
            }
        }
        return studentEnrollments;
    }
    
  
    @Override
    public void saveGrade(Grade grade) {
  
        grades.removeIf(g -> 
            g.getStudentId().equals(grade.getStudentId()) &&
            g.getCourseCode().equals(grade.getCourseCode())
        );
        
        grades.add(grade);
        saveGradesToFile();
    }
    
    @Override
    public List<Grade> getGradesByStudent(String studentId) {
        List<Grade> studentGrades = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getStudentId().equals(studentId)) {
                studentGrades.add(grade);
            }
        }
        return studentGrades;
    }
    

    
    @Override
    public void saveAllData(Map<String, Object> allData) {
 
        if (allData.containsKey("users")) {
            users = (Map<String, User>) allData.get("users");
            saveUsersToFile();
        }
        
        if (allData.containsKey("courses")) {
            courses = (Map<String, Course>) allData.get("courses");
            saveCoursesToFile();
        }
        
        if (allData.containsKey("enrollments")) {
            enrollments = (List<Enrollment>) allData.get("enrollments");
            saveEnrollmentsToFile();
        }
        
        if (allData.containsKey("grades")) {
            grades = (List<Grade>) allData.get("grades");
            saveGradesToFile();
        }
        
        System.out.println("All data saved successfully.");
    }
    
    @Override
    public Map<String, Object> loadAllData() {
        Map<String, Object> allData = new HashMap<>();

        try {
            loadUsersFromFile();
            loadCoursesFromFile();
            loadEnrollmentsFromFile();
            loadGradesFromFile();
            
            allData.put("users", users);
            allData.put("courses", courses);
            allData.put("enrollments", enrollments);
            allData.put("grades", grades);
            
            System.out.println("Data loaded successfully:");
            System.out.println("- Users: " + users.size());
            System.out.println("- Courses: " + courses.size());
            System.out.println("- Enrollments: " + enrollments.size());
            System.out.println("- Grades: " + grades.size());
            
        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
        
        return allData;
    }
    

    
    private void saveUsersToFile() {
        try {
            SerializationHelper.saveToFile(users, USERS_FILE);
        } catch (Exception e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }
    
    private void loadUsersFromFile() throws IOException {
        if (SerializationHelper.fileExists(USERS_FILE)) {
            Object loaded = SerializationHelper.loadFromFile(USERS_FILE);
            if (loaded != null) {
                users = (Map<String, User>) loaded;
            }
        } else {
            System.out.println("Users file not found. Starting with empty users.");
        }
    }
    
    private void saveCoursesToFile() {
        try {
            SerializationHelper.saveToFile(courses, COURSES_FILE);
        } catch (Exception e) {
            System.err.println("Error saving courses: " + e.getMessage());
        }
    }
    
    private void loadCoursesFromFile() throws IOException {
        if (SerializationHelper.fileExists(COURSES_FILE)) {
            Object loaded = SerializationHelper.loadFromFile(COURSES_FILE);
            if (loaded != null) {
                courses = (Map<String, Course>) loaded;
            }
        } else {
            System.out.println("Courses file not found. Starting with empty courses.");
        }
    }
    
    private void saveEnrollmentsToFile() {
        try {
            SerializationHelper.saveToFile(enrollments, ENROLLMENTS_FILE);
        } catch (Exception e) {
            System.err.println("Error saving enrollments: " + e.getMessage());
        }
    }
    
    private void loadEnrollmentsFromFile() throws IOException {
        if (SerializationHelper.fileExists(ENROLLMENTS_FILE)) {
            Object loaded = SerializationHelper.loadFromFile(ENROLLMENTS_FILE);
            if (loaded != null) {
                enrollments = (List<Enrollment>) loaded;
            }
        } else {
            System.out.println("Enrollments file not found. Starting with empty enrollments.");
        }
    }
    
    private void saveGradesToFile() {
        try {
            SerializationHelper.saveToFile(grades, GRADES_FILE);
        } catch (Exception e) {
            System.err.println("Error saving grades: " + e.getMessage());
        }
    }
    
    private void loadGradesFromFile() throws IOException {
        if (SerializationHelper.fileExists(GRADES_FILE)) {
            Object loaded = SerializationHelper.loadFromFile(GRADES_FILE);
            if (loaded != null) {
                grades = (List<Grade>) loaded;
            }
        } else {
            System.out.println("Grades file not found. Starting with empty grades.");
        }
    }
    

    public void saveAllDataPeriodically() {
        System.out.println("Saving all data periodically...");
        saveUsersToFile();
        saveCoursesToFile();
        saveEnrollmentsToFile();
        saveGradesToFile();
        System.out.println("Periodic save completed.");
    }
    
    public void loadAllDataOnStartup() {
        System.out.println("Loading all data on startup...");
        loadAllData();
    }

    public void printDataStatistics() {
        System.out.println("\n=== Data Statistics ===");
        System.out.println("Users in memory: " + users.size());
        System.out.println("Courses in memory: " + courses.size());
        System.out.println("Enrollments in memory: " + enrollments.size());
        System.out.println("Grades in memory: " + grades.size());
    }

    public void clearAllData() {
        users.clear();
        courses.clear();
        enrollments.clear();
        grades.clear();

        deleteFile(USERS_FILE);
        deleteFile(COURSES_FILE);
        deleteFile(ENROLLMENTS_FILE);
        deleteFile(GRADES_FILE);
        
        System.out.println("All data cleared.");
    }
    
    private void deleteFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }
}