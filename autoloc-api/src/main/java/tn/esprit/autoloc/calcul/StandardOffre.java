package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component("STANDARD")
public class StandardOffre implements OffreStrategy {
    @Override
    public double tarifJournalier(double base) {
        return base;
    }
}