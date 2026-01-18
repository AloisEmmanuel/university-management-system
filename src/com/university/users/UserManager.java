package com.university.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    private Map<String, User> users;
    
    public UserManager() {
        users = new HashMap<>();
    }
    

    public User createUser(String type, String id, String username, String password, 
                          String name, String email, String... additionalInfo) {
        
        User newUser = null;
        
        switch (type.toUpperCase()) {
            case "STUDENT":
                if (additionalInfo.length >= 3) {
                    newUser = new Student(id, username, password, name, email,
                                         additionalInfo[0], 
                                         additionalInfo[1],  
                                         Integer.parseInt(additionalInfo[2]));
                }
                break;
                
            case "INSTRUCTOR":
                if (additionalInfo.length >= 2) {
                    newUser = new Instructor(id, username, password, name, email,
                                            additionalInfo[0],  
                                            additionalInfo[1]); 
                }
                break;
                
            case "ADMIN":
                if (additionalInfo.length >= 2) {
                    newUser = new Admin(id, username, password, name, email,
                                       additionalInfo[0], 
                                       additionalInfo[1]); 
                }
                break;
                
            default:
                System.out.println("Invalid user type: " + type);
                return null;
        }
        
        if (newUser != null) {
            users.put(id, newUser);
            System.out.println("User created: " + name + " (" + type + ")");
        }
        
        return newUser;
    }
    
 
    public boolean updateUser(String userId, String username, String password, 
                             String name, String email) {
        User user = users.get(userId);
        
        if (user != null) {
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            user.setEmail(email);
            System.out.println("User updated: " + name);
            return true;
        }
        
        System.out.println("User not found with ID: " + userId);
        return false;
    }
    
  
    public boolean deleteUser(String userId) {
        User removedUser = users.remove(userId);
        
        if (removedUser != null) {
            System.out.println("User deleted: " + removedUser.getName());
            return true;
        }
        
        System.out.println("User not found with ID: " + userId);
        return false;
    }
    
    public User getUserById(String userId) {
        return users.get(userId);
    }
    

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    

    public User login(String username, String password) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username) && 
                user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Student) {
                students.add((Student) user);
            }
        }
        return students;
    }
    
    public List<Instructor> getAllInstructors() {
        List<Instructor> instructors = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Instructor) {
                instructors.add((Instructor) user);
            }
        }
        return instructors;
    }
    
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Admin) {
                admins.add((Admin) user);
            }
        }
        return admins;
    }
    
    public int getUserCount() {
        return users.size();
    }
    
    public void displayAllUsers() {
        System.out.println("\n=== All Users (" + users.size() + ") ===");
        for (User user : users.values()) {
            user.displayInfo();
            System.out.println("---");
        }
    }
}