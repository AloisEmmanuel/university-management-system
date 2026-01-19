package com.university.users;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {

    private String adminId;
    private String department;
    private List<String> permissions;
    

    public Admin(String id, String username, String password, String name, String email,
                 String adminId, String department) {
        super(id, username, password, name, email);
        this.adminId = adminId;
        this.department = department;
        this.permissions = new ArrayList<>();

        permissions.add("MANAGE_USERS");
        permissions.add("MANAGE_COURSES");
        permissions.add("VIEW_REPORTS");
    }
    

    @Override
    public String getRole() {
        return "ADMIN";
    }
    
    @Override
    public void displayInfo() {
        System.out.println("=== Administrator Information ===");
        System.out.println("Admin ID: " + adminId);
        System.out.println("Name: " + getName());
        System.out.println("Department: " + department);
        System.out.println("Email: " + getEmail());
        System.out.println("Permissions: " + permissions.size());
        System.out.println("Role: " + getRole());
    }

    public void sendAnnouncement(String message) {
        System.out.println("\n=== ADMIN ANNOUNCEMENT ===");
        System.out.println("From: " + getName());
        System.out.println("Message: " + message);
        System.out.println("===========================");
    }
  
    public void addPermission(String permission) {
        if (!permissions.contains(permission)) {
            permissions.add(permission);
        }
    }
    
    public void removePermission(String permission) {
        permissions.remove(permission);
    }
    
    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }
 
    public String getAdminId() {
        return adminId;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public List<String> getPermissions() {
        return new ArrayList<>(permissions);
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
}