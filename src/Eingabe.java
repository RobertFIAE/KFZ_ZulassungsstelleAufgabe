import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Eingabe{

    private static HashMap<String, String> getFehlerMeldungHashMap() {
        HashMap<String, String> fehlerMeldungen = new HashMap<>();
        fehlerMeldungen.put("nurA-Z", "Bitte nur Buchstaben eingeben. Umlaute, Zahlen und Sonderzeichen sind ausgeschlossen.");
        fehlerMeldungen.put("nurA-Z+0-9", "Bitte nur Buchstaben und Ganzzahlen eingeben. Sonderzeichen sind ausgeschlossen.");
        fehlerMeldungen.put("nurA-Z+ÄÖÜ", "Bitte nur Buchstaben eingeben. Zahlen und Sonderzeichen sind ausgeschlossen.");
        fehlerMeldungen.put("nur0-9", "Bitte nur Ganzzahlen eingeben. Buchstaben und Sonderzeichen sind ausgeschlossen.");
        fehlerMeldungen.put("sonder+leer", "Das Feld darf nicht leer sein. Sonder- und Leerzeichen sind nicht erlaubt.");
        fehlerMeldungen.put("leer", "Das Feld darf nicht leer sein.");
        fehlerMeldungen.put("identifikationsnummer", "Bitte genau 17 Zeichen (Buchstaben und Zahlen) eingeben. Sonderzeichen und Umlaute sind ausgeschlossen.");
        return fehlerMeldungen;
    }

    public static String getEingabeString(String nachricht, String fehlerMeldung, String zulaessigeZeichen, int min, int max) {
        HashMap<String, String> fehlerMeldungen = getFehlerMeldungHashMap();
        System.out.println(nachricht);
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.nextLine().trim();
        if (eingabe != null && !eingabe.isEmpty() && eingabe.length() >= min && eingabe.length() <= max && eingabe.matches(zulaessigeZeichen)) {
            return eingabe;
        } else {
            System.out.println(fehlerMeldungen.get(fehlerMeldung));
            return getEingabeString(nachricht, fehlerMeldung, zulaessigeZeichen, min, max);
        }
    }

    public static int getEingabeInt(String nachricht, int min, int max) {
        System.out.println(nachricht);
        Scanner scanner = new Scanner(System.in);
        try {
            int eingabe = scanner.nextInt();
            if (eingabe >= min && eingabe <= max) {
                return eingabe;
            } else {
                System.out.println("Bitte eine gültige positive Zahl zwischen " + min + " und " + max + " eingeben.");
                return getEingabeInt(nachricht, min, max);
            }
        } catch (InputMismatchException e) {
            System.out.println("Bitte nur Ganzzahlen eingeben.");
            return getEingabeInt(nachricht, min, max);
        }
    }
}
