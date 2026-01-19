package com.university.notifications;

public class InAppNotification extends Notification {

    private String username;

    public InAppNotification(String username, String message) {
        super(message);
        this.username = username;
    }

    @Override
    public void send() {
        System.out.println("IN-APP for user " + username + ": " + message);
    }
}