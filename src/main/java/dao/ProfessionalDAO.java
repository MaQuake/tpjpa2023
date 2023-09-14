package dao;

import dao.generic.AbstractJpaDao;
import domain.Professional;

public class ProfessionalDAO extends AbstractJpaDao {

    public ProfessionalDAO(){
        this.setClazz(Professional.class);
    }
}
