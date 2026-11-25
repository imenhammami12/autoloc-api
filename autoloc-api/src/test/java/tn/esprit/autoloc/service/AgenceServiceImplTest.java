package tn.esprit.autoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.io.IRapport;
import tn.esprit.autoloc.io.RapportFactory;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgenceServiceImplTest {

    @Mock
    private IAgenceRepository repository;
    @Mock
    private IVehiculeService vehiculeService;
    @Mock
    private IEmployeService employeService;
    @Mock
    private RapportFactory rapportFactory;
    @Mock
    private IRapport rapport;

    private AgenceServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new AgenceServiceImpl(repository, vehiculeService, employeService, rapportFactory);
    }

    private Agence agenceValide() {
        return Agence.builder()
                .adresse("1 Rue Hedi")
                .nom("Agence Ariana")
                .telephone("71585874")
                .ville("Tunis")
                .build();
    }

    @Test
    void createAgenceValide() {
        Agence agence = agenceValide();
        when(repository.save(agence)).thenReturn(agence);

        Agence result = service.create(agence);

        assertEquals("Agence Ariana", result.getNom());
        verify(repository).save(agence);
    }

    @Test
    void createAgenceAvecIdLeveException() {
        Agence agence = agenceValide();
        agence.setIdAgence(1L);

        assertThrows(IllegalArgumentException.class, () -> service.create(agence));
        verify(repository, never()).save(any());
    }

    @Test
    void createAgenceInvalideLeveException() {
        Agence agence = Agence.builder().nom("").build();

        assertThrows(IllegalArgumentException.class, () -> service.create(agence));
        verify(repository, never()).save(any());
    }

    @Test
    void findByIdExistant() {
        Agence agence = agenceValide();
        when(repository.findById(1L)).thenReturn(Optional.of(agence));

        assertSame(agence, service.findById(1L));
    }

    @Test
    void findByIdIntrouvable() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(99L));
    }

    @Test
    void updateAgence() {
        Agence existing = agenceValide();
        Agence modif = agenceValide();
        modif.setNom("Nouveau nom");
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        Agence result = service.update(1L, modif);

        assertEquals("Nouveau nom", result.getNom());
    }

    @Test
    void deleteByIdIntrouvable() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.deleteById(99L));
    }

    @Test
    void deleteByIdDetacheLesVehicules() {
        Agence agence = agenceValide();
        Vehicule v = Vehicule.builder()
                .categorie(CategorieVehicule.SUV)
                .immatriculation("123TU1")
                .marque("Toyota")
                .modele("Yaris")
                .statut(StatutVehicule.DISPONIBLE)
                .tarifJournalier(new BigDecimal("80"))
                .build();
        agence.addVehicule(v);
        when(repository.findById(1L)).thenReturn(Optional.of(agence));
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteById(1L);

        assertNull(v.getAgence());
        verify(repository).deleteById(1L);
    }

    @Test
    void hasVehiculesFaux() {
        when(repository.findById(1L)).thenReturn(Optional.of(agenceValide()));

        assertFalse(service.hasVehicules(1L));
    }

    @Test
    void estGrandeFaux() {
        assertFalse(service.estGrande(agenceValide()));
    }

    @Test
    void simulerGenereUnRapport() {
        Agence agence = agenceValide();
        Vehicule v = Vehicule.builder()
                .categorie(CategorieVehicule.SUV)
                .immatriculation("123TU1")
                .marque("Toyota")
                .modele("Yaris")
                .statut(StatutVehicule.DISPONIBLE)
                .tarifJournalier(new BigDecimal("80"))
                .build();
        agence.addVehicule(v);
        when(rapportFactory.creer()).thenReturn(rapport);
        when(vehiculeService.calculTarif(v, 3, VehiculeOffre.STANDARD)).thenReturn(240.0);
        when(rapport.generer()).thenReturn("RAPPORT");

        String result = service.simuler(agence, 3, VehiculeOffre.STANDARD);

        assertEquals("RAPPORT", result);
        verify(rapport).ajouterTotal(240.0);
    }

    @Test
    void messagesContientTroisFois() {
        assertEquals("Agence introuvableAgence introuvableAgence introuvable", service.messages());
    }
}