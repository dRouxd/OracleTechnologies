package com.example.a1342097.assignment1;

import java.util.Date;

/**
 * Created by danny on 9/13/2016.
 */
public class Note
{

    private String title;
    private String body;
    private int category;
    private boolean hasReminder;
    private Date reminder;


    public Note(String title, String body, int category, boolean hasReminder, Date reminder) {
        this.title = title;
        this.body = body;
        this.category = category;
        this.hasReminder = hasReminder;
        this.reminder = reminder;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", category=" + category +
                ", hasReminder=" + hasReminder +
                ", reminder=" + reminder +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getCategory() {
        return category;
    }

    public boolean isHasReminder() {
        return hasReminder;
    }

    public Date getReminder() {
        return reminder;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setHasReminder(boolean hasReminder) {
        this.hasReminder = hasReminder;
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
    }



}
