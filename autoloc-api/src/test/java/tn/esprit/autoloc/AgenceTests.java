package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.domain.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class AgenceTests {

    @Autowired
    private AgenceRepositoryMock agenceRepository;

    @Test
    public void addAgence() {
        Agence agence = Agence.builder()
                .adresse("1 Rue Hedi")
                .nom("Agence ariana")
                .telephone("71585874")
                .ville("Tunis")
                .build();

        Vehicule v1 = Vehicule.builder()
                .categorie(CategorieVehicule.SUV)
                .immatriculation("785414TU96")
                .marque("Isuzu")
                .modele("DMax")
                .statut(StatutVehicule.MAINTENANCE)
                .tarifJournalier(new BigDecimal("100"))
                .build();

        Vehicule v2 = Vehicule.builder()
                .categorie(CategorieVehicule.UTILITAIRE)
                .immatriculation("785414TU95")
                .marque("Toyota")
                .modele("Yaris")
                .statut(StatutVehicule.DISPONIBLE)
                .tarifJournalier(new BigDecimal("80"))
                .build();

        agence.addVehicule(v1);
        agence.addVehicule(v2);

        agenceRepository.save(agence);
    }

    @Test
    public void loadAgence() {
        StringBuilder sb = new StringBuilder("\n");

        for (Agence a : agenceRepository.findAll()) {
            sb.append(a.getIdAgence()).append(" | ").append(a.getNom()).append("\n");
            sb.append("Vehicules Count : ").append(a.getVehicules().size()).append("\n");
            for (Vehicule v : a.getVehicules()) {
                sb.append("=== ").append(v.getIdVehicule())
                        .append("|").append(v.getImmatriculation()).append("\n");
            }
        }

        fail(sb.toString());
    }
}

interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}