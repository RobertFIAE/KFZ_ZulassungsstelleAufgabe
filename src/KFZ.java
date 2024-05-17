import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;

public class KFZ {
    private String marke, modell, farbe, identifikationsnummer, kennzeichen, angemeldetesKennzeichen;
    private int motorleistung, baujahr, zulaessigesGesamtgewicht, sitzplaetze;
    private YearMonth huAu;
    private boolean huAuNachweisGueltig = false;
    private boolean angemeldet;


    public KFZ() {
        this.kennzeichen = "";
    }

//-----------------------GETTER-SETTER-------------------------------------------------------------

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke.toUpperCase();
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell.toUpperCase();
    }

    public int getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(int baujahr) {
        this.baujahr = baujahr;
    }

    public int getZulaessigesGesamtgewicht() {
        return zulaessigesGesamtgewicht;
    }

    public void setZulaessigesGesamtgewicht(int zulaessigesGesamtgewicht) {
        this.zulaessigesGesamtgewicht = zulaessigesGesamtgewicht;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe.toUpperCase();
    }

    public String getIdentifikationsnummer() {
        return identifikationsnummer;
    }

    public void setIdentifikationsnummer(String identifikationsnummer) {
        this.identifikationsnummer = identifikationsnummer.toUpperCase();
    }

    public int getSitzplaetze() {
        return sitzplaetze;
    }

    public void setSitzplaetze(int sitzplaetze) {
        this.sitzplaetze = sitzplaetze;
    }


    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    public String getAngemeldetesKennzeichen() {
        return angemeldetesKennzeichen;
    }

    private void setAngemeldetesKennzeichen(String angemeldetesKennzeichen) {
        this.angemeldetesKennzeichen = angemeldetesKennzeichen;
    }

    public boolean getAngemeldet() {
        return angemeldet;
    }

    private void setAngemeldet(boolean angemeldet) {
        this.angemeldet = angemeldet;
    }

    public int getMotorleistung() {
        return motorleistung;
    }

    public void setMotorleistung(int motorleistung) {
        this.motorleistung = motorleistung;
    }

    public YearMonth getHuAu() {
        return huAu;
    }

    public void setHuAu(YearMonth huAu) {
        this.huAu = huAu;
    }

    public boolean isHuAuNachweisGueltig() {
        return huAuNachweisGueltig;
    }

    private void setHuAuNachweisGueltig(boolean huAuNachweisGueltig) {
        this.huAuNachweisGueltig = huAuNachweisGueltig;
    }



    //-------------------------------------------------Methoden-------------------------------------------------------------------------------

    public String getFahrzeugschein() {
        String fahrzeugDetails = "Marke: " + this.marke +
                "\nModell: " + this.modell +
                "\nMotorleistung: " + this.motorleistung + " PS/ " + ((int) (this.motorleistung / 1.366)) + " KW" +
                "\nBaujahr: " + this.baujahr +
                "\nFarbe: " + this.farbe +
                "\nIdentifikationsnummer: " + this.identifikationsnummer +
                "\nSitzplätze: " + this.sitzplaetze +
                "\nZulässiges Gesamtgewicht: " + this.zulaessigesGesamtgewicht;

        if (this.angemeldet) {
            return fahrzeugDetails +
                    "\nKennzeichen: " + this.angemeldetesKennzeichen + " | HU/AU Plakette: " + this.huAu +
                    "\n-Fahrzeug ist angemeldet-";
        } else {
            return fahrzeugDetails +
                    "\n-Fahrzeug ist abgemeldet-";
        }
    }


    public void anmelden() {
        if (!angemeldet) {
            setAngemeldet(true);
            setAngemeldetesKennzeichen(this.kennzeichen);
            setKennzeichen("");
            Verwaltung.anmeldenKennzeichen(this.angemeldetesKennzeichen);
            Verwaltung.anmeldenKFZMap(this);

        }
    }

    public void abmelden() {
        if (getAngemeldet()) {
            setAngemeldet(false);
            Verwaltung.abmeldenKennzeichen(this.angemeldetesKennzeichen);
            setKennzeichen(this.angemeldetesKennzeichen);
            setAngemeldetesKennzeichen("-kein Kennzeichen angemeldet-");
            Verwaltung.abmeldenKFZ(this);
        }
    }

    public static KFZ erstelleKFZ(Klassen_Enum kfzEnum) {
        String marke = Eingabe.getEingabeString("Bitte geben Sie die Marke ein: ", "nurA-Z", "[A-Za-z]+", 1, 15);
        String modell = Eingabe.getEingabeString("Bitte geben Sie die Modell ein: ", "nurA-Z+0-9", "[A-Za-zÄÖÜäöü0-9 ]+", 1, 20);
        int motorleistung = Eingabe.getEingabeInt("Bitte geben Sie die Motorleistung in PS ein: ", 1, 3000);
        int baujahr = Eingabe.getEingabeInt("Bitte geben Sie das Baujahr ein: (zwischen 1886 und max. " + Year.now().getValue() + ")", 1886, Year.now().getValue());
        int zulaessigesGesamtgewicht = Eingabe.getEingabeInt("Bitte geben Sie das zulässige Gesamtgewicht in Kilo ein:", 1, 100_000);
        String farbe = Eingabe.getEingabeString("Bitte geben Sie die Grundfarbe des KFZ's ein: ", "nurA-Z+ÄÖÜ", "[A-Za-zÄÖÜäöü ]+", 1, 15);
        String identifikationsnummer = Eingabe.getEingabeString("Bitte geben Sie die 17-stellige Identifikationsnummer ein:", "identifikationsnummer", "[A-Za-z0-9]+", 17, 17);
        int sitzplaetze = Eingabe.getEingabeInt("Bitte geben Sie die Anzahl der Sitzplätze ein:", 1, 10);

        switch (kfzEnum) {
            case PKW -> {
                return new PKW(marke, modell, motorleistung, baujahr, zulaessigesGesamtgewicht, farbe, identifikationsnummer, sitzplaetze);
            }
            case LKW -> {
                return new LKW(marke, modell, motorleistung, baujahr, zulaessigesGesamtgewicht, farbe, identifikationsnummer, sitzplaetze);
            }
            case MOFA -> {
                return new Mofa(marke, modell, motorleistung, baujahr, zulaessigesGesamtgewicht, farbe, identifikationsnummer, sitzplaetze);
            }
            case MOTORRAD -> {
                return new Motorrad(marke, modell, motorleistung, baujahr, zulaessigesGesamtgewicht, farbe, identifikationsnummer, sitzplaetze);
            }
            default -> throw new IllegalArgumentException("Ungültiger Fahrzeugtyp: " + kfzEnum);
        }
    }


    public void erstelleKennzeichen(Halter halter) {
        String kennzeichenBuchstaben = erstelleKennzeichenBuchstaben().toUpperCase();
        int kennzeichenZahlen = erstelleKennzeichenZahlen(kennzeichenBuchstaben.length());
        String kennzeichenKomplett = "LIP:" + kennzeichenBuchstaben + ":" + kennzeichenZahlen;
        if (!halter.getZugelasseneKennzeichen().contains(kennzeichenKomplett)) {
            halter.setKennzeichen(kennzeichenKomplett);
            setKennzeichen(kennzeichenKomplett);
        } else {
            System.out.println("Das Kennzeichen: " + kennzeichenKomplett + " ist leider schon vergeben. Versuchen sie es bitte erneut.");
            erstelleKennzeichen(halter);
        }
    }

    private static String erstelleKennzeichenBuchstaben() {
        ArrayList<String> verboteneKombinationen = new ArrayList<>(Arrays.asList("KZ", "HJ", "NS", "SA", "SS", "IS"));
        String kennzeichenBuchstaben = Eingabe.getEingabeString("Bitte geben Sie die 1- maximal 2 Buchstaben ihres (Wunsch)Kennzeichens ein:", "nurBuchstaben", "[A-Za-z]+", 1, 2).toUpperCase();
        if (verboteneKombinationen.contains(kennzeichenBuchstaben)) {
            System.out.println("Entschuldigen sie bitte, folgende Kombination sind nicht erlaubt: KZ, HJ, NS, SA, SS oder IS");
            return erstelleKennzeichenBuchstaben();
        } else {
            return kennzeichenBuchstaben;
        }
    }

    private static int erstelleKennzeichenZahlen(int kennzeichenBuchstaben) {
        int max = kennzeichenBuchstaben == 1 ? 9999 : 999;
        return Eingabe.getEingabeInt("Bitte geben sie ihre (Wunsch)Zahl ein zwischen 1 und max. " + max + ".", 1, max);
    }

    //-------------------------------------------------------------------setzeHuAu() muss wieder auf private, für testzwecke auf public
    public void pruefeUndErstelleHuAu(int baujahr) {
        System.out.println("HU/AU Eingabe für das KFZ: "+this.getMarke()+" "+this.getModell());
        Month monat = Month.of(Eingabe.getEingabeInt("Bitte geben Sie den Monat ihrer HU/AU als Zahl ein: (Beispiel:'08' für August)", 1, 12));
        int jahr = Eingabe.getEingabeInt("Bitte geben Sie das Jahr ihrer HU/AU ein: (Beispiel: 2024)", baujahr, Year.now().getValue() + 2);
        setHuAu(YearMonth.of(jahr, monat));
    }

    public void erstelleHuAuNachweis() {
        if (isHUAUNachweisGueltig()) {
            setHuAuNachweisGueltig(true);
        } else {
            setHuAuNachweisGueltig(false);
        }

    }
    private boolean isHUAUNachweisGueltig() {
        YearMonth currentYearMonth = YearMonth.now();
        if ((this.huAu != null) && !this.huAu.isBefore(currentYearMonth) && !this.huAu.equals(currentYearMonth)) {
            return true;
        } else {
            System.out.println("Der HU/AU Nachweis ist abgelaufen und nicht mehr gültig.");
            return false;
        }
    }

    protected void konstruktor(String marke, String modell, int motorleistung, int baujahr, int zulaessigesGesamtgewicht, String farbe, String identifikationsnummer, int sitzplaetze){
        setMarke(marke);
        setModell(modell);
        setMotorleistung(motorleistung);
        setBaujahr(baujahr);
        setZulaessigesGesamtgewicht(zulaessigesGesamtgewicht);
        setFarbe(farbe);
        setIdentifikationsnummer(identifikationsnummer);
        setSitzplaetze(sitzplaetze);
        pruefeUndErstelleHuAu(baujahr);
    }
}

