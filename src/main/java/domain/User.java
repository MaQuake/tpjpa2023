package domain;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class User implements Serializable {

    private long id;
    protected   String mail;
    protected String password;
    protected String firstname;
    protected String lastName;

    protected List<Appointment> appointmentList =  new ArrayList<Appointment>();;

    @Id
    @GeneratedValue
    @XmlElement(name = "id")
    public long getId() {
        return id;
    }

    @XmlElement(name = "mail")
    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    @XmlElement(name = "lastname")
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
