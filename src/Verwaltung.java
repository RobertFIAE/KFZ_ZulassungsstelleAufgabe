import java.util.*;

public class Verwaltung {
    private static Set<String> angemeldeteKennzeichen = new HashSet<>();
    private static Map<KFZ, String> angemeldeteKFZMap = new HashMap<>();
    private static Map<String, KFZ> erstellteKFZMap = new HashMap<>();
    private static Map<String, Halter> erstellteHalterMap = new HashMap<>();

    public static Map<String, KFZ> getErstellteKFZMap() {
        return erstellteKFZMap;
    }

    public static void setErstellteKFZMap(String key, KFZ kfz) {
        Verwaltung.erstellteKFZMap.put(key, kfz);
    }

    public static void abmeldenKennzeichen(String kennzeichen) {
        angemeldeteKennzeichen.remove(kennzeichen);
    }

    public static boolean istKennzeichenAngemeldet(String kennzeichen) {
        return angemeldeteKennzeichen.contains(kennzeichen);
    }

    public static void abmeldenKFZ(KFZ kfz) {
        angemeldeteKFZMap.remove(kfz);
    }

    public static boolean istKFZAngemeldet(KFZ kfz) {
        return angemeldeteKFZMap.containsKey(kfz) && angemeldeteKFZMap.containsValue(kfz.getFahrzeugschein());
    }

    public static Map<KFZ, String> getAngemeldeteKFZMap() {
        return angemeldeteKFZMap;
    }

    public static Map<String, Halter> getErstellteHalterMap() {
        return erstellteHalterMap;
    }

    public static void setErstellteHalterMap(String key, Halter halter) {
        Verwaltung.erstellteHalterMap.put(key, halter);
    }

    public static void anmeldenKennzeichen(String kennzeichen) {
        if (!angemeldeteKennzeichen.contains(kennzeichen)) {
            angemeldeteKennzeichen.add(kennzeichen);
        } else {
            System.out.println("Das Kennzeichen: " + kennzeichen + " ist bereits angemeldet.");
        }
    }

    public static void anmeldenKFZ(KFZ kfz) {
        if (!angemeldeteKFZMap.containsKey(kfz) && !angemeldeteKFZMap.containsValue(kfz.getFahrzeugschein())) {
            angemeldeteKFZMap.put(kfz, kfz.getFahrzeugschein());
        } else {
            System.out.println("Das KFZ: " + kfz.getMarke() + " " + kfz.getModell() + " ist bereits mit dem Kennzeichen: " + kfz.getAngemeldetesKennzeichen() + " angemeldet.");
        }
    }

    public static void iteriereDurchAngemeldeteKFZMap() {
        Iterator<Map.Entry<KFZ, String>> iterator = angemeldeteKFZMap.entrySet().iterator();
        int counter = 1;
        while (iterator.hasNext()) {
            Map.Entry<KFZ, String> entry = iterator.next();
            KFZ kfz = entry.getKey();
            System.out.println("Nummer: " + counter + " | " + kfz.getClass().getName() + " | \n" + kfz.getFahrzeugschein() + "\n");
            counter++;
        }
    }

    public static void iteriereDurchErstellteKFZMap() {
        Iterator<Map.Entry<String, KFZ>> iterator = erstellteKFZMap.entrySet().iterator();
        int counter = 1;
        while (iterator.hasNext()) {
            Map.Entry<String, KFZ> entry = iterator.next();
            KFZ kfz = entry.getValue();
            System.out.println("Nummer: " + counter + " | " + kfz.getClass().getName() + " | \n" + kfz.getFahrzeugschein() + "\n");
            counter++;
        }
    }


    public static void iteriereDurchErstellteHalterMap() {
        Iterator<Map.Entry<String, Halter>> iterator = erstellteHalterMap.entrySet().iterator();
        int counter = 1;
        while (iterator.hasNext()) {
            Map.Entry<String, Halter> entry = iterator.next();
            String key = entry.getKey();
            Halter value = entry.getValue();
            if (value instanceof PrivatPerson) {
                System.out.println("Nummer: " + counter + " | " + value.getClass().getName() + " | \n" + ((PrivatPerson) value).getPersonalausweis() + "\n");
            } else if (value instanceof Unternehmen) {
                System.out.println("Nummer: " + counter + " | " + value.getClass().getName() + " | \n" + ((Unternehmen) value).getHandelsregisterauszug() + "\n");
            }
            counter++;
        }
    }

    private static boolean pruefeIndexErstellteHalterMap() {
        return !Verwaltung.getErstellteHalterMap().isEmpty();
    }

    private static boolean pruefeIndexErstellteKFZMap() {
        return !Verwaltung.getErstellteKFZMap().isEmpty();
    }

    private static boolean pruefeIndexAngemeldeteKFZMap() {
        return !Verwaltung.getAngemeldeteKFZMap().isEmpty();
    }

    public static void auswahlAnmelden() {
        KFZ kfz = auswahlKFZ();
        Halter halter = auswahlHalter();
        if (kfz != null && halter != null) {
            KFZ_Zulassungsstelle.kfzAnmelden(halter, kfz);
        }
    }

    public static void auswahlAbmelden() {
        KFZ kfz = auswahlAngemeldeteKFZ();
        Halter halter = auswahlHalter();

        if (kfz != null && halter != null) {
            KFZ_Zulassungsstelle.kfzAbmelden(halter, kfz);
        }
    }

    public static Halter auswahlHalter() {
        if (pruefeIndexErstellteHalterMap()) {
            System.out.println("Verfügbare Halter:");
            Verwaltung.iteriereDurchErstellteHalterMap();
            Halter halter = waehleHalter(Eingabe.getEingabeInt("Bitte wählen Sie einen Halter aus:", 1, Verwaltung.getErstellteHalterMap().size()) - 1);
            System.out.println("Ausgewählter Halter: " + halter.getVorname() + " " + halter.getNachname() + "\n");
            return halter;
        } else {
            System.out.println("Es wurden noch keine Halter erstellt.");
            return null;
        }
    }

    private static Halter waehleHalter(int auswahl) {
        ArrayList<String> keys = new ArrayList<>(getErstellteHalterMap().keySet());
        if (auswahl >= 0 && auswahl <= keys.size()) {
            return getErstellteHalterMap().get(keys.get(auswahl));
        } else {
            System.out.println("Ungültige Auswahl. Bitte wählen Sie eine Zahl zwischen 1 und " + keys.size());
            return null;
        }
    }

    public static KFZ auswahlKFZ() {
        if (pruefeIndexErstellteKFZMap()) {
            System.out.println("Verfügbare KFZ:");
            Verwaltung.iteriereDurchErstellteKFZMap();
            KFZ kfz = waehleKFZ(Eingabe.getEingabeInt("Bitte wählen Sie ein KFZ aus:", 1, Verwaltung.getErstellteKFZMap().size()) - 1);
            System.out.println("Ausgewähltes KFZ: " + kfz.getClass().getName() + " " + kfz.getMarke() + " " + kfz.getModell() + "\n");
            return kfz;
        } else {
            System.out.println("Es wurden noch keine KFZ's erstellt.");
            return null;
        }
    }

    private static KFZ waehleKFZ(int auswahl) {
        ArrayList<String> keys = new ArrayList<>(getErstellteKFZMap().keySet());
        if (auswahl >= 0 && auswahl <= keys.size()) {
            return getErstellteKFZMap().get(keys.get(auswahl));
        } else {
            System.out.println("Ungültige Auswahl. Bitte wählen Sie eine Zahl zwischen 1 und " + keys.size());
            return null;
        }
    }

    private static KFZ auswahlAngemeldeteKFZ() {
        if (pruefeIndexAngemeldeteKFZMap()) {
            System.out.println("Verfügbare KFZ:");
            Verwaltung.iteriereDurchAngemeldeteKFZMap();
            return waehleAngemeldeteKFZ(Eingabe.getEingabeInt("Bitte wählen Sie ein KFZ aus:", 1, Verwaltung.getAngemeldeteKFZMap().size()) - 1);
        } else {
            System.out.println("Es wurden noch keine KFZ's angemeldet.");
            return null;
        }
    }

    private static KFZ waehleAngemeldeteKFZ(int auswahl) {
        ArrayList<KFZ> keys = new ArrayList<>(getAngemeldeteKFZMap().keySet());
        if (auswahl >= 0 && auswahl <= keys.size()) {
            return keys.get(auswahl);
        } else {
            System.out.println("Ungültige Auswahl. Bitte wählen Sie eine Zahl zwischen 1 und " + keys.size());
            return null;
        }
    }
}
