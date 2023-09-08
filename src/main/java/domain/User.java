package domain;

import jakarta.persistence.*;
import org.hsqldb.lib.List;

import java.util.Set;

@Entity
public abstract class User {

    private long id;
    protected   String mail;
    protected String password;
    protected String firstname;
    protected String lastName;

    protected Set<Appointment> appointmentList;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
