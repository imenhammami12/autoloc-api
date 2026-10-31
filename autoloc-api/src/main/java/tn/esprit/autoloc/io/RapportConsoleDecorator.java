package tn.esprit.autoloc.io;

import tn.esprit.autoloc.domain.Agence;

public class RapportConsoleDecorator extends RapportDecorator {

    public RapportConsoleDecorator(IRapport rapport) {
        super(rapport);
    }

    @Override
    public void ajouterEntete(Agence a) {
        System.out.println("=== Agence " + a.getIdAgence() + " - " + a.getNom()
                + " - Tel : " + a.getTelephone() + " ===");
        rapport.ajouterEntete(a);
    }
}