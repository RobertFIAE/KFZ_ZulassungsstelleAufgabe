import java.util.HashSet;
import java.util.Set;

public class KennzeichenVerwaltung {
    private static Set<String> angemeldeteKennzeichen = new HashSet<>();

    public static void anmeldenKennzeichen(String kennzeichen) {
        if (!angemeldeteKennzeichen.contains(kennzeichen)) {
            angemeldeteKennzeichen.add(kennzeichen);
        } else {
            System.out.println("Das Kennzeichen: " + kennzeichen + " ist bereits angemeldet.");
        }
    }

    public static void abmeldenKennzeichen(String kennzeichen) {
        angemeldeteKennzeichen.remove(kennzeichen);
    }

    public static boolean istAngemeldet(String kennzeichen) {
        return angemeldeteKennzeichen.contains(kennzeichen);
    }
}
