package courses;

public abstract class Report {

    private String title;

    public Report(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // All child reports must implement this
    public abstract void generate();
}
