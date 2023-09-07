package domain;

import jakarta.persistence.*;
import org.hsqldb.lib.List;

import java.util.ArrayList;
@Entity
public class Professional extends User{

    private String profession;
    //List<Appointment> appointmentList = (List<Appointment>) new ArrayList<Appointment>();

    public Professional() {

    }


    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    /*@OneToMany(targetEntity= Professional.class, mappedBy = "pro", cascade = CascadeType.PERSIST)
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }*/
}
