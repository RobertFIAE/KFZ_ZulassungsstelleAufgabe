import java.time.Year;
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
            System.out.println(kfz.getClass().getName() + "\n" + kfz.getFahrzeugschein() + " | zugelassen auf: " + getVorname() + " " + getNachname() + "\n");
        }
    }

    public void setZugelasseneAutosPerHalter(KFZ zugelassenesKFZ) {
        this.zugelasseneAutosPerHalter.add(zugelassenesKFZ);
    }

    public void getListOfZugelasseneKennzeichen() {
        for (String zugelassenesKennzeichen : this.zugelasseneKennzeichen) {
            System.out.println(zugelassenesKennzeichen + " | zugelassen auf: " + getVorname() + " " + getNachname() + "\n");
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


    public void erstelleEvbNummer() {
        String versicherung = Eingabe.getEingabeString("Bitte geben sie den Namen ihrer Versicherung ein: (min. 2 Zeichen) ", "sonder+leer", "[A-Za-zÄÖÜäöü0-9]+", 2, 20);
        int zufallsnummer = (int) (Math.random() * 99998) - 1;
        setEvbNummer(versicherung.substring(0, 2).toUpperCase() + zufallsnummer);
        System.out.println("eVB Nummer erfolgreich erstellt. Ihre eVB Nummer lautet: "+getEvbNummer()+"\n");
    }

    public boolean pruefeEvbNummer() {
        return (getEvbNummer() != null) && !getEvbNummer().isEmpty() && !getEvbNummer().isBlank();
    }

    public void erstelleLastschriftMandat() {
        String bankname = Eingabe.getEingabeString("Bitte geben sie den Namen ihrer Bank ein:", "sonder+leer", "[A-Za-zÄÖÜäöü0-9-]+", 1, 20);
        String iban = Eingabe.getEingabeString("Bitte geben sie die 20 Ziffern ihrer IBAN nach dem 'DE' ein:", "nur0-9", "[0-9]+", 20, 20);
        setLastschriftMandat("| Inhaber: "+getVorname()+" "+getNachname()+" | Bankname: "+bankname.toUpperCase() + " | IBAN : DE" + iban+" |");
        System.out.println("Ihr Lastschrift Mandat wurde erfolgreich erstellt. Lastschrift Mandat: "+getLastschriftMandat()+"\n");
    }

    public boolean pruefeLastschriftMandat() {
        return (getLastschriftMandat() != null) && !getLastschriftMandat().isEmpty() && !getLastschriftMandat().isBlank();
    }

    public void erstelleHUAUNachweis(KFZ kfz) {
        setHuAuNachweis(kfz.isHuAuNachweisGueltig());
    }

    public boolean pruefeFahrzeugschein() {
        return (getFahrzeugschein() != null) && !getFahrzeugschein().isEmpty() && !getFahrzeugschein().isBlank();
    }

    public static Halter erstelleHalter(Klassen_Enum halterEnum) {
        String vorname = (Eingabe.getEingabeString("Bitte Vorname eingeben: ","nurA-Z+ÄÖÜ","[A-Za-zÄÖÜäöü ]+",1,50).toUpperCase());
        String nachname = (Eingabe.getEingabeString("Bitte Nachname eingeben: ","nurA-Z+ÄÖÜ","[A-Za-zÄÖÜäöü ]+",1,50).toUpperCase());

        switch (halterEnum) {
            case PRIVATPERSON -> {
                return new PrivatPerson(vorname,nachname);
            }
            case UNTERNEHMEN -> {
                return new Unternehmen(vorname,nachname);
            }
            default -> throw new IllegalArgumentException("Ungültiger Haltertyp: " + halterEnum);
        }
    }
}
