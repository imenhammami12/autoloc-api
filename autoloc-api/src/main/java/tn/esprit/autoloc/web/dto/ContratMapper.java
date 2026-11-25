package tn.esprit.autoloc.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Contrat;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContratMapper {

    ContratDTO toDto(Contrat entity);

    Contrat fromDto(ContratDTO dto);

    List<ContratDTO> toDtoList(List<Contrat> entities);

    List<Contrat> fromDtoList(List<ContratDTO> dtos);
}