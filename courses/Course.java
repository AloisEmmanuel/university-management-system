package courses;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private String name;
    private int capacity;
    private List<String> prerequisites = new ArrayList<>();
    private List<String> enrolled = new ArrayList<>();

    public Course(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public List<String> getPrerequisites() { return prerequisites; }
    public List<String> getEnrolled() { return enrolled; }

    public void setName(String name) { this.name = name; }
    public void setCapacity(int c) { this.capacity = c; }

    public void addPrerequisite(String pid) { prerequisites.add(pid); }
}
