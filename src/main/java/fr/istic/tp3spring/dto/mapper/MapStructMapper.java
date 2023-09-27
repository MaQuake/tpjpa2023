package fr.istic.tp3spring.dto.mapper;

import fr.istic.tp3spring.dao.PatientDAO;
import fr.istic.tp3spring.dao.ProfessionalDAO;
import fr.istic.tp3spring.domain.Appointment;
import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.domain.Professional;
import fr.istic.tp3spring.dto.AppointmentDTO;
import fr.istic.tp3spring.dto.PatientDTO;
import fr.istic.tp3spring.dto.ProfessionalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    MapStructMapper INSTANCE = Mappers.getMapper( MapStructMapper.class );


    PatientDTO patientToPatientDTO(Patient patient);

    ProfessionalDTO professionalToProfessionalDTO(Professional pro);

    @Mapping(source="appointment.pro.id",target = "proId")
    @Mapping(source="appointment.patient.id",target = "patientId")
    AppointmentDTO appointmentToAppointmentDTO(Appointment appointment);

    Appointment appointmentDTOToAppointment(AppointmentDTO appointmentDTO);

    Patient patientDTOToPatient(PatientDTO patientDTO);

    Professional professionalDTOToProfessional(ProfessionalDTO professionalDTO);
}
