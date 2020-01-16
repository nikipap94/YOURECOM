package com.yourecom.data.model;

public class Tip {
    String authorName;
    String text;

    public static final String DB_NAME = "Tip";

    public Tip(String authorName, String text) {
        this.authorName = authorName;
        this.text = text;
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
}
