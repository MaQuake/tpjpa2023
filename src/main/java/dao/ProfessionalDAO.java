package dao;

import dao.generic.AbstractJpaDao;
import domain.Patient;

public class ProfessionalDAO extends AbstractJpaDao {

    public ProfessionalDAO(){
        this.setClazz(ProfessionalDAO.class);
    }
}
