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

    public boolean contains(String s){
        s = s.toLowerCase();
        return (this.title != null && this.title.toLowerCase().contains(s)) ||
                (this.acronym != null && this.acronym.toLowerCase().contains(s)) ||
                (this.professor != null && this.professor.getName() != null && this.professor.getName().toLowerCase().contains(s));

    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", acronym='" + acronym + '\'' +
                '}';
    }
}
