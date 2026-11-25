package tn.esprit.autoloc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.io.IRapport;
import tn.esprit.autoloc.io.RapportFactory;
import tn.esprit.autoloc.repository.IAgenceRepository;

@Service
@Primary
public class AgenceServiceImpl extends GenericServiceImpl<Agence, Long>
        implements IAgenceService, IAgenceSimulationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgenceServiceImpl.class);
    private static final String AGENCE_INTROUVABLE = "Agence introuvable";

    private final IVehiculeService vehiculeService;
    private final IEmployeService employeService;
    private final RapportFactory rapportFactory;

    public AgenceServiceImpl(IAgenceRepository repository,
                             IVehiculeService vehiculeService,
                             IEmployeService employeService,
                             RapportFactory rapportFactory) {
        super(repository);
        this.vehiculeService = vehiculeService;
        this.employeService = employeService;
        this.rapportFactory = rapportFactory;
    }

    @Override
    protected String getEntityName() {
        return "Agence";
    }

    @Override
    protected Long getId(Agence agence) {
        return agence.getIdAgence();
    }

    @Override
    protected void setFields(Agence source, Agence target) {
        target.setNom(source.getNom());
        target.setAdresse(source.getAdresse());
        target.setVille(source.getVille());
        target.setTelephone(source.getTelephone());
    }

    @Override
    public boolean check(Agence agence, boolean isNew) {
        return agence != null
                && Utils.isNotEmpty(agence.getNom())
                && Utils.isNotEmpty(agence.getAdresse())
                && Utils.isNotEmpty(agence.getVille())
                && Utils.isNotEmpty(agence.getTelephone());
    }

    @Override
    public boolean hasVehicules(Long id) {
        return !findById(id).getVehicules().isEmpty();
    }

    @Override
    public void deleteById(Long id) {
        findById(id).getVehicules().forEach(v -> v.setAgence(null));
        super.deleteById(id);
    }

    @Override
    public Agence updateAvecEmployes(Long id, Agence agence) {
        // 1. Mettre à jour d'abord l'agence
        Agence existing = update(id, agence);

        // 2. Utiliser le service des employés pour insérer les employés
        for (Employe e : agence.getEmployes()) {
            existing.addEmploye(e);
            employeService.create(e);
        }

        // 3. Mettre à jour l'effectif avec la taille de la liste des employés de l'agence
        existing.setEffectif(existing.getEmployes().size());
        return repository.save(existing);
    }

    @Override
    public String simuler(Agence agence, int nbJours, VehiculeOffre offre) {
        LOGGER.info("Simulation sur {} jours", nbJours);

        IRapport rapport = rapportFactory.creer();
        rapport.ajouterEntete(agence);

        double total = 0;
        for (Vehicule vehicule : agence.getVehicules()) {
            double tarif = vehiculeService.calculTarif(vehicule, nbJours, offre);
            rapport.ajouterLigne(vehicule.getImmatriculation() + " : " + tarif);
            total += tarif;
        }

        rapport.ajouterTotal(total);
        return rapport.generer();
    }

    // ---------- Méthodes ajoutées pour l'analyse ----------

    public boolean estGrande(Agence agence) {
        return agence.getEmployes().size() > 10;
    }

    public String messages() {
        return AGENCE_INTROUVABLE + AGENCE_INTROUVABLE + AGENCE_INTROUVABLE;
    }
}