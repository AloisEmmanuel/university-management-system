package gradeManagemen;

public class GradeManager {

    public static void main(String[] args) {

        Student s1 = new Student();
        Instructor instructor = new Instructor();

        instructor.assignGrade(s1, new Grade("A", new LetterGradeScheme()));
        instructor.assignGrade(s1, new Grade(85.0, new NumericGradeScheme()));
        instructor.assignGrade(s1, new Grade("Pass", new PassFailScheme()));

        System.out.println("GPA: " + s1.calculateGPA());
    }
}
