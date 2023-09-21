package fr.istic.tp3spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.istic.tp3spring.domain.Appointment;
import lombok.*;

import java.util.List;


public class PatientDTO {

    private long id;

    private String mail;

    private String firstname;

    private String lastname;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    /*@JsonProperty("appointmentList")
    private List<Appointment> appointmentList;*/

}
