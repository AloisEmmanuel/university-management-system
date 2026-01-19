package notification;

public class EmailNotification extends Notification {

    private String email;

    public EmailNotification(String email, String message) {
        super(message);
        this.email = email;
    }

    @Override
    public void send() {
        System.out.println("EMAIL to " + email + ": " + message);
    }
}