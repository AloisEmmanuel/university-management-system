package com.university.auth;

import com.university.users.User;
import com.university.users.UserManager;


public class AuthenticationService {
    private UserManager userManager;
    private Session session;
 
    public AuthenticationService(UserManager userManager) {
        this.userManager = userManager;
        this.session = new Session();
    }
 
    public User login(String username, String password) {
        User user = userManager.login(username, password);
        if (user != null) {
            session.login(user);
            System.out.println("Login successful! Welcome " + user.getName());
            return user;
        }
        System.out.println("Login failed. Invalid username or password.");
        return null;
    }
    
    public void logout() {
        if (session.isLoggedIn()) {
            System.out.println("Goodbye, " + session.getCurrentUser().getName() + "!");
            session.logout();
        }
    }

    public boolean isLoggedIn() {
        return session.isLoggedIn();
    }

    public User getCurrentUser() {
        return session.getCurrentUser();
    }
    

    public Session getSession() {
        return session;
    }
}