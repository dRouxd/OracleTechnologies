package com.example.a1342097.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

/**
 * Created by 1342097 on 2016-10-28.
 */
public class Note {


    public String url;
    public String title;
    public String body;
    public int category;
    public Date reminder;
    public Date created;
    public String createdBy;


    class Links
    {
        class Self
        {
            public String href;
        }

        class InnerNote
        {
            public String href;
        }

        class CreatedBy
        {
            public String href;
        }


        Self self;
        InnerNote note;
        CreatedBy createdBy;
    }

    private Links _links;


    private static class Embedded
    {
        public Embedded()
        {
        }
        private Note[] note;
    }

    private static class NoteArray
    {

        private  Embedded _embedded = new Embedded();
    }




    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isHasReminder() {
        if(reminder == null)
            return false;
        return reminder.equals(null);
    }

    public Date getReminder() {
        return reminder;
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "User{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", category=" + category +
                ", reminder=" + reminder +
                ", created=" + created +
                '}';
    }


    public static Note parse(String parseString)
    {


        System.out.println(parseString);

        Note note = new Gson().fromJson(parseString, Note.class);

        note.setUrl(note._links.self.href);
        note.setCreatedBy(note._links.createdBy.href);

        return note;
    }

    public static Note[] parseArray(String parseString)
    {


        System.out.println(parseString);

        Note[] notes = new Gson().fromJson(parseString, NoteArray.class)._embedded.note;

        return notes;
    }

    public String format()
    {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .create();
        return gson.toJson(this);
    }

}


















































