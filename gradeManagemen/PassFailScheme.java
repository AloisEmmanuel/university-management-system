package gradeManagemen;
public class PassFailScheme implements GradingScheme {

    @Override
    public double calculateGradePoint(Object grade) {
        String result = (String) grade;
        return result.equalsIgnoreCase("Pass") ? 4.0 : 0.0;
    }
}
