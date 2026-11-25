package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component("standard")
public class StandardOffre implements OffreStrategy {
    @Override
    public double tarifJournalier(double base) {
        return base;
    }
}