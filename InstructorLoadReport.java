package courses;

public class InstructorLoadReport extends Report {

    private Instructor instructor;

    public InstructorLoadReport(Instructor instructor) {
        super("Instructor Teaching Load Report");
        this.instructor = instructor;
    }

    @Override
    public void generate() {
        System.out.println(getTitle());
        System.out.println("Instructor: " + instructor.getName());
        System.out.println("Courses Taught:");

        for (Course c : instructor.getCourses()) {
            System.out.println("- " + c.getName());
        }

        System.out.println("-------------------------");
    }
}
