package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IEquipementService;
import tn.esprit.autoloc.web.dto.EquipementDTO;
import tn.esprit.autoloc.web.dto.EquipementMapper;

import java.util.List;

@Tag(name = "Équipements", description = "Gestion des équipements")
@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
public class EquipementController {

    private final IEquipementService equipementService;
    private final EquipementMapper equipementMapper;

    @Operation(summary = "Lister les équipements")
    @GetMapping
    public List<EquipementDTO> findAll() {
        return equipementMapper.toDtoList(equipementService.findAll());
    }

    @Operation(summary = "Consulter un équipement")
    @GetMapping("/{id}")
    public EquipementDTO findById(@PathVariable Long id) {
        return equipementMapper.toDto(equipementService.findById(id));
    }

    @Operation(summary = "Créer un équipement")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipementDTO create(@Valid @RequestBody EquipementDTO equipementDTO) {
        return equipementMapper.toDto(equipementService.create(equipementMapper.fromDto(equipementDTO)));
    }

    @Operation(summary = "Modifier un équipement")
    @PutMapping("/{id}")
    public EquipementDTO update(@PathVariable Long id, @Valid @RequestBody EquipementDTO equipementDTO) {
        return equipementMapper.toDto(equipementService.update(id, equipementMapper.fromDto(equipementDTO)));
    }

    @Operation(summary = "Supprimer un équipement")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        equipementService.deleteById(id);
    }
}