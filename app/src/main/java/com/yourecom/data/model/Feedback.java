package com.yourecom.data.model;

import java.util.Date;

public class Feedback {
    String authorName;
    String text;
    Integer rating;
    Date date;
    String courseId; //key of the course on firebase

    public static final String DB_NAME = "Feedback";

    public Feedback(String authorName, String text, Integer rating, String courseId) {
        this.authorName = authorName;
        this.text = text;
        this.rating = rating;
        this.courseId = courseId;
        this.date = new Date();
    }

    public Feedback() {
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
