package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IVehiculeService;
import tn.esprit.autoloc.web.dto.VehiculeDTO;
import tn.esprit.autoloc.web.dto.VehiculeMapper;

import java.util.List;

@Tag(name = "Véhicules", description = "Gestion des véhicules")
@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehiculeService vehiculeService;
    private final VehiculeMapper vehiculeMapper;

    @Operation(summary = "Lister les véhicules")
    @GetMapping
    public List<VehiculeDTO> findAll() {
        return vehiculeMapper.toDtoList(vehiculeService.findAll());
    }

    @Operation(summary = "Consulter un véhicule")
    @GetMapping("/{id}")
    public VehiculeDTO findById(@PathVariable Long id) {
        return vehiculeMapper.toDto(vehiculeService.findById(id));
    }

    @Operation(summary = "Créer un véhicule")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehiculeDTO create(@Valid @RequestBody VehiculeDTO vehiculeDTO) {
        return vehiculeMapper.toDto(vehiculeService.create(vehiculeMapper.fromDto(vehiculeDTO)));
    }

    @Operation(summary = "Modifier un véhicule")
    @PutMapping("/{id}")
    public VehiculeDTO update(@PathVariable Long id, @Valid @RequestBody VehiculeDTO vehiculeDTO) {
        return vehiculeMapper.toDto(vehiculeService.update(id, vehiculeMapper.fromDto(vehiculeDTO)));
    }

    @Operation(summary = "Supprimer un véhicule")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        vehiculeService.deleteById(id);
    }

    @PutMapping("/desaffecterAgence/{idVehicule}")
    public void desaffecterAgence(@PathVariable Long idVehicule) {
        vehiculeService.desaffecterAgence(idVehicule);
    }

    @PutMapping("/affecterAgence/{idVehicule}/{idAgence}")
    public VehiculeDTO affecterAgence(@PathVariable Long idVehicule, @PathVariable Long idAgence) {
        return vehiculeMapper.toDto(vehiculeService.affecterAgence(idVehicule, idAgence));
    }

    @PutMapping("/affecterEquipement/{idVehicule}/{idEquipement}")
    public VehiculeDTO affecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeMapper.toDto(vehiculeService.affecterEquipement(idVehicule, idEquipement));
    }
}