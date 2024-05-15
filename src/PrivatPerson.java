import java.time.Year;
import java.util.HashMap;

public class PrivatPerson extends Halter {
    private String personalausweis;

    public PrivatPerson() {
        erstellePersonalausweis();
    }

    public String getPersonalausweis() {
        return personalausweis;
    }

    private void setPersonalausweis(String personalausweis) {
        this.personalausweis = personalausweis;
    }

    public void erstellePersonalausweis() {
        HashMap<String, String> fehlerMeldungen = getFehlerMeldungHashMap();
        String vorname = getEingabeString("Bitte Vorname eingeben: ",fehlerMeldungen.get("nurA-Z+ÄÖÜ"),"[A-Za-zÄÖÜäöü ]+",1,50);
        String nachname = getEingabeString("Bitte Nachname eingeben: ",fehlerMeldungen.get("nurA-Z+ÄÖÜ"),"[A-Za-zÄÖÜäöü ]+",1,50);
        String geburtsOrt = getEingabeString("Bitte Geburtsort eingeben: ",fehlerMeldungen.get("nurA-Z+ÄÖÜ"),"[A-Za-zÄÖÜäöü ]+",1,50);
        setVorname(vorname.toUpperCase());
        setNachname(nachname.toUpperCase());
        setPersonalausweis((vorname+" : "+nachname+" : "+setGeburtstagVollstaendig() + " : " +
                geburtsOrt).toUpperCase());
    }
    public boolean pruefePersonalausweisVorhanden(){
        return (getPersonalausweis() != null) && !getPersonalausweis().isEmpty() && !getPersonalausweis().isBlank();
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
