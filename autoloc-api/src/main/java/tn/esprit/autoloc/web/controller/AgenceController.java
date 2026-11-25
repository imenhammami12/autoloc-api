package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;
import tn.esprit.autoloc.service.IAgenceSimulationService;
import tn.esprit.autoloc.web.dto.AgenceAvecEmployesDTO;
import tn.esprit.autoloc.web.dto.AgenceDTO;
import tn.esprit.autoloc.web.dto.AgenceMapper;

import java.util.List;

@Tag(name = "Agences", description = "Gestion des agences de location")
@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;
    private final IAgenceSimulationService simulationService;
    private final AgenceMapper agenceMapper;

    @Operation(summary = "Lister les agences")
    @GetMapping
    public List<AgenceDTO> findAll() {
        return agenceMapper.toDtoList(agenceService.findAll());
    }

    @Operation(summary = "Consulter une agence")
    @ApiResponse(responseCode = "200", description = "Agence trouvée")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @GetMapping("/{id}")
    public AgenceDTO findById(@Parameter(description = "Identifiant de l'agence") @PathVariable Long id) {
        return agenceMapper.toDto(agenceService.findById(id));
    }

    @Operation(summary = "Créer une agence")
    @ApiResponse(responseCode = "201", description = "Agence créée")
    @ApiResponse(responseCode = "400", description = "Données invalides ou identifiant fourni")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgenceDTO create(@Valid @RequestBody AgenceDTO agenceDTO) {
        Agence agence = agenceService.create(agenceMapper.fromDto(agenceDTO));
        return agenceMapper.toDto(agence);
    }

    @Operation(summary = "Modifier une agence")
    @ApiResponse(responseCode = "200", description = "Agence modifiée")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @PutMapping("/{id}")
    public AgenceDTO update(@Parameter(description = "Identifiant de l'agence") @PathVariable Long id,
                            @Valid @RequestBody AgenceDTO agenceDTO) {
        Agence agence = agenceService.update(id, agenceMapper.fromDto(agenceDTO));
        return agenceMapper.toDto(agence);
    }

    @Operation(summary = "Supprimer une agence")
    @ApiResponse(responseCode = "204", description = "Agence supprimée")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "Identifiant de l'agence") @PathVariable Long id) {
        agenceService.deleteById(id);
    }

    @Operation(summary = "Simuler le coût de location des véhicules d'une agence")
    @GetMapping("/{id}/simulation")
    public String simuler(@PathVariable Long id,
                          @RequestParam("jours") int nbJours,
                          @RequestParam VehiculeOffre offre) {
        Agence agence = agenceService.findById(id);
        return simulationService.simuler(agence, nbJours, offre);
    }

    @Operation(summary = "Mettre à jour une agence et ajouter une liste d'employés")
    @PutMapping("/updateAvecEmployes/{id}")
    public AgenceDTO updateAvecEmployes(@PathVariable Long id,
                                        @Valid @RequestBody AgenceAvecEmployesDTO agenceDTO) {
        Agence agence = agenceService.updateAvecEmployes(id, agenceMapper.fromAvecEmployesDto(agenceDTO));
        return agenceMapper.toDto(agence);
    }
}