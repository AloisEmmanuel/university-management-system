package courses;

public class TranscriptReport extends Report {

    private Student student;

    public TranscriptReport(Student student) {
        super("Transcript Report");
        this.student = student;
    }

    @Override
    public void generate() {
        System.out.println(getTitle());
        System.out.println("Student: " + student.getName());
        System.out.println("Courses and Grades:");

        student.getCourses().forEach((course, grade) -> {
            System.out.println("- " + course + ": " + grade);
        });

        System.out.println("-------------------------");
    }
}
