package dao;

import dao.generic.AbstractJpaDao;
import domain.Patient;

public class PatientDAO extends AbstractJpaDao {

    public PatientDAO(){
        this.setClazz(Patient.class);
    }
}
