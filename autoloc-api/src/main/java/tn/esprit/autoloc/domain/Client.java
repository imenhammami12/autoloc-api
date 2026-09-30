package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nom;
    private String prenom;

    @Column(unique = true)
    private String email;

    private String telephone;
    private String numPermis;
    private LocalDate dateInscription;

    @OneToMany(mappedBy = "client", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @Builder.Default
    private List<Reservation> reservations = new ArrayList<>();
}