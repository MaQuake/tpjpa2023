package fr.istic.tp3spring.dao;

import fr.istic.tp3spring.domain.Professional;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface ProfessionalDAO extends JpaRepository<Professional, Long> {

    public Professional getById(Long id);
    /**
     * Find a professional by mail
     * @param mail
     * @return
     */
    public Professional getByMail(String mail);

    /**
     * find professional by profession
     * @param profession
     * @return
     */
    public List<Professional> getByProfession(String profession);




}
