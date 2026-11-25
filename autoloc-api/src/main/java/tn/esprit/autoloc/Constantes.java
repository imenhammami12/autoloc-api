package tn.esprit.autoloc;

public class Constantes {

    public static final String VERSION = "1.0";

    private Constantes() {
        // classe utilitaire : ne doit pas être instanciée
    }

    public static String getVersion() {
        return VERSION;
    }
}