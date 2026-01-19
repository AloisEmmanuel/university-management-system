package projecgrade;

public class PassFailCalculator extends GradeCalculator {

    
    public String calculateLetterGrade(double score) {
        return score >= 60 ? "Pass" : "Fail";
    }

    
    public double calculateGradePoint(double score) {
        return 0.0; 
    }

    public boolean isCreditEarned(double score) {
        return score >= 60;
    }
}
