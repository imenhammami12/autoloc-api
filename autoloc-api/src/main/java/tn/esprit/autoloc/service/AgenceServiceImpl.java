package tn.esprit.autoloc.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.io.IRapport;
import tn.esprit.autoloc.io.RapportFactory;
import tn.esprit.autoloc.repository.IAgenceRepository;

@Service
@Primary
public class AgenceServiceImpl extends GenericServiceImpl<Agence, Long>
        implements IAgenceService, IAgenceSimulationService {

    private final IVehiculeService vehiculeService;
    private final RapportFactory rapportFactory;

    public AgenceServiceImpl(IAgenceRepository repository,
                             IVehiculeService vehiculeService,
                             RapportFactory rapportFactory) {
        super(repository);
        this.vehiculeService = vehiculeService;
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

    // Signature inchangée : le rapport vient de la Factory
    @Override
    public String simuler(Agence agence, int nbJours, VehiculeOffre offre) {
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
}