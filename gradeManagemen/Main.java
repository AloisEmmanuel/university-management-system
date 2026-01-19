package gradeManagemen;

public class Main {

    public static void main(String[] args) {

        // Create instructor
        Instructor instructor = new Instructor();

        // Create students
        Student student1 = new Student();
        Student student2 = new Student();

        // Assign grades using different grading schemes
        instructor.assignGrade(
                student1,
                new Grade("A", new LetterGradeScheme())
        );

        instructor.assignGrade(
                student1,
                new Grade(78.0, new NumericGradeScheme())
        );

        instructor.assignGrade(
                student2,
                new Grade("Pass", new PassFailScheme())
        );

        instructor.assignGrade(
                student2,
                new Grade("B", new LetterGradeScheme())
        );

        // Display GPA
        System.out.println("Student: Ali");
        System.out.println("GPA: " + student1.calculateGPA());

        System.out.println();

        System.out.println("Student: Sara");
        System.out.println("GPA: " + student2.calculateGPA());
    }
}
