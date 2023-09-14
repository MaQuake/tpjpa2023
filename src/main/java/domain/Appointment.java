package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.util.Date;

@Entity
@XmlRootElement(name = "Appointment")
public class Appointment implements Serializable {
    long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentDate;

    private int duration;
    private String name;

    private Patient patient;
    private Professional pro;

    public Appointment() {}

    public Appointment(Date appointmentDate, int duration, String name, Patient patient, Professional pro) {
        this.appointmentDate = appointmentDate;
        this.duration = duration;
        this.name = name;
        this.patient = patient;
        this.pro = pro;
    }

    @Id
    @GeneratedValue
    @XmlElement(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlElement(name = "date")
    public Date getAppointmentDate(){
        return this.appointmentDate;
    }

    public void setAppointmentDate(Date date){
        this.appointmentDate = date;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setPro(Professional pro) {
        this.pro = pro;
    }

    @ManyToOne
    @XmlTransient
    @JsonIgnore
    public Patient getPatient() {
        return patient;
    }

    @ManyToOne
    @XmlTransient
    @JsonIgnore
    public Professional getPro() {
        return pro;
    }

    @XmlElement(name = "durée")

    public int getDuration() {
        return duration;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rdv at " + this.appointmentDate.toString() + " with "+ patient.firstname + " and " + pro.firstname;
    }
}
