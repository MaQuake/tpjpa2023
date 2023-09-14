package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.List;


@Entity
@XmlRootElement(name = "Profesional")
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

    @XmlElement(name = "profession")
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
