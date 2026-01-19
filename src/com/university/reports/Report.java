package com.university.reports;

import java.io.Serializable;

public abstract class Report implements Serializable {
    private static final long serialVersionUID = 1L; // ✅ ADDED
    
    private String title;

    public Report(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

 
    public abstract void generate();
}