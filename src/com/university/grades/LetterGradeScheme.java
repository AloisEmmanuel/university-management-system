package com.university.grades;

public class LetterGradeScheme implements GradingScheme {

    @Override
    public double calculateGradePoint(Object grade) {
        String letter = (String) grade;
        switch (letter.toUpperCase()) {
            case "A": return 4.0;
            case "B": return 3.0;
            case "C": return 2.0;
            case "D": return 1.0;
            default: return 0.0;
        }
    }
    
    @Override
    public String getGradeAsString(Object grade) {
        return (String) grade;
    }
    
    @Override
    public boolean isPassing(Object grade) {
        String letter = (String) grade;
        return !letter.equalsIgnoreCase("F");
    }
}