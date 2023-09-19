package fr.istic.tp3spring.dao;

import fr.istic.tp3spring.domain.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface PatientDAO extends JpaRepository<Patient, Long> {


    public Patient getById(Long id);
    /**
     * This method will allow to find a patient with his mail
     * @param mail
     * @return
     */
    public Patient getByMail(String mail);




}
