package fr.istic.tp3spring.service;

import fr.istic.tp3spring.domain.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface PatientDAO extends JpaRepository<Patient, Long> {

    /**
     * This method will allow to find a patient with his mail
     * @param mail
     * @return
     */
    public Patient findByMail(String mail);
}
