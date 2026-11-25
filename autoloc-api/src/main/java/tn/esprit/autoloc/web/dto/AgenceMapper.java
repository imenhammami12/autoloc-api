package tn.esprit.autoloc.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Agence;

import java.util.List;

@Mapper(componentModel = "spring", uses = EmployeMapper.class)
public interface AgenceMapper {

    AgenceDTO toDto(Agence agence);

    Agence fromDto(AgenceDTO agenceDTO);

    Agence fromAvecEmployesDto(AgenceAvecEmployesDTO agenceDTO);

    List<AgenceDTO> toDtoList(List<Agence> agences);

    List<Agence> fromDtoList(List<AgenceDTO> agenceDTOs);
}