package domain;

import jakarta.persistence.*;
import org.hsqldb.lib.List;

@Entity
public class User {

    private long id;
    private  String mail;
    private String password;
    private String firstname;
    private String lastName;

    private List<Appointment> appointmentList;

    public User() {
    }

    public User(String mail, String password, String firstname, String lastName) {
        this.mail = mail;
        this.password = password;
        this.firstname = firstname;
        this.lastName = lastName;
    }

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
    @OneToMany(mappedBy = "patient", cascade = CascadeType.PERSIST)
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
