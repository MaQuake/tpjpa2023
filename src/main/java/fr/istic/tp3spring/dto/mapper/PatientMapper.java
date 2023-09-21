package fr.istic.tp3spring.dto.mapper;

import fr.istic.tp3spring.domain.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * This class allow the mapping of id to Patient
 */
@Mapper(componentModel = "spring", uses = {ReferenceMapper.class})
public interface PatientMapper  {
    Patient toEntity(Long id);
    @Named("mapPatient")
    Patient map(Long id);
}
