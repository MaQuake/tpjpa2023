package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.List;


@Entity
@XmlRootElement(name = "Patient")
public class Patient extends User {

    public Patient(String mail, String password, String firstname, String lastName) {
        this.mail = mail;
        this.password = password;
        this.firstname = firstname;
        this.lastName = lastName;
    }

    public Patient(){
    }


    @OneToMany(mappedBy = "patient", cascade = CascadeType.PERSIST)
    public List<Appointment> getAppointmentList() {
        return  appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public String toString() {
        return "[" + this.firstname + ", " + this.lastName + ", " + this.mail + "]";
    }


}
