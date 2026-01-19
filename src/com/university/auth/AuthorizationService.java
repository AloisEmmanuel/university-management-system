package com.university.auth;

import com.university.users.User;
import com.university.users.Admin;

public class AuthorizationService {

    public boolean hasAccess(User user, String requiredRole) {
        if (user == null) return false;
        return user.getRole().equals(requiredRole);
    }
    

    public boolean hasAnyRole(User user, String... roles) {
        if (user == null) return false;
        
        String userRole = user.getRole();
        for (String role : roles) {
            if (userRole.equals(role)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasAdminPermission(User user, String permission) {
        if (user == null || !user.getRole().equals("ADMIN")) {
            return false;
        }
        
   
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            return admin.hasPermission(permission);
        }
        
        return false;
    }
    
 
    public boolean canPerformAction(User user, String action) {
        if (user == null) return false;
        
        switch (action) {
            case "ENROLL_IN_COURSE":
            case "DROP_COURSE":
            case "VIEW_OWN_GRADES":
            case "VIEW_TRANSCRIPT":
                return hasAccess(user, "STUDENT");
                
            case "ASSIGN_GRADES":
            case "VIEW_COURSE_ROSTER":
            case "VIEW_TEACHING_LOAD":
                return hasAccess(user, "INSTRUCTOR");
                
            case "MANAGE_USERS":
            case "MANAGE_COURSES":
            case "VIEW_SYSTEM_REPORTS":
            case "SEND_ANNOUNCEMENTS":
                return hasAccess(user, "ADMIN");
                
            case "VIEW_COURSES":
                return hasAnyRole(user, "STUDENT", "INSTRUCTOR", "ADMIN");
                
            default:
                return false;
        }
    }
}