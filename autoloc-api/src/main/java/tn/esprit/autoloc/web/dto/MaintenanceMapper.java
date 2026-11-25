package tn.esprit.autoloc.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {

    MaintenanceDTO toDto(Maintenance entity);

    Maintenance fromDto(MaintenanceDTO dto);

    List<MaintenanceDTO> toDtoList(List<Maintenance> entities);

    List<Maintenance> fromDtoList(List<MaintenanceDTO> dtos);
}