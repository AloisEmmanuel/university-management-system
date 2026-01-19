package notification;
public class NotificationManager {

    public void notifyEnrollment(Notification notification) {
        notification.send();
    }

    public void notifyGradePosted(Notification notification) {
        notification.send();
    }

    public void notifyCourseDropped(Notification notification) {
        notification.send();
    }
}