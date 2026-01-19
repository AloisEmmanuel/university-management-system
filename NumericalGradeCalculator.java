package projecgrade;

public class NumericalGradeCalculator extends GradeCalculator {

   
    public String calculateLetterGrade(double score) {
        return String.valueOf(score); 
    }

   
    public double calculateGradePoint(double score) {
        return 0.0; 
    }
}
