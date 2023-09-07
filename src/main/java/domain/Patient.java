package domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.hsqldb.lib.List;

import java.util.ArrayList;

@Entity
public class Patient extends User {

    public Patient(String mail, String password, String firstname, String lastName) {
        this.mail = mail;
        this.password = password;
        this.firstname = firstname;
        this.lastName = lastName;
    }

    public Patient(){
    }




    /*@OneToMany(mappedBy = "patient", cascade = CascadeType.PERSIST)
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }*/
}
