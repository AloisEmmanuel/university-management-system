package com.university.grades;

public class NumericGradeScheme implements GradingScheme {

    @Override
    public double calculateGradePoint(Object grade) {
        double score = (double) grade;

        if (score >= 90) return 4.0;
        if (score >= 80) return 3.0;
        if (score >= 70) return 2.0;
        if (score >= 60) return 1.0;
        return 0.0;
    }
    
    @Override
    public String getGradeAsString(Object grade) {
        double score = (double) grade;
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }
    
    @Override
    public boolean isPassing(Object grade) {
        double score = (double) grade;
        return score >= 60;
    }
}