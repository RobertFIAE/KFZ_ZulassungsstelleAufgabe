import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Halter {
    private ArrayList<KFZ> zugelasseneAutosPerHalter = new ArrayList<KFZ>();
    private ArrayList<String> zugelasseneKennzeichen = new ArrayList<String>();
    private String vorname, nachname, lastschriftMandat, kennzeichen, huAuNachweis, evbNummer, fahrzeugschein;
    private boolean laschschriftMandatVorhanden, fahrzeugscheinVorhanden, evbNummerVorhanden, huAuNachweisGueltig;

    public Halter() {

    }

    public ArrayList<KFZ> getZugelasseneAutosPerHalter() {
        return this.zugelasseneAutosPerHalter;
    }

    public void getListeZugelasseneAutosPerHalter() {
        for (KFZ kfz : this.zugelasseneAutosPerHalter) {
            System.out.println(kfz.getClass().getName() + "\n" + kfz.getFahrzeugschein() + "\n");
        }
    }

    public void setZugelasseneAutosPerHalter(KFZ zugelassenesKFZ) {
        this.zugelasseneAutosPerHalter.add(zugelassenesKFZ);
    }

    public String getListOfZugelasseneKennzeichen() {
        for (String zugelassenesKennzeichen : this.zugelasseneKennzeichen) {
            return zugelassenesKennzeichen;
        }
        return "";
    }

    public ArrayList<String> getZugelasseneKennzeichen() {
        return this.zugelasseneKennzeichen;
    }

    public void setZugelasseneKennzeichen(String zugelasseneKennzeichen) {
        this.zugelasseneKennzeichen.add(zugelasseneKennzeichen);
    }

    public String getLastschriftMandat() {
        return lastschriftMandat;
    }

    public void setLastschriftMandat(String lastschriftMandat) {
        this.lastschriftMandat = lastschriftMandat;
    }

    public String getFahrzeugschein() {
        return fahrzeugschein;
    }

    public void setFahrzeugschein(String fahrzeugschein) {
        this.fahrzeugschein = fahrzeugschein;
    }

    public boolean isFahrzeugscheinVorhanden() {
        return fahrzeugscheinVorhanden;
    }

    public void setFahrzeugscheinVorhanden(boolean fahrzeugscheinVorhanden) {
        this.fahrzeugscheinVorhanden = fahrzeugscheinVorhanden;
    }

    public void setHuAuNachweisGueltig(boolean huAuNachweisGueltig) {
        this.huAuNachweisGueltig = huAuNachweisGueltig;
    }

    public String getEvbNummer() {
        return evbNummer;
    }

    public void setEvbNummer(String evbNummer) {
        this.evbNummer = evbNummer;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    public String getHuAuNachweis() {
        return huAuNachweis;
    }

    public void setHuAuNachweis(String huAuNachweis) {
        this.huAuNachweis = huAuNachweis;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public boolean isLaschschriftMandatVorhanden() {
        return laschschriftMandatVorhanden;
    }

    public void setLaschschriftMandatVorhanden(boolean laschschriftMandatVorhanden) {
        this.laschschriftMandatVorhanden = laschschriftMandatVorhanden;
    }

    public boolean isEvbNummerVorhanden() {
        return evbNummerVorhanden;
    }

    public void setEvbNummerVorhanden(boolean evbNummerVorhanden) {
        this.evbNummerVorhanden = evbNummerVorhanden;
    }

    public boolean isHuAuNachweisGueltig() {
        return huAuNachweisGueltig;
    }

    public void setHuAuNachweisGueltig(KFZ kfz) {
        YearMonth currentYearMonth = YearMonth.now();
        this.huAuNachweisGueltig = !kfz.getHuAu().isBefore(currentYearMonth) && !kfz.getHuAu().equals(currentYearMonth);
    }

    //___________________________________________________________________________________________________________
    protected static HashMap<String, String> getStringStringHashMap() {
        HashMap<String, String> fehlerMeldungen = new HashMap<>();
        fehlerMeldungen.put("nurA-Z", "Bitte nur Buchstaben eingeben. Umlaute, Zahlen und Sonderzeichen sind ausgeschlossen.");
        fehlerMeldungen.put("nurA-Z+0-9", "Bitte nur Buchstaben und Ganzzahlen eingeben. Sonderzeichen sind ausgeschlossen.");
        fehlerMeldungen.put("nurA-Z+ÄÖÜ", "Bitte nur Buchstaben eingeben. Zahlen und Sonderzeichen sind ausgeschlossen.");
        return fehlerMeldungen;
    }

    protected static String getEingabeString(String nachricht, String fehlerMeldung, String zulaessigeZeichen, int min, int max) {
        getStringStringHashMap();
        System.out.println(nachricht);
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.nextLine().trim();
        if (eingabe != null && !eingabe.isEmpty() && eingabe.length() >= min && eingabe.length() <= max && eingabe.matches(zulaessigeZeichen)) {
            return eingabe;
        } else {
            System.out.println(fehlerMeldung);
            return getEingabeString(nachricht, fehlerMeldung, zulaessigeZeichen, min, max);
        }
    }

    protected static int getEingabeInt(String nachricht, int min, int max) {
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
