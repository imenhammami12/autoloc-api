package tn.esprit.autoloc;

public class Utils {

    private Utils() {
    }

    // true si la chaîne n'est ni null ni vide (espaces ignorés)
    public static boolean isNotEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }
}