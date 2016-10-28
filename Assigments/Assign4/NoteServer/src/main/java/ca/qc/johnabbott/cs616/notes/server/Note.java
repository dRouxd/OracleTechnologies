package ca.qc.johnabbott.cs616.notes.server;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ian on 15-10-02.c
 */
@Entity
@NoteDatesRange
@Table(name="note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "noteid")
    private long id;

    @Column(name="created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name="reminder")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reminder;

    @Column(name = "title")
    private String title;

    @Column(name="body")
    private String body;

    @Column(name="category")
    private int category;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "createdby")
    private User createdBy;

    public long getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getReminder() {
        return reminder;
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
