package fr.istic.tp3spring.service;

import fr.istic.tp3spring.domain.Appointment;
import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.domain.Professional;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface PatientDAO extends JpaRepository<Patient, Long> {

    /**
     * This method will allow to find a patient with his mail
     * @param mail
     * @return
     */
    public Patient findByMail(String mail);

    /**
     * create a new patient
     * @param patient
     * @return
     */
    public Patient savePatient(Patient patient);

    /**
     * update Patient
     * @param patient
     * @param patientId
     * @return
     */
    public Patient updatePatientById(Patient patient, Long patientId);

    /**
     * delete a patient By Id
     * @param patientId
     */
    public void deletePatientById(long patientId);


}
