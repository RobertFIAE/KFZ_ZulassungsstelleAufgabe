import java.time.Year;

public class PrivatPerson extends Halter {
    private String personalausweis, geburtsort, geburtsdatum;

    public PrivatPerson(String vorname, String nachname) {
        setVorname(vorname);
        setNachname(nachname);
        setGeburtsort(Eingabe.getEingabeString("Bitte Geburtsort eingeben: ", "nurA-Z+ÄÖÜ", "[A-Za-zÄÖÜäöü ]+", 1, 50).toUpperCase());
        setGeburtsdatum(setGeburtstagVollstaendig());
        erstellePersonalausweis();
        Verwaltung.setErstellteHalterMap(getPersonalausweis().trim(), this);
    }

    public String getPersonalausweis() {
        return personalausweis;
    }

    private void setPersonalausweis(String personalausweis) {
        this.personalausweis = personalausweis;
    }

    public void erstellePersonalausweis() {
        setPersonalausweis((getVorname() + " : " + getNachname() + " : " + getGeburtsdatum() + " : " +
                getGeburtsort()));
    }

    public boolean pruefePersonalausweisVorhanden() {
        return (getPersonalausweis() != null) && !getPersonalausweis().isEmpty() && !getPersonalausweis().isBlank();
    }

    private String setGeburtstagVollstaendig() {
        return setGeburtsTag() + "." + setGeburtsMonat() + "." + setGeburtsJahr();
    }

    private int setGeburtsTag() {
        return Eingabe.getEingabeInt("Bitte Geburtstag ein : (zwischen 1 und 31)", 1, 31);
    }

    private String setGeburtsMonat() {
        int eingabe = Eingabe.getEingabeInt("Bitte geben sie ihren Geburtsmonat ein: (zwischen 1 und 12)", 1, 12);
        return Monate_Enum.values()[eingabe - 1].name();
    }

    private int setGeburtsJahr() {
        int aktuellesJahr = Year.now().getValue();
        return Eingabe.getEingabeInt("Bitte Geburtsjahr ein : (zwischen " + (aktuellesJahr - 110) + " und " + aktuellesJahr + ".", (aktuellesJahr - 110), aktuellesJahr);
    }

    public String getGeburtsort() {
        return geburtsort;
    }

    public void setGeburtsort(String geburtsort) {
        this.geburtsort = geburtsort;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
}
