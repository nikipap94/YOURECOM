package com.yourecom.data.model;

public class Course {
    private String title;
    private String acronym;
    private Professor professor;



    public Course(String title, String acronym, Professor professor) {
        this.title = title;
        this.acronym = acronym;
        this.professor = professor;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
