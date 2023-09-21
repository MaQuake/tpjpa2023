package fr.istic.tp3spring.dto;

import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.domain.Professional;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AppointmentDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appointmentDate;
    private int duration;
    private String name;
    private long patientId;
    private long proId;

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getProId() {
        return proId;
    }

    public void setProId(long proId) {
        this.proId = proId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
}
