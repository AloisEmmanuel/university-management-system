package com.university;

import com.university.users.*;
import com.university.courses.*;
import com.university.enrollment.*;
import com.university.grades.*;
import com.university.notifications.*;
import com.university.reports.*;
import com.university.auth.*;
import com.university.persistence.*;
import java.util.*;

public class Main {
    private static UserManager userManager;
    private static CourseManager courseManager;
    private static EnrollmentManager enrollmentManager;
    private static GradeManager gradeManager;
    private static NotificationManager notificationManager;
    private static ReportManager reportManager;
    private static AuthenticationService authService;
    private static FileRepository dataRepository;
    private static User currentUser;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("  UNIVERSITY COURSE MANAGEMENT SYSTEM");
        System.out.println("=========================================\n");
        
        initializeSystem();
        displayMainMenu();
    }

    private static void initializeSystem() {
        System.out.println("Initializing system...");
        dataRepository = new FileRepository();
        System.out.println("Loading data from files...");
        dataRepository.loadAllDataOnStartup();
        userManager = new UserManager();
        courseManager = new CourseManager();
        notificationManager = new NotificationManager();
        gradeManager = new GradeManager(notificationManager);
        enrollmentManager = new EnrollmentManager(courseManager, gradeManager, notificationManager);
        reportManager = new ReportManager(userManager, courseManager, enrollmentManager, gradeManager);
        authService = new AuthenticationService(userManager);
        if (userManager.getAllUsers().isEmpty()) {
            System.out.println("No data found. Creating sample data...");
            createSampleData();
        }
        System.out.println("System initialization complete!\n");
    }

    private static void createSampleData() {
        userManager.createUser("ADMIN", "1", "admin", "admin123", 
                               "System Admin", "admin@university.edu",
                               "ADM001", "Administration");
        userManager.createUser("INSTRUCTOR", "2", "prof1", "prof123",
                               "Dr. Jane Smith", "j.smith@university.edu",
                               "INS001", "Computer Science");
        userManager.createUser("STUDENT", "3", "rugagi", "30054",
                               "Rugagi", "rugagi@university.edu",
                               "STU30054", "Computer Science", "2");
        userManager.createUser("STUDENT", "4", "mahammed", "29956",
                               "Mahammed", "mahammed@university.edu",
                               "STU29956", "Computer Science", "2");
        userManager.createUser("STUDENT", "5", "tunga", "29602",
                               "Tunga", "tunga@university.edu",
                               "STU29602", "Computer Science", "2");
        userManager.createUser("STUDENT", "6", "bruno", "30245",
                               "Bruno", "bruno@university.edu",
                               "STU30245", "Computer Science", "2");
        userManager.createUser("STUDENT", "7", "yacoub", "29657",
                               "Yacoub", "yacoub@university.edu",
                               "STU29657", "Computer Science", "2");
        userManager.createUser("STUDENT", "8", "munib", "30011",
                               "Munib", "munib@university.edu",
                               "STU30011", "Computer Science", "2");
        userManager.createUser("STUDENT", "9", "adil", "30119",
                               "Adil", "adil@university.edu",
                               "STU30119", "Computer Science", "2");
        courseManager.addCourse(new Course("CS101", "Introduction to Programming", 3, "INS001", 30));
        courseManager.addCourse(new Course("CS201", "Data Structures", 3, "INS001", 25));
        courseManager.addCourse(new Course("MATH101", "General Mathematics ", 4, "", 40));
        System.out.println("Sample data created successfully.");
    }

    private static void displayMainMenu() {
        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose option (1-2): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    exitSystem();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login() {
        System.out.println("\n=== LOGIN ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        currentUser = authService.login(username, password);
        if (currentUser != null) {
            System.out.println("\nWelcome, " + currentUser.getName() + "!");
            showRoleBasedMenu();
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    private static void showRoleBasedMenu() {
        String role = currentUser.getRole();
        switch (role) {
            case "STUDENT":
                showStudentMenu();
                break;
            case "INSTRUCTOR":
                showInstructorMenu();
                break;
            case "ADMIN":
                showAdminMenu();
                break;
            default:
                System.out.println("Unknown role. Returning to main menu.");
        }
    }

    private static void showStudentMenu() {
        Student student = (Student) currentUser;
        while (true) {
            System.out.println("\n=== STUDENT MENU ===");
            System.out.println("Welcome, " + student.getName() + " (ID: " + student.getStudentId() + ")");
            System.out.println("1. View Available Courses");
            System.out.println("2. Enroll in Course");
            System.out.println("3. Drop Course");
            System.out.println("4. View My Courses");
            System.out.println("5. View Grades");
            System.out.println("6. View Transcript");
            System.out.println("7. Logout");
            System.out.print("Choose option (1-7): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    viewAvailableCourses();
                    break;
                case "2":
                    enrollInCourse(student);
                    break;
                case "3":
                    dropCourse(student);
                    break;
                case "4":
                    viewMyCourses(student);
                    break;
                case "5":
                    viewGrades(student);
                    break;
                case "6":
                    viewTranscript(student);
                    break;
                case "7":
                    System.out.println("Logging out...");
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showInstructorMenu() {
        Instructor instructor = (Instructor) currentUser;
        while (true) {
            System.out.println("\n=== INSTRUCTOR MENU ===");
            System.out.println("Welcome, " + instructor.getName() + " (ID: " + instructor.getInstructorId() + ")");
            System.out.println("1. View My Courses");
            System.out.println("2. View Course Roster");
            System.out.println("3. Assign Grades");
            System.out.println("4. View Teaching Load");
            System.out.println("5. Logout");
            System.out.print("Choose option (1-5): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    viewInstructorCourses(instructor);
                    break;
                case "2":
                    viewCourseRoster(instructor);
                    break;
                case "3":
                    assignGrades(instructor);
                    break;
                case "4":
                    viewTeachingLoad(instructor);
                    break;
                case "5":
                    System.out.println("Logging out...");
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showAdminMenu() {
        Admin admin = (Admin) currentUser;
        while (true) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("Welcome, " + admin.getName() + " (ID: " + admin.getAdminId() + ")");
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Courses");
            System.out.println("3. View System Reports");
            System.out.println("4. Send Announcement");
            System.out.println("5. View System Statistics");
            System.out.println("6. Save All Data");
            System.out.println("7. Load All Data");
            System.out.println("8. Logout");
            System.out.print("Choose option (1-8): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    manageUsers(admin);
                    break;
                case "2":
                    manageCourses(admin);
                    break;
                case "3":
                    viewSystemReports(admin);
                    break;
                case "4":
                    sendAnnouncement(admin);
                    break;
                case "5":
                    viewSystemStatistics();
                    break;
                case "6":
                    saveAllData();
                    break;
                case "7":
                    loadAllData();
                    break;
                case "8":
                    System.out.println("Logging out...");
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewAvailableCourses() {
        System.out.println("\n=== AVAILABLE COURSES ===");
        List<Course> courses = courseManager.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                System.out.println((i + 1) + ". " + course.getCode() + " - " + course.getTitle());
                System.out.println("   Credits: " + course.getCredits() + ", Capacity: " + 
                                 course.getCapacity() + ", Instructor: " + course.getInstructorId());
            }
        }
    }
    
    private static void enrollInCourse(Student student) {
        System.out.println("\n=== ENROLL IN COURSE ===");
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter semester (e.g., Fall2024): ");
        String semester = scanner.nextLine();
        Enrollment enrollment = enrollmentManager.enrollStudent(student.getStudentId(), courseCode, semester);
        if (enrollment != null) {
            System.out.println("Successfully enrolled in " + courseCode);
        }
    }
    
    private static void dropCourse(Student student) {
        System.out.println("\n=== DROP COURSE ===");
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter semester: ");
        String semester = scanner.nextLine();
        boolean success = enrollmentManager.dropCourse(student.getStudentId(), courseCode, semester);
        if (success) {
            System.out.println("Successfully dropped " + courseCode);
        }
    }
    
    private static void viewMyCourses(Student student) {
        System.out.println("\n=== MY COURSES ===");
        List<Course> courses = enrollmentManager.getEnrolledCourses(student.getStudentId(), "Fall2024");
        if (courses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
        } else {
            for (Course course : courses) {
                System.out.println("- " + course.getCode() + ": " + course.getTitle());
            }
        }
    }
    
    private static void viewGrades(Student student) {
        System.out.println("\n=== MY GRADES ===");
        List<Grade> grades = gradeManager.getGradesByStudent(student.getStudentId());
        if (grades.isEmpty()) {
            System.out.println("No grades available.");
        } else {
            System.out.printf("%-12s %-10s %-10s %-10s\n", "Course", "Grade", "Points", "Status");
            System.out.println("------------------------------------------");
            for (Grade grade : grades) {
                String status = grade.isPassing() ? "PASS" : "FAIL";
                System.out.printf("%-12s %-10s %-10.1f %-10s\n", grade.getCourseCode(), grade.getGradeAsString(), grade.getGradePoint(), status);
            }
        }
    }
    
    private static void viewTranscript(Student student) {
        System.out.println("\n=== TRANSCRIPT ===");
        reportManager.generateTranscript(student);
    }

    private static void viewInstructorCourses(Instructor instructor) {
        System.out.println("\n=== MY COURSES ===");
        List<String> courses = instructor.getCoursesTaught();
        if (courses.isEmpty()) {
            System.out.println("You are not teaching any courses.");
        } else {
            for (String courseCode : courses) {
                Course course = courseManager.getCourseByCode(courseCode);
                if (course != null) {
                    System.out.println("- " + course.getCode() + ": " + course.getTitle());
                }
            }
        }
    }
    
    private static void viewCourseRoster(Instructor instructor) {
        System.out.println("\n=== COURSE ROSTER ===");
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        List<String> students = enrollmentManager.getEnrolledStudents(courseCode, "Fall2024");
        if (students.isEmpty()) {
            System.out.println("No students enrolled in " + courseCode);
        } else {
            System.out.println("Students enrolled in " + courseCode + ":");
            for (String studentId : students) {
                System.out.println("- " + studentId);
            }
        }
    }
    
    private static void assignGrades(Instructor instructor) {
        System.out.println("\n=== ASSIGN GRADES ===");
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.println("Select grading scheme:");
        System.out.println("1. Letter Grade (A, B, C, D, F)");
        System.out.println("2. Numeric Grade (0-100)");
        System.out.println("3. Pass/Fail");
        System.out.print("Choose scheme (1-3): ");
        String schemeChoice = scanner.nextLine();
        String schemeType = "";
        Object gradeValue = null;
        switch (schemeChoice) {
            case "1":
                System.out.print("Enter letter grade (A/B/C/D/F): ");
                gradeValue = scanner.nextLine();
                schemeType = "LETTER";
                break;
            case "2":
                System.out.print("Enter numeric grade (0-100): ");
                gradeValue = Double.parseDouble(scanner.nextLine());
                schemeType = "NUMERIC";
                break;
            case "3":
                System.out.print("Enter result (Pass/Fail): ");
                gradeValue = scanner.nextLine();
                schemeType = "PASSFAIL";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        System.out.print("Enter semester: ");
        String semester = scanner.nextLine();
        boolean success = gradeManager.assignGrade(studentId, courseCode, gradeValue, schemeType, semester);
        if (success) {
            System.out.println("Grade assigned successfully!");
        }
    }
    
    private static void viewTeachingLoad(Instructor instructor) {
        System.out.println("\n=== TEACHING LOAD ===");
        reportManager.generateTeachingLoad(instructor.getInstructorId());
    }
    
    private static void manageUsers(Admin admin) {
        System.out.println("\n=== MANAGE USERS ===");
        System.out.println("1. View All Users");
        System.out.println("2. Add User");
        System.out.println("3. Remove User");
        System.out.print("Choose option (1-3): ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                viewAllUsers();
                break;
            case "2":
                addUser();
                break;
            case "3":
                removeUser();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    private static void viewAllUsers() {
        System.out.println("\n=== ALL USERS ===");
        List<User> users = userManager.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users in system.");
        } else {
            for (User user : users) {
                System.out.println("- " + user.getName() + " (" + user.getRole() + ")");
            }
        }
    }
    
    private static void addUser() {
        System.out.println("\n=== ADD USER ===");
        System.out.println("User type: STUDENT, INSTRUCTOR, or ADMIN");
        System.out.print("Enter user type: ");
        String type = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        User newUser = null;
        if (type.equalsIgnoreCase("STUDENT")) {
            System.out.print("Enter student ID: ");
            String studentId = scanner.nextLine();
            System.out.print("Enter program: ");
            String program = scanner.nextLine();
            System.out.print("Enter year: ");
            String year = scanner.nextLine();
            newUser = userManager.createUser("STUDENT", generateId(), username, password, name, email, studentId, program, year);
        } else if (type.equalsIgnoreCase("INSTRUCTOR")) {
            System.out.print("Enter instructor ID: ");
            String instructorId = scanner.nextLine();
            System.out.print("Enter department: ");
            String department = scanner.nextLine();
            newUser = userManager.createUser("INSTRUCTOR", generateId(), username, password, name, email, instructorId, department);
        } else if (type.equalsIgnoreCase("ADMIN")) {
            System.out.print("Enter admin ID: ");
            String adminId = scanner.nextLine();
            System.out.print("Enter department: ");
            String department = scanner.nextLine();
            newUser = userManager.createUser("ADMIN", generateId(), username, password, name, email, adminId, department);
        }
        if (newUser != null) {
            System.out.println("User added successfully.");
        }
    }
    
    private static void removeUser() {
        System.out.println("\n=== REMOVE USER ===");
        System.out.print("Enter user ID to remove: ");
        String userId = scanner.nextLine();
        boolean removed = userManager.deleteUser(userId);
        if (removed) {
            System.out.println("User removed successfully.");
        }
    }
    
    private static void manageCourses(Admin admin) {
        System.out.println("\n=== MANAGE COURSES ===");
        System.out.println("1. View All Courses");
        System.out.println("2. Add Course");
        System.out.println("3. Remove Course");
        System.out.print("Choose option (1-3): ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                viewAllCourses();
                break;
            case "2":
                addCourse();
                break;
            case "3":
                removeCourse();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    private static void viewAllCourses() {
        System.out.println("\n=== ALL COURSES ===");
        List<Course> courses = courseManager.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses in system.");
        } else {
            for (Course course : courses) {
                System.out.println("- " + course.getCode() + ": " + course.getTitle() + " (" + course.getCredits() + " credits)");
            }
        }
    }
    
    private static void addCourse() {
        System.out.println("\n=== ADD COURSE ===");
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter credits: ");
        int credits = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter instructor ID: ");
        String instructorId = scanner.nextLine();
        System.out.print("Enter capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine());
        Course course = new Course(code, title, credits, instructorId, capacity);
        courseManager.addCourse(course);
        System.out.println("Course added successfully.");
    }
    
    private static void removeCourse() {
        System.out.println("\n=== REMOVE COURSE ===");
        System.out.print("Enter course code to remove: ");
        String courseCode = scanner.nextLine();
        boolean removed = courseManager.removeCourse(courseCode);
        if (removed) {
            System.out.println("Course removed successfully.");
        }
    }
    
    private static void viewSystemReports(Admin admin) {
        System.out.println("\n=== SYSTEM REPORTS ===");
        System.out.println("1. Generate Student Transcript");
        System.out.println("2. Generate Course Roster");
        System.out.println("3. Generate Teaching Load Report");
        System.out.println("4. Generate Department Summary");
        System.out.println("5. Generate System Report");
        System.out.println("6. List All Report Types");
        System.out.print("Choose option (1-6): ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.print("Enter student ID: ");
                String studentId = scanner.nextLine();
                reportManager.generateTranscript(studentId);
                break;
            case "2":
                System.out.print("Enter course code: ");
                String courseCode = scanner.nextLine();
                reportManager.generateCourseRoster(courseCode);
                break;
            case "3":
                System.out.print("Enter instructor ID: ");
                String instructorId = scanner.nextLine();
                reportManager.generateTeachingLoad(instructorId);
                break;
            case "4":
                System.out.print("Enter department name: ");
                String department = scanner.nextLine();
                reportManager.generateDepartmentSummary(department);
                break;
            case "5":
                reportManager.generateSystemReport();
                break;
            case "6":
                reportManager.listReportTypes();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    private static void sendAnnouncement(Admin admin) {
        System.out.println("\n=== SEND ANNOUNCEMENT ===");
        System.out.print("Enter announcement: ");
        String message = scanner.nextLine();
        admin.sendAnnouncement(message);
        System.out.println("Announcement sent!");
    }
    
    private static void viewSystemStatistics() {
        System.out.println("\n=== SYSTEM STATISTICS ===");
        System.out.println("Users: " + userManager.getAllUsers().size());
        System.out.println("Courses: " + courseManager.getAllCourses().size());
        System.out.println("Students: " + userManager.getAllStudents().size());
        System.out.println("Instructors: " + userManager.getAllInstructors().size());
        System.out.println("Admins: " + userManager.getAllAdmins().size());
    }
    
    private static void saveAllData() {
        System.out.println("\n=== SAVE ALL DATA ===");
        dataRepository.saveAllDataPeriodically();
        System.out.println("Data saved successfully!");
    }
    
    private static void loadAllData() {
        System.out.println("\n=== LOAD ALL DATA ===");
        dataRepository.loadAllDataOnStartup();
        System.out.println("Data loaded successfully!");
    }

    private static void exitSystem() {
        System.out.println("\n=== EXITING SYSTEM ===");
        System.out.print("Save data before exiting? (yes/no): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            saveAllData();
        }
        System.out.println("\nThank you for using University Course Management System!");
        System.out.println("Goodbye!");
        scanner.close();
        System.exit(0);
    }

    private static String generateId() {
        return "ID" + System.currentTimeMillis() + (int)(Math.random() * 1000);
    }

}
