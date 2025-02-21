package com.dogigiri.Java.Core.functionalprogramming.optional;

public class ObjectNullPattern {
    public static void main(String[] args) {
        // now whenever a null behavior for reading level is needed we can return instance of NullReadingLevel class.
    }
}

abstract class ReadingLevel {
    private long id;
    private String grade;
    private String level;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

class DRAReadingLevel extends ReadingLevel {
    public DRAReadingLevel(long id, String grade, String level) {
        setId(id);
        setLevel(level);
        setGrade(grade);
    }

    @Override
    public String toString() {
        return "DRA Reading Level" + getLevel() + " " + getGrade();
    }
}

class NullReadingLevel extends ReadingLevel {
    private final static NullReadingLevel instance = new NullReadingLevel();

    public NullReadingLevel() {
        setGrade("N/A");
        setLevel("N/A");
    }

    public static NullReadingLevel getInstance() {
        return instance;
    }
}