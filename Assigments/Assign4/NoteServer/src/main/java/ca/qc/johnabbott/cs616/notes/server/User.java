package ca.qc.johnabbott.cs616.notes.server;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ian on 15-10-02.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userid")
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "createdBy", targetEntity = Note.class)
    private List<Note> created;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public List<Note> getCreated() {
        return created;
    }

    public void setCreated(List<Note> created) {
        this.created = created;
    }
}
