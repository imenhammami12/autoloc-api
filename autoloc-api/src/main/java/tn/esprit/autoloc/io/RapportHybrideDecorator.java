package tn.esprit.autoloc.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tn.esprit.autoloc.domain.Agence;

public class RapportHybrideDecorator extends RapportDecorator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RapportHybrideDecorator.class);

    public RapportHybrideDecorator(IRapport rapport) {
        super(rapport);
    }

    @Override
    public void ajouterEntete(Agence a) {
        LOGGER.info("=== Agence {} - {} - Tel : {} ===",
                a.getIdAgence(), a.getNom(), a.getTelephone());
    }

    @Override
    public void ajouterLigne(String texte) {
        // Volontairement vide : ce décorateur n'ajoute pas de lignes de détail au rapport
    }
}