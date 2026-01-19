package com.university.notifications;

public class SMSNotification extends Notification {

    private String phone;

    public SMSNotification(String phone, String message) {
        super(message);
        this.phone = phone;
    }

    @Override
    public void send() {
        System.out.println("SMS to " + phone + ": " + message);
    }
}