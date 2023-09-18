package fr.istic.tp3spring.dto.mapper;

import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.domain.Professional;
import fr.istic.tp3spring.dto.PatientDTO;
import fr.istic.tp3spring.dto.ProfessionalDTO;

public interface MapStructMapper {

    PatientDTO patientToPatientDTO(Patient patient);

    ProfessionalDTO professionalToProfessionalDTO(Professional pro);
}
