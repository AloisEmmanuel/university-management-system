package com.university.notifications;

public abstract class Notification {
    
    protected String message;

    public Notification(String message) {
        this.message = message;
    }

    public abstract void send();
}