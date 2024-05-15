import java.util.*;


public class Halter {
    private ArrayList<KFZ> zugelasseneAutosPerHalter = new ArrayList<KFZ>();
    private ArrayList<String> zugelasseneKennzeichen = new ArrayList<String>();
    private String vorname, nachname, lastschriftMandat, kennzeichen, evbNummer, fahrzeugschein;
    private boolean huAuNachweis;

    public Halter() {

    }

    public ArrayList<KFZ> getZugelasseneAutosPerHalter() {
        return this.zugelasseneAutosPerHalter;
    }

    public void getListeZugelasseneAutosPerHalter() {
        for (KFZ kfz : this.zugelasseneAutosPerHalter) {
            System.out.println(kfz.getClass().getName() + "\n" + kfz.getFahrzeugschein() +" | zugelassen auf: "+getVorname()+" "+getNachname() +"\n");
        }
    }

    public void setZugelasseneAutosPerHalter(KFZ zugelassenesKFZ) {
        this.zugelasseneAutosPerHalter.add(zugelassenesKFZ);
    }

    public void getListOfZugelasseneKennzeichen() {
        for (String zugelassenesKennzeichen : this.zugelasseneKennzeichen) {
            System.out.println(zugelassenesKennzeichen+" | zugelassen auf: "+getVorname()+" "+getNachname()+"\n");
        }
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

    public boolean isHuAuNachweisGueltig() {
        return huAuNachweis;
    }

    private void setHuAuNachweis(boolean huAuNachweis) {
        this.huAuNachweis = huAuNachweis;
    }

    //___________________________________________________________________________________________________________
    protected static HashMap<String, String> getFehlerMeldungHashMap() {
        HashMap<String, String> fehlerMeldungen = new HashMap<>();
        fehlerMeldungen.put("nurA-Z", "Bitte nur Buchstaben eingeben. Umlaute, Zahlen und Sonderzeichen sind ausgeschlossen.");
        fehlerMeldungen.put("nurA-Z+0-9", "Bitte nur Buchstaben und Ganzzahlen eingeben. Sonderzeichen sind ausgeschlossen.");
        fehlerMeldungen.put("nurA-Z+ÄÖÜ", "Bitte nur Buchstaben eingeben. Zahlen und Sonderzeichen sind ausgeschlossen.");
        fehlerMeldungen.put("nur0-9", "Bitte nur Ganzzahlen eingeben. Buchstaben und Sonderzeichen sind ausgeschlossen.");
        fehlerMeldungen.put("sonder+leer", "Das Feld darf nicht leer sein. Sonder- und Leerzeichen sind nicht erlaubt.");
        fehlerMeldungen.put("leer", "Das Feld darf nicht leer sein.");
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

    public void erstelleEvbNummer() {
        String versicherung = getEingabeString("Bitte geben sie den Namen ihrer Versicherung ein: (min. 2 Zeichen) ", "sonder+leer", "[A-Za-zÄÖÜäöü0-9]+", 2, 20);
        int zufallsnummer = (int) (Math.random() * 99998) - 1;
        setEvbNummer(versicherung.substring(0, 2).toUpperCase() + zufallsnummer);
    }

    public boolean pruefeEvbNummer() {
        return (getEvbNummer() != null) && !getEvbNummer().isEmpty() && !getEvbNummer().isBlank();
    }

    public void erstelleLastschriftMandat() {
        String bankname = getEingabeString("Bitte geben sie den Namen ihrer Bank ein:", "sonder+leer", "[A-Za-zÄÖÜäöü0-9-]+", 1, 20);
        String iban = getEingabeString("Bitte geben sie die 20 Ziffern ihrer IBAN nach dem 'DE' ein:", "nur0-9", "[0-9]+", 20, 20);
        setLastschriftMandat(bankname.toUpperCase() + " : DE" + iban);
    }

    public boolean pruefeLastschriftMandat() {
        return (getLastschriftMandat() != null) && !getLastschriftMandat().isEmpty() && !getLastschriftMandat().isBlank();
    }

    public void erstelleHUAUNachweis(KFZ kfz){
        setHuAuNachweis(kfz.isHuAuNachweisGueltig());
    }

    public boolean pruefeFahrzeugschein() {
        return (getFahrzeugschein() != null) && !getFahrzeugschein().isEmpty() && !getFahrzeugschein().isBlank();
    }



}
