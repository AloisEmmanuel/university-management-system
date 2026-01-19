package com.university.auth;

import com.university.users.User;

public class AccessControl {
    private AuthorizationService authService;
    
    public AccessControl(AuthorizationService authService) {
        this.authService = authService;
    }
    
    public boolean canAccessMenu(User user, String menuName) {
        if (user == null) return false;
        
        switch (menuName.toUpperCase()) {
            case "STUDENT_MENU":
                return authService.hasAccess(user, "STUDENT");
                
            case "INSTRUCTOR_MENU":
                return authService.hasAccess(user, "INSTRUCTOR");
                
            case "ADMIN_MENU":
                return authService.hasAccess(user, "ADMIN");
                
            case "ENROLLMENT":
                return authService.canPerformAction(user, "ENROLL_IN_COURSE");
                
            case "GRADE_ASSIGNMENT":
                return authService.canPerformAction(user, "ASSIGN_GRADES");
                
            case "USER_MANAGEMENT":
                return authService.canPerformAction(user, "MANAGE_USERS");
                
            case "COURSE_MANAGEMENT":
                return authService.canPerformAction(user, "MANAGE_COURSES");
                
            case "REPORT_GENERATION":
                return authService.canPerformAction(user, "VIEW_SYSTEM_REPORTS");
                
            default:
                return false;
        }
    }
    
    public boolean validateAccess(User user, String action, String errorMessage) {
        boolean hasAccess = authService.canPerformAction(user, action);
        
        if (!hasAccess && errorMessage != null) {
            System.out.println("Access Denied: " + errorMessage);
        }
        
        return hasAccess;
    }
    

    public String[] getAccessibleMenus(User user) {
        if (user == null) return new String[0];
        
        String role = user.getRole();
        
        switch (role) {
            case "STUDENT":
                return new String[] {
                    "View Courses", "Enroll", "Drop Course", 
                    "My Courses", "View Grades", "Transcript"
                };
                
            case "INSTRUCTOR":
                return new String[] {
                    "My Courses", "Course Roster", "Assign Grades",
                    "Teaching Load"
                };
                
            case "ADMIN":
                return new String[] {
                    "Manage Users", "Manage Courses", "System Reports",
                    "Send Announcement", "System Statistics", 
                    "Save Data", "Load Data"
                };
                
            default:
                return new String[0];
        }
    }
}