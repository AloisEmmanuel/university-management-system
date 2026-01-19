package courses;


public class CourseRosterReport extends Report {

    private Course course;

    public CourseRosterReport(Course course) {
        super("Course Roster Report");
        this.course = course;
    }

    @Override
    public void generate() {
        System.out.println(getTitle());
        System.out.println("Course: " + course.getName());
        System.out.println("Enrolled Students:");

        for (Student s : course.getStudents()) {
            System.out.println("- " + s.getName());
        }

        System.out.println("-------------------------");
    }
}
