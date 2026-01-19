package com.university.grades;

import com.university.notifications.NotificationManager;
import java.util.*;

public class GradeManager {
    private List<Grade> grades;
    private NotificationManager notificationManager;
    
    public GradeManager(NotificationManager notificationManager) {
        this.grades = new ArrayList<>();
        this.notificationManager = notificationManager;
    }
    

    public boolean assignGrade(String studentId, String courseCode, Object gradeValue, 
                               String schemeType, String semester) {
        GradingScheme scheme = createGradingScheme(schemeType);
        
        if (scheme == null) {
            System.out.println("Invalid grading scheme: " + schemeType);
            return false;
        }
 
        grades.removeIf(g -> 
            g.getStudentId().equals(studentId) && 
            g.getCourseCode().equals(courseCode)
        );

        Grade grade = new Grade(studentId, courseCode, gradeValue, scheme, semester);
        grades.add(grade);

        if (notificationManager != null) {
            String gradeString = scheme.getGradeAsString(gradeValue);
            notificationManager.sendGradeNotification(studentId, courseCode, gradeString);
        }
        
        System.out.println("Grade assigned: " + studentId + " - " + courseCode + 
                         " = " + gradeValue + " (" + schemeType + ")");
        return true;
    }
 
    public List<Grade> getGradesByStudent(String studentId) {
        List<Grade> studentGrades = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getStudentId().equals(studentId)) {
                studentGrades.add(grade);
            }
        }
        return studentGrades;
    }

    public List<Grade> getGradesByCourse(String courseCode) {
        List<Grade> courseGrades = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getCourseCode().equals(courseCode)) {
                courseGrades.add(grade);
            }
        }
        return courseGrades;
    }

    public Grade getGrade(String studentId, String courseCode) {
        for (Grade grade : grades) {
            if (grade.getStudentId().equals(studentId) && 
                grade.getCourseCode().equals(courseCode)) {
                return grade;
            }
        }
        return null;
    }
    
    public double calculateGPA(String studentId) {
        List<Grade> studentGrades = getGradesByStudent(studentId);
        
        if (studentGrades.isEmpty()) {
            return 0.0;
        }
        
        double totalPoints = 0.0;
        int count = 0;
        
        for (Grade grade : studentGrades) {
            if (grade.isPassing()) {
                totalPoints += grade.getGradePoint();
                count++;
            }
        }
        
        return count > 0 ? totalPoints / count : 0.0;
    }
    
    public boolean hasPassedCourse(String studentId, String courseCode) {
        Grade grade = getGrade(studentId, courseCode);
        return grade != null && grade.isPassing();
    }

    private GradingScheme createGradingScheme(String type) {
        switch (type.toUpperCase()) {
            case "LETTER":
            case "LETTERGRADE":
                return new LetterGradeScheme();
            case "NUMERIC":
            case "NUMERICAL":
                return new NumericGradeScheme();
            case "PASSFAIL":
                return new PassFailScheme();
            default:
                return null;
        }
    }
 
    public List<Grade> getAllGrades() {
        return new ArrayList<>(grades);
    }
    
    public void setAllGrades(List<Grade> grades) {
        this.grades = new ArrayList<>(grades);
    }
    

    public void printGradeStatistics() {
        System.out.println("=== Grade Statistics ===");
        System.out.println("Total grades: " + grades.size());
        
        Map<String, Integer> schemeCount = new HashMap<>();
        for (Grade grade : grades) {
            String schemeName = grade.getScheme().getClass().getSimpleName();
            schemeCount.put(schemeName, schemeCount.getOrDefault(schemeName, 0) + 1);
        }
        
        System.out.println("Grading schemes used:");
        for (Map.Entry<String, Integer> entry : schemeCount.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }
}