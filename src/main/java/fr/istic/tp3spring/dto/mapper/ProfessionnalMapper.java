package fr.istic.tp3spring.dto.mapper;

import fr.istic.tp3spring.dao.ProfessionalDAO;
import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.domain.Professional;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

/**
 * This class allow the mapping of id to Professional
 */
@Mapper(componentModel = "spring", uses = {ReferenceMapper.class})
public interface ProfessionnalMapper  {

    Professional toEntity(Long id);
    @Named("mapPro")
    Professional map(Long id);
}
