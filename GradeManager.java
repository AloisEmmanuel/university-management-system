package projecgrade;

import java.util.ArrayList;
import java.util.List;

public class GradeManager {

    private List<Grade> grades;
    private NotificationService notificationService;
    private GradeCalculator calculator;

    public GradeManager(NotificationService notificationService, GradeCalculator calculator) {
        this.grades = new ArrayList<>();
        this.notificationService = notificationService;
        this.calculator = calculator;
    }

    public void assignGrade(Grade grade) {
        String letter = calculator.calculateLetterGrade(grade.getScore());
        grade.setLetterGrade(letter);

        grades.add(grade);

        notificationService.notifyStudent(
                grade.getStudentId(),
                "New grade posted for course " + grade.getCourseCode() +
                ": " + letter
        );
    }

    public double calculateGPA() {
        double totalPoints = 0;
        int count = 0;

        for (Grade grade : grades) {
            double points = calculator.calculateGradePoint(grade.getScore());
            if (points > 0) {
                totalPoints += points;
                count++;
            }
        }
        return count == 0 ? 0.0 : totalPoints / count;
    }

    public List<Grade> getGrades() {
        return grades;
    }
}
