package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IClientService;
import tn.esprit.autoloc.web.dto.ClientDTO;
import tn.esprit.autoloc.web.dto.ClientMapper;

import java.util.List;

@Tag(name = "Clients", description = "Gestion des clients")
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;
    private final ClientMapper clientMapper;

    @Operation(summary = "Lister les clients")
    @GetMapping
    public List<ClientDTO> findAll() {
        return clientMapper.toDtoList(clientService.findAll());
    }

    @Operation(summary = "Consulter un client")
    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable Long id) {
        return clientMapper.toDto(clientService.findById(id));
    }

    @Operation(summary = "Créer un client")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO create(@Valid @RequestBody ClientDTO clientDTO) {
        return clientMapper.toDto(clientService.create(clientMapper.fromDto(clientDTO)));
    }

    @Operation(summary = "Modifier un client")
    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
        return clientMapper.toDto(clientService.update(id, clientMapper.fromDto(clientDTO)));
    }

    @Operation(summary = "Supprimer un client")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }
}