package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IMaintenanceService;
import tn.esprit.autoloc.web.dto.MaintenanceDTO;
import tn.esprit.autoloc.web.dto.MaintenanceMapper;

import java.util.List;

@Tag(name = "Maintenances", description = "Gestion des maintenances")
@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;
    private final MaintenanceMapper maintenanceMapper;

    @Operation(summary = "Lister les maintenances")
    @GetMapping
    public List<MaintenanceDTO> findAll() {
        return maintenanceMapper.toDtoList(maintenanceService.findAll());
    }

    @Operation(summary = "Consulter une maintenance")
    @GetMapping("/{id}")
    public MaintenanceDTO findById(@PathVariable Long id) {
        return maintenanceMapper.toDto(maintenanceService.findById(id));
    }

    @Operation(summary = "Créer une maintenance")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceDTO create(@Valid @RequestBody MaintenanceDTO maintenanceDTO) {
        return maintenanceMapper.toDto(maintenanceService.create(maintenanceMapper.fromDto(maintenanceDTO)));
    }

    @Operation(summary = "Modifier une maintenance")
    @PutMapping("/{id}")
    public MaintenanceDTO update(@PathVariable Long id, @Valid @RequestBody MaintenanceDTO maintenanceDTO) {
        return maintenanceMapper.toDto(maintenanceService.update(id, maintenanceMapper.fromDto(maintenanceDTO)));
    }

    @Operation(summary = "Supprimer une maintenance")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        maintenanceService.deleteById(id);
    }
}