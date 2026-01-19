package courses;

import java.util.List;
import java.util.Map;

public class Main2 {
    public static void main(String[] args) {

        // ----- Create Students -----
        Student s1 = new Student("Alice", Map.of(
                "Math", "A",
                "English", "B"
        ));

        Student s2 = new Student("Bob", Map.of(
                "Math", "C"
        ));

        // ----- Create Course -----
        Course math = new Course("Math", List.of(s1, s2));

        // ----- Create Instructor -----
        Instructor i1 = new Instructor("Dr. John", List.of(math));

        // ----- Create Department -----
        Department cs = new Department(
                "Computer Science",
                List.of(s1, s2),
                List.of(i1),
                List.of(math)
        );

        // ----- Generate Reports -----
        Report r1 = new TranscriptReport(s1);
        Report r2 = new CourseRosterReport(math);
        Report r3 = new InstructorLoadReport(i1);
        Report r4 = new DepartmentSummaryReport(cs);

        r1.generate();
        r2.generate();
        r3.generate();
        r4.generate();
    }
}
