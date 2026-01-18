package com.university.enrollment;

public class EnrollmentException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public EnrollmentException(String message) {
        super(message);
    }
    
    public EnrollmentException(String message, Throwable cause) {
        super(message, cause);
    }
}