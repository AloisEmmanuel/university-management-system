package courses;

import java.util.ArrayList;
import java.util.List;

public class CourseManagement {
    private List<Course> courses = new ArrayList<>();

    public void create(String id, String name, int cap) {
        courses.add(new Course(id, name, cap));
        System.out.println("Course created: " + name);
    }

    public void list() {
        System.out.println("\nAvailable Courses:");
        for (Course c : courses) {
            System.out.println(c.getId() + " - " + c.getName() +
                    " | Cap: " + c.getCapacity() +
                    " | Pre: " + c.getPrerequisites());
        }
    }

    public void update(String id, String newName, int newCap) {
        Course c = find(id);
        if (c != null) {
            c.setName(newName);
            c.setCapacity(newCap);
            System.out.println("Course updated: " + newName);
        } else System.out.println("Course not found!");
    }

    public void delete(String id) {
        courses.removeIf(c -> c.getId().equals(id));
        System.out.println("Course deleted (if existed): " + id);
    }

    public Course find(String id) {
        for (Course c : courses) if (c.getId().equals(id)) return c;
        return null;
    }
}
