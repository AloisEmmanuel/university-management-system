package gradeManagemen;

public class Grade {
    private Object value;
    private GradingScheme scheme;

    public Grade(Object value, GradingScheme scheme) {
        this.value = value;
        this.scheme = scheme;
    }

    public double getGradePoint() {
        return scheme.calculateGradePoint(value);
    }
}
