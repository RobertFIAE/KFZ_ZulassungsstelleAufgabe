import java.time.Year;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PrivatPerson extends Halter {
    private String personalausweis;
    private boolean PersonalausweisVorhanden;

    public PrivatPerson(String vorname, String nachname, boolean personalausweisGueltig, boolean lastschriftmandat, boolean evbNummer, boolean huAuNachweis) {
        setVorname(vorname);
        setNachname(nachname);
        setPersonalausweisVorhanden(personalausweisGueltig);
        setEvbNummerVorhanden(evbNummer);
        setLaschschriftMandatVorhanden(lastschriftmandat);
        setHuAuNachweisGueltig(huAuNachweis);
    }

    public String getPersonalausweis() {
        return personalausweis;
    }

    public void setPersonalausweis(String personalausweis) {
        this.personalausweis = personalausweis;
    }

    public void setPersonalausweisVorhanden(boolean personalausweisGueltig) {
        this.PersonalausweisVorhanden = personalausweisGueltig;
    }

    public boolean isPersonalausweisVorhanden() {
        return PersonalausweisVorhanden;
    }

    public void erstellePersonalausweis() {
        HashMap<String, String> fehlerMeldungen = getStringStringHashMap();
        String vorname = getEingabeString("Bitte Vorname eingeben: ",fehlerMeldungen.get("nurA-Z+ÄÖÜ"),"[A-Za-zÄÖÜäöü ]+",1,50);
        String nachname = getEingabeString("Bitte Nachname eingeben: ",fehlerMeldungen.get("nurA-Z+ÄÖÜ"),"[A-Za-zÄÖÜäöü ]+",1,50);
        String geburtsOrt = getEingabeString("Bitte Geburtsort eingeben: ",fehlerMeldungen.get("nurA-Z+ÄÖÜ"),"[A-Za-zÄÖÜäöü ]+",1,50);
        setPersonalausweis((vorname+" : "+nachname+" : "+setGeburtstagVollstaendig() + " : " +
                geburtsOrt).toUpperCase());
    }
    private String setGeburtstagVollstaendig() {
        return setGeburtsTag() + "." + setGeburtsMonat() + "." + setGeburtsJahr();
    }

    private int setGeburtsTag() {
        return getEingabeInt("Bitte Geburtstag ein : (zwischen 1 und 31)", 1, 31);
    }
    private String setGeburtsMonat() {
        int eingabe = getEingabeInt("Bitte geben sie ihren Geburtsmonat ein: (zwischen 1 und 12)", 1, 12);
        return Monate_Enum.values()[eingabe - 1].name();
    }
    private int setGeburtsJahr() {
        int aktuellesJahr = Year.now().getValue();
        return getEingabeInt("Bitte Geburtsjahr ein : (zwischen " + (aktuellesJahr - 110) + " und " + aktuellesJahr + ".", (aktuellesJahr - 110), aktuellesJahr);
    }
}
