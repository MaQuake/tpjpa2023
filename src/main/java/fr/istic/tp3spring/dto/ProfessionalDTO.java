package fr.istic.tp3spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.istic.tp3spring.domain.Appointment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfessionalDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("profession")
    private String profession;

    @JsonProperty("mail")
    private String mail;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("appointmentList")
    private List<Appointment> appointmentList;
}
