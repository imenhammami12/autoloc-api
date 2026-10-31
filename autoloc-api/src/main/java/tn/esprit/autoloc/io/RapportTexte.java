package tn.esprit.autoloc.io;

import tn.esprit.autoloc.domain.Agence;

public class RapportTexte implements IRapport {
    private final StringBuilder sb = new StringBuilder();

    @Override
    public void ajouterEntete(Agence a) {
        sb.append("Agence ").append(a.getIdAgence())
                .append(" - ").append(a.getNom())
                .append(" - Tel : ").append(a.getTelephone()).append("\n");
    }

    @Override
    public void ajouterLigne(String texte) {
        sb.append(texte).append("\n");
    }

    @Override
    public void ajouterTotal(double total) {
        sb.append("Total : ").append(total);
    }

    @Override
    public String generer() {
        return sb.toString();
    }
}