package com.university.auth;


/**
 * Service for user authentication and session management.
 * Demonstrates ENCAPSULATION - internal authentication logic is hidden.
 * 
 * TODO: Implement login, logout, and role-based access control.
 */
public class AuthenticationService {
    private User currentUser;
    
    public AuthenticationService() {
        this.currentUser = null;
    }
    
    /**
     * Authenticate a user with username and password.
     * 
     * @param username The username
     * @param password The password
     * @return The authenticated user if successful, null otherwise
     */
    public User login(String username, String password) {
        // TODO: Implement authentication logic
        // - Retrieve user from repository
        // - Verify password
        // - Set currentUser if successful
        return null;
    }
    
    /**
     * Log out the current user.
     */
    public void logout() {
        this.currentUser = null;
    }
    
    /**
     * Check if a user is currently logged in.
     * 
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }
    
    /**
     * Get the currently logged in user.
     * 
     * @return The current user, or null if not logged in
     */
    public User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Check if the current user has a specific role.
     * 
     * @param role The role to check (e.g., "ADMIN", "INSTRUCTOR", "STUDENT")
     * @return true if user has the role, false otherwise
     */
    public boolean hasRole(String role) {
        if (currentUser == null) {
            return false;
        }
        return currentUser.getRole().equals(role);
    }
    
    /**
     * Check if the current user can perform an action.
     * 
     * @param action The action to check (e.g., "ENROLL_STUDENT", "ASSIGN_GRADE")
     * @return true if user can perform the action, false otherwise
     */
    public boolean canPerformAction(String action) {
        if (!isLoggedIn()) {
            return false;
        }
        
        // TODO: Implement role-based access control logic
        // Different roles have different permissions
        String role = currentUser.getRole();
        
        switch (action) {
            case "ENROLL_STUDENT":
                return role.equals("STUDENT") || role.equals("ADMIN");
            case "ASSIGN_GRADE":
                return role.equals("INSTRUCTOR") || role.equals("ADMIN");
            case "MANAGE_USERS":
                return role.equals("ADMIN");
            case "VIEW_TRANSCRIPT":
                return true; // All users can view their own transcript
            default:
                return false;
        }
    }
}