package tn.esprit.autoloc.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Client;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO toDto(Client entity);

    Client fromDto(ClientDTO dto);

    List<ClientDTO> toDtoList(List<Client> entities);

    List<Client> fromDtoList(List<ClientDTO> dtos);
}