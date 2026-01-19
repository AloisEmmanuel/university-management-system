package com.university.grades;

import java.io.Serializable;

public class Grade implements Serializable {
    private static final long serialVersionUID = 1L; 
    
    private String studentId;       
    private String courseCode;      
    private Object value;
    private GradingScheme scheme;
    private String semester;        
    
    public Grade(String studentId, String courseCode, Object value, 
                 GradingScheme scheme, String semester) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.value = value;
        this.scheme = scheme;
        this.semester = semester;
    }
    

    public Grade(Object value, GradingScheme scheme) {
        this(null, null, value, scheme, null);
    }

    public double getGradePoint() {
        return scheme.calculateGradePoint(value);
    }

    public String getStudentId() {
        return studentId;
    }
    
    public String getCourseCode() {
        return courseCode;
    }
    
    public Object getValue() {
        return value;
    }
    
    public GradingScheme getScheme() {
        return scheme;
    }
    
    public String getSemester() {
        return semester;
    }
    
    public String getGradeAsString() {
        return scheme.getGradeAsString(value);
    }
    
    public boolean isPassing() {
        return scheme.isPassing(value);
    }
    
    @Override
    public String toString() {
        return "Grade{" +
               "studentId='" + studentId + '\'' +
               ", courseCode='" + courseCode + '\'' +
               ", value=" + value +
               ", scheme=" + scheme.getClass().getSimpleName() +
               ", gradePoint=" + getGradePoint() +
               ", semester='" + semester + '\'' +
               '}';
    }
}