package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IContratService;
import tn.esprit.autoloc.web.dto.ContratDTO;
import tn.esprit.autoloc.web.dto.ContratMapper;

import java.util.List;

@Tag(name = "Contrats", description = "Gestion des contrats de location")
@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContratService contratService;
    private final ContratMapper contratMapper;

    @Operation(summary = "Lister les contrats")
    @GetMapping
    public List<ContratDTO> findAll() {
        return contratMapper.toDtoList(contratService.findAll());
    }

    @Operation(summary = "Consulter un contrat")
    @GetMapping("/{id}")
    public ContratDTO findById(@PathVariable Long id) {
        return contratMapper.toDto(contratService.findById(id));
    }

    @Operation(summary = "Créer un contrat")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContratDTO create(@Valid @RequestBody ContratDTO contratDTO) {
        return contratMapper.toDto(contratService.create(contratMapper.fromDto(contratDTO)));
    }

    @Operation(summary = "Modifier un contrat")
    @PutMapping("/{id}")
    public ContratDTO update(@PathVariable Long id, @Valid @RequestBody ContratDTO contratDTO) {
        return contratMapper.toDto(contratService.update(id, contratMapper.fromDto(contratDTO)));
    }

    @Operation(summary = "Supprimer un contrat")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        contratService.deleteById(id);
    }
}