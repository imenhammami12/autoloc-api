package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component("LONGUE_DUREE")
public class LongueDureeOffre implements OffreStrategy {

    @Override
    public double tarifJournalier(double base) {
        return base * 0.75;
    }

    @Override
    public double ajusterTotal(double total) {
        return total > 2000 ? total * 0.95 : total;
    }
}