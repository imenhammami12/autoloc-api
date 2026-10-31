package tn.esprit.autoloc.io;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RapportFactory {

    @Value("${autoloc.rapport.console:true}")
    private boolean console;

    public IRapport creer() {
        IRapport rapport = new RapportTexte();
        return console ? new RapportConsoleDecorator(rapport) : rapport;
    }
}