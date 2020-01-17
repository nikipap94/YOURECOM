package com.yourecom.data.model;

import java.util.Date;

public class Tip {
    String authorName;
    String text;
    Date date;
    String courseId; //key of the course on firebase

    public static final String DB_NAME = "Tip";

    public Tip(String authorName, String text, String courseId) {
        this.authorName = authorName;
        this.text = text;
        this.courseId = courseId;
        this.date = new Date();
    }

    public Tip() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
