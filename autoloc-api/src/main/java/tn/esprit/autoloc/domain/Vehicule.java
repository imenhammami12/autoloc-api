package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "vehicule")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    @Column(nullable = false, unique = true, length = 20)
    private String immatriculation;

    private String marque;
    private String modele;

    @Enumerated(EnumType.STRING)
    private CategorieVehicule categorie;

    @Column(precision = 10, scale = 2)
    private BigDecimal tarifJournalier;

    @Enumerated(EnumType.STRING)
    private StatutVehicule statut;

    // Le véhicule peut exister sans agence
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agence_id")
    private Agence agence;

    // Chargement non lié, suppression liée
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Reservation> reservations = new ArrayList<>();


    @ManyToMany
    @Builder.Default
    private Set<Equipement> equipements = new HashSet<>();
}