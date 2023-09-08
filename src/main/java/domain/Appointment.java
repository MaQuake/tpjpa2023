package domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Appointment {
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
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    public Patient getPatient() {
        return patient;
    }

    @ManyToOne
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
}
