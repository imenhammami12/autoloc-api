package tn.esprit.autoloc.io;

import tn.esprit.autoloc.domain.Agence;

public class RapportHybrideDecorator extends RapportDecorator {

    public RapportHybrideDecorator(IRapport rapport) {
        super(rapport);
    }

    @Override
    public void ajouterEntete(Agence a) {
        System.out.println("=== Agence " + a.getIdAgence()
                + " - " + a.getNom()
                + " - Tel : " + a.getTelephone() + " ===");
    }

    @Override
    public void ajouterLigne(String texte) {
    }

}