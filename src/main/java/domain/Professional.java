package domain;

import jakarta.persistence.*;
import org.hsqldb.lib.List;

import java.util.ArrayList;
import java.util.Set;

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
    public Set<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(Set<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
