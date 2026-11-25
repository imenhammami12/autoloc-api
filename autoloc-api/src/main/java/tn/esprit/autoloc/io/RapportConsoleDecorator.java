package tn.esprit.autoloc.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tn.esprit.autoloc.domain.Agence;

public class RapportConsoleDecorator extends RapportDecorator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RapportConsoleDecorator.class);

    public RapportConsoleDecorator(IRapport rapport) {
        super(rapport);
    }

    @Override
    public void ajouterEntete(Agence a) {
        LOGGER.info("=== Agence {} - {} - Tel : {} ===",
                a.getIdAgence(), a.getNom(), a.getTelephone());
        rapport.ajouterEntete(a);
    }
}