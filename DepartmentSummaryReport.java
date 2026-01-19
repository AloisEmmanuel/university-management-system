package courses;

public class DepartmentSummaryReport extends Report {

    private Department department;

    public DepartmentSummaryReport(Department department) {
        super("Department Summary Report");
        this.department = department;
    }

    @Override
    public void generate() {
        System.out.println(getTitle());
        System.out.println("Department: " + department.getName());
        System.out.println("Total Students: " + department.getStudents().size());
        System.out.println("Total Instructors: " + department.getInstructors().size());
        System.out.println("Total Courses: " + department.getCourses().size());
        System.out.println("-------------------------");
    }
}
