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
    

    public String erstellePersonalausweis() {
        return (setName("Vorname") + " : " +
                setName("Nachname") + " : " +
                setGeburtstagVollstaendig() + " : " +
                setGeburtsOrt()).toUpperCase();
    }

    private String setName(String vorNachName) {
        System.out.println("Bitte " + vorNachName + " eingeben: ");
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.nextLine().trim();
        String name = "";
        try {
            if (eingabe != null && !eingabe.isEmpty() && !eingabe.isBlank() && eingabe.matches("[A-Za-zÄÖÜäöü ]+")
            ) {
                name = eingabe;
            } else {
                System.out.println("Das Feld darf nicht Leer sein. Und keine Zahlen enthalten");
                return setName(vorNachName);
            }
        } catch (InputMismatchException e) {
            System.out.println("Was denn hier los?");
            return setName(vorNachName);
        }

        return name;
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


    private String setGeburtsOrt() {
        System.out.println("Bitte Geburtsort eingeben: ");
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.nextLine().trim();
        String geburtsort = "";
        if (eingabe != null && !eingabe.isEmpty() && !eingabe.isBlank() && eingabe.matches("[A-Za-zÄÖÜäöü ]+")) {
            geburtsort = eingabe;
        } else {
            System.out.println("Das Feld darf nicht leer sein und nur Buchstaben enthalten.");
            return setGeburtsOrt();
        }
        return geburtsort;
    }

}
