package com.university.grades;

public class PassFailScheme implements GradingScheme {

    @Override
    public double calculateGradePoint(Object grade) {
        String result = (String) grade;
        return result.equalsIgnoreCase("Pass") ? 4.0 : 0.0;
    }
    
    @Override
    public String getGradeAsString(Object grade) {
        return (String) grade;
    }
    
    @Override
    public boolean isPassing(Object grade) {
        String result = (String) grade;
        return result.equalsIgnoreCase("Pass");
    }
}