package tn.esprit.autoloc.io;

import tn.esprit.autoloc.domain.Agence;

public class RapportHybrideDecorator extends RapportDecorator {

    public RapportHybrideDecorator(IRapport rapport) {
        super(rapport);
    }

    // L'entête va sur la console et n'est PAS ajouté au texte retourné
    @Override
    public void ajouterEntete(Agence a) {
        System.out.println("=== Agence " + a.getIdAgence()
                + " - " + a.getNom()
                + " - Tel : " + a.getTelephone() + " ===");
    }

    // Les lignes par véhicule ne sont pas retournées : seul le total l'est
    @Override
    public void ajouterLigne(String texte) {
        // ignoré volontairement
    }

    // ajouterTotal() et generer() sont délégués au rapport de base par @Delegate
}