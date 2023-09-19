package fr.istic.tp3spring.dao;

import fr.istic.tp3spring.domain.Appointment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface AppointmentDAO extends JpaRepository<Appointment, Long> {

    /**
     * find appointment by id
     * @param id
     * @return
     */
    public Appointment getById(Long id);

    /**
     * Find all appointment linked to a pro
     * @param id
     * @return
     */
    public List<Appointment> getByProId(Long id);

    /**
     * Find all patient linked to a patient
     * @param id
     * @return
     */
    public List<Appointment> getByPatientId(Long id);
}
