package com.university.reports;

import com.university.users.Student;
import com.university.grades.Grade;
import com.university.grades.GradeManager;
import com.university.courses.Course;
import com.university.courses.CourseManager;
import java.io.Serializable;
import java.util.List;

public class TranscriptReport extends Report implements Serializable {
    private static final long serialVersionUID = 1L; // ✅ ADDED
    
    private Student student;
    private GradeManager gradeManager;
    private CourseManager courseManager;

    public TranscriptReport(Student student, GradeManager gradeManager, CourseManager courseManager) {
        super("Transcript Report for " + student.getName());
        this.student = student;
        this.gradeManager = gradeManager;
        this.courseManager = courseManager;
    }

    @Override
    public void generate() {
        System.out.println("\n" + getTitle());
        System.out.println("=========================================");
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Name: " + student.getName());
        System.out.println("Program: " + student.getProgram());
        System.out.println("Year: " + student.getYear());
        System.out.println("Current GPA: " + String.format("%.2f", student.getGpa()));
        System.out.println("=========================================");
        System.out.printf("%-12s %-30s %-8s %-10s %-8s\n", 
                         "Course Code", "Course Title", "Credits", "Grade", "Points");
        System.out.println("-------------------------------------------------------------------");

        List<Grade> grades = gradeManager.getGradesByStudent(student.getStudentId());
        
        int totalCreditsAttempted = 0;
        int totalCreditsEarned = 0;
        double totalQualityPoints = 0.0;
        
        if (grades.isEmpty()) {
            System.out.println("No grades recorded yet.");
        } else {
            for (Grade grade : grades) {

                Course course = courseManager.getCourseByCode(grade.getCourseCode());
                int credits = (course != null) ? course.getCredits() : 3;
                String courseTitle = (course != null) ? course.getTitle() : "Course Not Found";
                
                // Calculate quality points
                double gradePoints = grade.getGradePoint();
                double qualityPoints = credits * gradePoints;
                

                totalCreditsAttempted += credits;
                if (grade.isPassing()) {
                    totalCreditsEarned += credits;
                    totalQualityPoints += qualityPoints;
                }
                
                System.out.printf("%-12s %-30s %-8d %-10s %-8.1f\n",
                                grade.getCourseCode(),
                                courseTitle,
                                credits,
                                grade.getGradeAsString(),
                                gradePoints);
            }
        }
        
  
        int completedCreditsByGrades = totalCreditsEarned;

        double calculatedGPA = (totalCreditsAttempted > 0) ? 
                              totalQualityPoints / totalCreditsAttempted : 0.0;
        
        System.out.println("Summary:");
        System.out.println("  Credits Attempted: " + totalCreditsAttempted);
        System.out.println("  Credits Earned: " + completedCreditsByGrades);
        System.out.println("  Quality Points: " + String.format("%.1f", totalQualityPoints));
        System.out.println("  Calculated GPA: " + String.format("%.2f", calculatedGPA));
        System.out.println("  Stored GPA: " + String.format("%.2f", student.getGpa()));
        
        System.out.println("=========================================\n");
    }
}