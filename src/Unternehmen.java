import java.time.Year;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Unternehmen extends Halter {
    private String handelsregisterauszug;
    private boolean handelsregisterauszugVorhanden;

    public Unternehmen(String vorname, String nachname,boolean handelsregisterauszugGueltig, boolean lastschriftmandat, boolean evbNummer) {
        setVorname(vorname);
        setNachname(nachname);
        setHandelsregisterauszugVorhanden(handelsregisterauszugGueltig);
        setEvbNummerVorhanden(evbNummer);
        setLaschschriftMandatVorhanden(lastschriftmandat);
    }

    public String getHandelsregisterauszug() {
        return handelsregisterauszug;
    }

    public void setHandelsregisterauszug(String handelsregisterauszug) {
        this.handelsregisterauszug = handelsregisterauszug;
    }
    public void setHandelsregisterauszugVorhanden(boolean handelsregisterauszugGueltig){
        this.handelsregisterauszugVorhanden = handelsregisterauszugGueltig;
    }

    public boolean isHandelsregisterauszugVorhanden() {
        return handelsregisterauszugVorhanden;
    }

    public void erstelleHandelsregisterauszug() {
        HashMap<String, String> fehlerMeldungen = getStringStringHashMap();
        String vorname = getEingabeString("Bitte Vorname des Geschäftsführers eingeben: ",fehlerMeldungen.get("nurA-Z+ÄÖÜ"),"[A-Za-zÄÖÜäöü ]+",1,50);
        String nachname = getEingabeString("Bitte Nachname des Geschäftsführers eingeben: ",fehlerMeldungen.get("nurA-Z+ÄÖÜ"),"[A-Za-zÄÖÜäöü ]+",1,50);
        String firmenname = getEingabeString("Bitte vollständigen Firmennamen eingeben: ","Das Feld darf nicht leer sein.",".*",1,50);
        String firmensitz = getEingabeString("Bitte Firmensitz eingeben: ",fehlerMeldungen.get("nurA-Z+ÄÖÜ"),"[A-Za-zÄÖÜäöü ]+",1,50);
        setHandelsregisterauszug((vorname+" : "+nachname+" : "+firmenname+" : "+setFirmaGegruendetAm() + " : " +
                firmensitz).toUpperCase());
    }
    private String setFirmaGegruendetAm() {
        return setFirmaGegruendetAmTag()+"."+ setFirmaGegruendetAmMonat()+"."+ setFirmaGegruendetAmJahr();
    }
    private int setFirmaGegruendetAmTag() {
        return getEingabeInt("Bitte Gründungstag ein : (zwischen 1 und 31)", 1, 31);
    }
    private String setFirmaGegruendetAmMonat() {
        int eingabe = getEingabeInt("Bitte geben sie den Gründungsmonat ein: (zwischen 1 und 12)", 1, 12);
        return Monate_Enum.values()[eingabe - 1].name();
    }
    private int setFirmaGegruendetAmJahr() {
        int aktuellesJahr = Year.now().getValue();
        return getEingabeInt("Bitte Gründungsjahr ein : (zwischen " + (aktuellesJahr - 1100) + " und " + aktuellesJahr + ".", (aktuellesJahr - 1100), aktuellesJahr);
    }
}
