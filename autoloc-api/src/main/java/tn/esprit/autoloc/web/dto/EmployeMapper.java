package tn.esprit.autoloc.web.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Employe;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeMapper {

    EmployeDTO toDto(Employe employe);

    @Mapping(target = "agence", ignore = true)
    Employe fromDto(EmployeDTO employeDTO);

    List<EmployeDTO> toDtoList(List<Employe> employes);

    List<Employe> fromDtoList(List<EmployeDTO> employeDTOs);
}