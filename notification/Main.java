package notification;
public class Main {
    public static void main(String[] args) {

        NotificationManager manager = new NotificationManager();

        Notification email = new EmailNotification(
                "student@mail.com",
                "You have been enrolled in CSC101"
        );

        Notification sms = new SMSNotification(
                "+250791234567",
                "Your grade has been posted for CSC101: A"
        );

        Notification inApp = new InAppNotification(
                "khalil",
                "You dropped CSC101"
        );

        manager.notifyEnrollment(email);
        manager.notifyGradePosted(sms);
        manager.notifyCourseDropped(inApp);
    }
}