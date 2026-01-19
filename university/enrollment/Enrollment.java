package com.university.enrollment;

import java.util.Date;

public class Enrollment {
    private String enrollmentId;
    private String studentId;
    private String courseCode;
    private String semester;
    private EnrollmentStatus status;
    private Date enrollmentDate;
    private Date dropDate;
    
    public Enrollment(String enrollmentId, String studentId, String courseCode, 
                     String semester, EnrollmentStatus status, Date enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.semester = semester;
        this.status = status;
        this.enrollmentDate = enrollmentDate;
    }
    

    public String getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(String enrollmentId) { this.enrollmentId = enrollmentId; }
    
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    
    public EnrollmentStatus getStatus() { return status; }
    public void setStatus(EnrollmentStatus status) { this.status = status; }
    
    public Date getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(Date enrollmentDate) { this.enrollmentDate = enrollmentDate; }
    
    public Date getDropDate() { return dropDate; }
    public void setDropDate(Date dropDate) { this.dropDate = dropDate; }
    
    @Override
    public String toString() {
        return "Enrollment{" +
               "enrollmentId='" + enrollmentId + '\'' +
               ", studentId='" + studentId + '\'' +
               ", courseCode='" + courseCode + '\'' +
               ", semester='" + semester + '\'' +
               ", status=" + status +
               '}';
    }
}