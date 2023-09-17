package fr.istic.tp3spring.domain;

import fr.istic.tp3spring.domain.Appointment;
import jakarta.persistence.*;


import java.util.List;


@Entity
public class Professional extends User{

    private String profession;

    public Professional() {
        this.profession = "unknow";
    }
    public Professional(String profession) {
        this.profession = profession;
    }

    public Professional(String mail, String password, String firstname, String lastName,String profession) {
        this.mail = mail;
        this.password = password;
        this.firstname = firstname;
        this.lastName = lastName;
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @OneToMany(mappedBy = "pro", cascade = CascadeType.PERSIST)
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public String toString() {
        return "[" + this.firstname + ", " + this.lastName + ", " + this.mail + ", " + this.profession +"]";
    }
}
