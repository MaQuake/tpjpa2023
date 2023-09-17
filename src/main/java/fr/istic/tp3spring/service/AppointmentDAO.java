package fr.istic.tp3spring.service;

import fr.istic.tp3spring.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentDAO extends JpaRepository<Appointment, Long> {

    /**
     * find appointment by id
     * @param id
     * @return
     */
    public Appointment findById(String id);

    /**
     * Save a list of appointment
     * @param appointmentList
     * @return
     */
    public List<Appointment> saveAll(List<Appointment> appointmentList);

    /**
     * Save an appointment
     * @param appointment
     * @return
     */
    public Appointment saveAppointment(Appointment appointment);

    /**
     * Update an appointment
     * @param appointment
     * @param appointmentId
     * @return
     */
    public Appointment updateAppointment(Appointment appointment, Long appointmentId);

    /**
     * delete aan appointment by Id
     * @param AppointmentId
     */
    public void deleteAppointmentById(long appointmentId);

}
