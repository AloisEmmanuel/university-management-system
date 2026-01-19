package com.university.grades;

public interface GradingScheme {
    double calculateGradePoint(Object grade);

    String getGradeAsString(Object grade);

    boolean isPassing(Object grade);
}