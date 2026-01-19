package gradeManagemen;

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
}
