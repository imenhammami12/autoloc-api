package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component("WEEKEND")
public class WeekendOffre implements OffreStrategy {
    @Override
    public double tarifJournalier(double base) {
        return base * 0.80;
    }
}


