package com.university.notifications;

public class NotificationManager {
    

    public void notifyEnrollment(Notification notification) {
        System.out.println("[Notification System] Enrollment notification:");
        notification.send();
    }

    public void notifyGradePosted(Notification notification) {
        System.out.println("[Notification System] Grade notification:");
        notification.send();
    }

    public void notifyCourseDropped(Notification notification) {
        System.out.println("[Notification System] Drop notification:");
        notification.send();
    }

    public void sendEnrollmentNotification(String studentId, String courseCode, String semester) {
        String message = "You have been successfully enrolled in " + courseCode + " for " + semester;
        

        EmailNotification email = new EmailNotification(studentId + "@university.edu", message);
        InAppNotification inApp = new InAppNotification(studentId, message);
        
   
        notifyEnrollment(email);
        notifyEnrollment(inApp);
    }
    
    public void sendDropNotification(String studentId, String courseCode, String semester) {
        String message = "You have dropped " + courseCode + " for " + semester;
        
        EmailNotification email = new EmailNotification(studentId + "@university.edu", message);
        InAppNotification inApp = new InAppNotification(studentId, message);
        
        notifyCourseDropped(email);
        notifyCourseDropped(inApp);
    }
    
    public void sendGradeNotification(String studentId, String courseCode, String grade) {
        String message = "Your grade for " + courseCode + " has been posted: " + grade;
        
        EmailNotification email = new EmailNotification(studentId + "@university.edu", message);
        InAppNotification inApp = new InAppNotification(studentId, message);
        
        notifyGradePosted(email);
        notifyGradePosted(inApp);
    }
    
    public void sendAnnouncement(String recipient, String message, String notificationType) {
        Notification notification;
        
        switch (notificationType.toUpperCase()) {
            case "EMAIL":
                notification = new EmailNotification(recipient, message);
                break;
            case "SMS":
                notification = new SMSNotification(recipient, message);
                break;
            case "INAPP":
                notification = new InAppNotification(recipient, message);
                break;
            default:
                notification = new EmailNotification(recipient, message);
        }
        
        notification.send();
    }
}