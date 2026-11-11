package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@Tag(name = "Véhicules", description = "Gestion des véhicules")
@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @Operation(summary = "Lister les véhicules")
    @GetMapping
    public List<Vehicule> findAll() { return vehiculeService.findAll(); }

    @Operation(summary = "Consulter un véhicule")
    @GetMapping("/{id}")
    public Vehicule findById(@PathVariable Long id) { return vehiculeService.findById(id); }

    @Operation(summary = "Créer un véhicule")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicule create(@RequestBody Vehicule vehicule) { return vehiculeService.create(vehicule); }

    @Operation(summary = "Modifier un véhicule")
    @PutMapping("/{id}")
    public Vehicule update(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return vehiculeService.update(id, vehicule);
    }

    @Operation(summary = "Supprimer un véhicule")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) { vehiculeService.deleteById(id); }

    @PutMapping("/desaffecterAgence/{idVehicule}")
    public void desaffecterAgence(@PathVariable Long idVehicule) {
        vehiculeService.desaffecterAgence(idVehicule);
    }

    @PutMapping("/affecterAgence/{idVehicule}/{idAgence}")
    public Vehicule affecterAgence(@PathVariable Long idVehicule, @PathVariable Long idAgence) {
        return vehiculeService.affecterAgence(idVehicule, idAgence);
    }

    @PutMapping("/affecterEquipement/{idVehicule}/{idEquipement}")
    public Vehicule affecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.affecterEquipement(idVehicule, idEquipement);
    }
}