package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.domain.*;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.service.IAgenceService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class AgenceTests {

    @Autowired
    private AgenceRepositoryMock basicAgenceRepository;

    @Autowired
    private IAgenceRepository fullAgenceRepository;

    @Autowired
    private IAgenceService agenceService;

    // ---------- AJOUT ----------

    private void addAgence(CrudRepository<Agence, Long> repository) {
        int suffixe = Math.abs((int) System.currentTimeMillis());

        Agence agence = Agence.builder()
                .adresse("1 Rue Hedi")
                .nom("Agence ariana")
                .telephone("71585874")
                .ville("Tunis")
                .build();

        Vehicule v1 = Vehicule.builder()
                .categorie(CategorieVehicule.SUV)
                .immatriculation("785414TU96" + suffixe)
                .marque("Isuzu")
                .modele("DMax")
                .statut(StatutVehicule.MAINTENANCE)
                .tarifJournalier(new BigDecimal("100"))
                .build();

        Vehicule v2 = Vehicule.builder()
                .categorie(CategorieVehicule.UTILITAIRE)
                .immatriculation("785414TU95" + suffixe)
                .marque("Toyota")
                .modele("Yaris")
                .statut(StatutVehicule.DISPONIBLE)
                .tarifJournalier(new BigDecimal("80"))
                .build();

        agence.addVehicule(v1);
        agence.addVehicule(v2);

        repository.save(agence);
    }

    @Test
    public void basicAddAgence() {
        addAgence(basicAgenceRepository);
    }

    @Test
    public void fullAddAgence() {
        addAgence(fullAgenceRepository);
    }

    // ---------- CHARGEMENT ----------

    private void loadAgence(CrudRepository<Agence, Long> repository, String typeDepot) {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("Type de dépôt : ").append(typeDepot).append("\n");

        for (Agence a : repository.findAll()) {
            sb.append(a.getIdAgence()).append(" | ").append(a.getNom()).append("\n");
            sb.append("Vehicules Count : ").append(a.getVehicules().size()).append("\n");
            for (Vehicule v : a.getVehicules()) {
                sb.append("=== ").append(v.getIdVehicule())
                        .append("|").append(v.getImmatriculation()).append("\n");
            }
        }

        fail(sb.toString());
    }

    @Test
    public void basicLoadAgence() {
        loadAgence(basicAgenceRepository, "CrudRepository (basic)");
    }

    @Test
    public void fullLoadAgence() {
        loadAgence(fullAgenceRepository, "JpaRepository (full)");
    }

    // ---------- TRI ----------

    @Test
    public void loadSortedAgences() {
        StringBuilder sb = new StringBuilder("\n");

        List<Agence> agences = fullAgenceRepository.findAll(Sort.by(Sort.Direction.DESC, "idAgence"));

        for (Agence a : agences) {
            sb.append(a.getIdAgence()).append(" | ").append(a.getNom()).append("\n");
        }

        fail(sb.toString());
    }

    // ---------- PAGINATION ----------

    @Test
    public void loadPagedAgences() {
        StringBuilder sb = new StringBuilder("\n");

        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "idAgence"));
        Page<Agence> page = fullAgenceRepository.findAll(pageable);

        sb.append("Total pages : ").append(page.getTotalPages()).append("\n");
        sb.append("Page en cours : ").append(page.getNumber()).append("\n");

        for (Agence a : page.getContent()) {
            sb.append(a.getIdAgence()).append(" | ").append(a.getNom()).append("\n");
        }

        fail(sb.toString());
    }

    // ---------- CHAPITRE 5 : SERVICE ----------

    @Test
    public void createAgence() {
        Agence agence = Agence.builder()
                .adresse("5 Avenue Habib Bourguiba")
                .nom("Agence Sousse")
                .telephone("73100200")
                .ville("Sousse")
                .build();

        Agence saved = agenceService.create(agence);

        assertNotNull(saved.getIdAgence());
    }
}

@Repository
interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}