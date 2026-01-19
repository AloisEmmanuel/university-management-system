package com.university.notifications;

public interface NotificationService {
    void sendNotification(String recipient, String message, String type);
    void sendEnrollmentNotification(String studentId, String courseCode, String semester);
    void sendDropNotification(String studentId, String courseCode, String semester);
    void sendGradeNotification(String studentId, String courseCode, String grade);
}