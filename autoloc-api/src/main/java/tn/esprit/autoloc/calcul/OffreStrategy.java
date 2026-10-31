package tn.esprit.autoloc.calcul;

public interface OffreStrategy {
    double tarifJournalier(double tarifBase);

    default double ajusterTotal(double total) {
        return total;
    }
}