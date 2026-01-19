package courses;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CourseManagement cm = new CourseManagement();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Create Course");
            System.out.println("2. List Courses");
            System.out.println("3. Update Course");
            System.out.println("4. Delete Course");
            System.out.println("5. Add Prerequisite");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Cap: ");
                    int cap = sc.nextInt(); sc.nextLine();
                    cm.create(id, name, cap);
                    break;

                case 2: cm.list(); break;

                case 3:
                    System.out.print("ID: ");
                    String uid = sc.nextLine();
                    System.out.print("New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("New Cap: ");
                    int newCap = sc.nextInt(); sc.nextLine();
                    cm.update(uid, newName, newCap);
                    break;

                case 4:
                    System.out.print("ID: ");
                    cm.delete(sc.nextLine());
                    break;

                case 5:
                    System.out.print("Course ID: ");
                    Course c = cm.find(sc.nextLine());
                    if (c != null) {
                        System.out.print("Prerequisite ID: ");
                        c.addPrerequisite(sc.nextLine());
                    } else System.out.println("Not found!");
                    break;
            }

        } while (choice != 0);

        System.out.println("Exit");
        sc.close();
    }
}
