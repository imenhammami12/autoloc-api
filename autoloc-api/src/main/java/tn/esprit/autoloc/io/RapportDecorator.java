package tn.esprit.autoloc.io;

import lombok.experimental.Delegate;

public abstract class RapportDecorator implements IRapport {

    @Delegate
    protected final IRapport rapport;

    protected RapportDecorator(IRapport rapport) {
        this.rapport = rapport;
    }
}