package fr.istic.tp3spring.dto.mapper;

import fr.istic.tp3spring.domain.Appointment;
import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.domain.Professional;
import fr.istic.tp3spring.dto.AppointmentDTO;
import fr.istic.tp3spring.dto.PatientDTO;
import fr.istic.tp3spring.dto.ProfessionalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructMapper {
    MapStructMapper INSTANCE = Mappers.getMapper( MapStructMapper.class );

    PatientDTO patientToPatientDTO(Patient patient);

    ProfessionalDTO professionalToProfessionalDTO(Professional pro);

    @Mapping(source="appointment.pro.id",target = "proId")
    @Mapping(source="appointment.patient.id",target = "patientId")
    AppointmentDTO appointmentToAppointmentDTO(Appointment appointment);
}
