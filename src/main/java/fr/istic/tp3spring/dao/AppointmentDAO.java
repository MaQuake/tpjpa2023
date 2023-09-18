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
     * Save an appointment
     * @param appointment
     * @return
     */
    //public Appointment saveAppointment(Appointment appointment);

    /**
     * Update an appointment
     * @param appointment
     * @param appointmentId
     * @return
     */
    //public Appointment updateAppointment(Appointment appointment, Long appointmentId);

    /**
     * delete aan appointment by Id
     * @param AppointmentId
     */
    //public void deleteAppointmentById(long appointmentId);

}
