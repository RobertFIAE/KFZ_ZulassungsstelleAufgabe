import java.util.HashSet;
import java.util.Set;

public class AngemeldeteKFZVerwaltung {
    private static Set<String> angemeldeteKennzeichen = new HashSet<>();
    private static Set<KFZ> angemeldeteKFZ = new HashSet<>();

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

    public static boolean istKennzeichenAngemeldet(String kennzeichen) {
        return angemeldeteKennzeichen.contains(kennzeichen);
    }
    public static void anmeldenKFZ(KFZ kfz) {
        if (!angemeldeteKFZ.contains(kfz)) {
            angemeldeteKFZ.add(kfz);
        } else {
            System.out.println("Das KFZ: " + kfz.getMarke()+" "+kfz.getModell()+" ist bereits mit dem Kennzeichen: "+kfz.getKennzeichen()  + " angemeldet.");
        }
    }
    public static void abmeldenKFZ(KFZ kfz) {
        angemeldeteKFZ.remove(kfz);
    }
    public static boolean istKFZAngemeldet(KFZ kfz) {
        return angemeldeteKFZ.contains(kfz);
    }
}
