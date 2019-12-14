package com.yourecom.data.model;

public class Feedback {
    String authorName;
    String text;
    Integer rating;

    public Feedback(String authorName, String text, Integer rating) {
        this.authorName = authorName;
        this.text = text;
        this.rating = rating;
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
}
