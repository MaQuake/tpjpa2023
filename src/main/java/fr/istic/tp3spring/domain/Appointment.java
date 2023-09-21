package fr.istic.tp3spring.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.TemporalType;
import org.springframework.data.jpa.repository.Temporal;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Appointment implements Serializable {

    private Date appointmentDate;
    private int duration;
    private String name;

    private Patient patient;
    private Professional pro;
    private Long id;

    public Appointment() {}

    public Appointment(Date appointmentDate, int duration, String name, Patient patient, Professional pro) {
        this.appointmentDate = appointmentDate;
        this.duration = duration;
        this.name = name;
        this.patient = patient;
        this.pro = pro;
    }

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
    @JsonIgnore
    public Patient getPatient() {
        return patient;
    }

    @ManyToOne
    @JsonIgnore
    public Professional getPro() {
        return pro;
    }


    public int getDuration() {
        return duration;
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
