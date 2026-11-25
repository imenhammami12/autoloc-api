package tn.esprit.autoloc.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Equipement;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipementMapper {

    EquipementDTO toDto(Equipement entity);

    Equipement fromDto(EquipementDTO dto);

    List<EquipementDTO> toDtoList(List<Equipement> entities);

    List<Equipement> fromDtoList(List<EquipementDTO> dtos);
}