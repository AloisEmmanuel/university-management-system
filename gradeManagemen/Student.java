package gradeManagemen;

public class Student {

    public static void main(String[] args) {

        Student s1 = new Student();
        Instructor instructor = new Instructor();

        instructor.assignGrade(s1, new Grade(85.0, new NumericGradeScheme()));
        instructor.assignGrade(s1, new Grade("Pass", new PassFailScheme()));

        System.out.println("GPA: " + s1.calculateGPA());
    }

	String calculateGPA() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addGrade(Grade grade) {
		// TODO Auto-generated method stub
		
	}
}
