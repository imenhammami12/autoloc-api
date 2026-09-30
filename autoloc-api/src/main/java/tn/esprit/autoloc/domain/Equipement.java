package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "equipement")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;

    private String libelle;


    @ManyToMany(mappedBy = "equipements")
    @Builder.Default
    private Set<Vehicule> vehicules = new HashSet<>();
}