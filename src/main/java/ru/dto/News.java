package ru.dto;

import java.util.Date;

public class News {
    private int id;
    private Date publishDate;
    private String title;
    private String text;

    public News(int id, Date publishDate, String title, String text) {
        this.id = id;
        this.publishDate = publishDate;
        this.title = title;
        this.text = text;
    }

    public News() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}