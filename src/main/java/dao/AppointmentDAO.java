package dao;

import dao.generic.AbstractJpaDao;
import domain.Appointment;

public class AppointmentDAO extends AbstractJpaDao {

    public AppointmentDAO(){
        this.setClazz(Appointment.class);
    }
}
