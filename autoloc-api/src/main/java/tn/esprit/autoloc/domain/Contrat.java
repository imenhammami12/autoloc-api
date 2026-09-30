package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "contrat")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    private LocalDate dateSignature;

    @Column(precision = 10, scale = 2)
    private BigDecimal montantTotal;

    private boolean valide;

    // Chargement du contrat => chargement des paiements
    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private List<Paiement> paiements = new ArrayList<>();

    // Côté inverse du OneToOne
    @OneToOne(mappedBy = "contrat")
    private Reservation reservation;
}