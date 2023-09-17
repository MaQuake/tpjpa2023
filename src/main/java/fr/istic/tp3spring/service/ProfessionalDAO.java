package fr.istic.tp3spring.service;

import fr.istic.tp3spring.domain.Appointment;
import fr.istic.tp3spring.domain.Professional;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface ProfessionalDAO extends JpaRepository<Professional, Long> {
    /**
     * Find a professional by mail
     * @param mail
     * @return
     */
    public Professional findByMail(String mail);

    /**
     * find professional by profession
     * @param profession
     * @return
     */
    public List<Professional> findByProfession(String profession);

    /**
     * Save a new professional
     * @param professional
     * @return
     */
    public Professional saveProfessional(Professional professional);

    /**
     * update a professional
     * @param professional
     * @param professionalId
     * @return
     */
    public Professional updateProfessionalById(Professional professional, Long professionalId);



}
