package com.yourecom.data.model;

public class Professor {
    private String name;

    public static final String DB_NAME = "Professor";

    public Professor(String name) {
        this.name = name;
    }

    public Professor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
