package tn.esprit.autoloc.io;

import tn.esprit.autoloc.domain.Agence;

public interface IRapport {
    void ajouterEntete(Agence agence);
    void ajouterLigne(String texte);
    void ajouterTotal(double total);
    String generer();
}