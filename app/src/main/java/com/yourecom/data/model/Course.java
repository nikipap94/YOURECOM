package com.yourecom.data.model;

public class Course {
    private String title;
    private String acronym;
    private Professor professor;
    private Integer ratingSum;
    private Integer ratingCount;
    private String tags;

    public static final String DB_NAME = "Course";


    public Course(String title, String acronym, Professor professor) {
        this.title = title;
        this.acronym = acronym;
        this.professor = professor;
        this.tags = generateTags();
    }

    public Course() {

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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getRatingSum() {
        if(ratingCount == null){
            return 0;
        }
        return ratingSum;
    }

    public void setRatingSum(Integer ratingSum) {
        this.ratingSum = ratingSum;
    }

    public Integer getRatingCount() {
        if(ratingCount == null){
            return 0;
        }
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public float getAverageRating(){
        if(getRatingCount() == 0){
            return 0;
        }
        return ((float)getRatingSum() / (float)getRatingCount());
    }

    public String generateTags(){
        return this.getAcronym() + " " + this.getTitle() + " " + this.getProfessor().getName();
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
