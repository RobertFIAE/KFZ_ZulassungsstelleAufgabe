import java.time.Year;
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

    public String erstelleHandelsregisterauszug() {
        return  (setName("Vorname vom Geschäftsführer") + " : " +
                setName("Nachname vom Geschäftsführer") + " : " +
                setName("Vollständiger Firmenname") + " : " +
                setFirmaGegruendetAm() + " : " +
                setFirmensitz()).toUpperCase();
    }


    private String setName(String vorNachName) {
        System.out.println("Bitte "+vorNachName+" eingeben: ");
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.nextLine().trim();
        String name ="";
        try {
            if (eingabe != null && !eingabe.isEmpty() &&!eingabe.isBlank() && eingabe.matches("[A-Za-zÄÖÜäöü ]+")
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



    private String setFirmaGegruendetAm() {
        return setFirmaGegruendetAmTag()+"."+ setFirmaGegruendetAmMonat()+"."+ setFirmaGegruendetAmJahr();
    }


    private int setFirmaGegruendetAmTag() {
        System.out.println("Bitte Gründungs-Tag eingeben : (zwischen 1 und 31)");
        Scanner scanner = new Scanner(System.in);
        int geburtstag = 0;
        try {
            int eingabe = scanner.nextInt();
            if (eingabe > 0 && eingabe <= 31) {
                geburtstag = eingabe;
            } else {
                System.out.println("Bitte eine gültige positive Zahl zwischen 1 und 31 eingeben.");
                return setFirmaGegruendetAmTag();
            }
        } catch (InputMismatchException e) {
            System.out.println("Bitte nur Ganzzahlen eingeben.");
            return setFirmaGegruendetAmTag();
        }
        return geburtstag;
    }

    private String setFirmaGegruendetAmMonat() {
        System.out.println("Bitte Gründungs-Monat eingeben: (zwischen 1 und 12)");
        Scanner scanner = new Scanner(System.in);
        int eingabe = scanner.nextInt();
        String monat = "";
        try {
            if (eingabe > 0 && eingabe <= 12) {
                switch (eingabe) {
                    case (1) -> monat = Monate_Enum.JANUAR.name();
                    case (2) -> monat = Monate_Enum.FEBRUAR.name();
                    case (3) -> monat = Monate_Enum.MAERZ.name();
                    case (4) -> monat = Monate_Enum.APRIL.name();
                    case (5) -> monat = Monate_Enum.MAI.name();
                    case (6) -> monat = Monate_Enum.JUNI.name();
                    case (7) -> monat = Monate_Enum.JULI.name();
                    case (8) -> monat = Monate_Enum.AUGUST.name();
                    case (9) -> monat = Monate_Enum.SEPTEMBER.name();
                    case (10) -> monat = Monate_Enum.OKTOBER.name();
                    case (11) -> monat = Monate_Enum.NOVEMBER.name();
                    case (12) -> monat = Monate_Enum.DEZEMBER.name();
                    default -> monat = "FEHLER";
                }
            } else {
                System.out.println("Bitte geben sie einen gültigen, positven Wert ein. Gültige Werte sind: " +
                        "\n'01' für Januar" +
                        "\n'02' für Februar" +
                        "\n'03' für März" +
                        "\n'04' für April" +
                        "\n'05' für Mai" +
                        "\n'06' für Juni" +
                        "\n'07' für Juli" +
                        "\n'08' für August" +
                        "\n'09' für September" +
                        "\n'10' für Oktober" +
                        "\n'11' für November" +
                        "\n'12' für Dezember");
                return setFirmaGegruendetAmMonat();
            }
        } catch (InputMismatchException e) {
            System.out.println("Bitte nur Ganzzahlen eingeben");
            return setFirmaGegruendetAmMonat();
        }

        return monat;
    }
    private int setFirmaGegruendetAmJahr(){
        System.out.println("Bitte Gründungs-Jahr eingeben : (zwischen 578 und "+ Year.now().getValue()+".");
        Scanner scanner = new Scanner(System.in);
        int gruendungsjahr = 0;
        try {
            int eingabe = scanner.nextInt();
            if (eingabe > 577 && eingabe <= Year.now().getValue()) {
                gruendungsjahr = eingabe;
            } else {
                System.out.println("Bitte eine positive Ganzzahl zwischen 578 und "+ Year.now().getValue()+".");
                return setFirmaGegruendetAmJahr();
            }
        } catch (InputMismatchException e) {
            System.out.println("Bitte nur Ganzzahlen eingeben.");
            return setFirmaGegruendetAmJahr();
        }
        return gruendungsjahr;
    }

    private String setFirmensitz() {
        System.out.println("Bitte Firmensitz eingeben: ");
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.nextLine().trim();
        String firmensitz = "";
        if (eingabe != null && !eingabe.isEmpty() && !eingabe.isBlank() && eingabe.matches("[A-Za-zÄÖÜäöü ]+")
        ) {
            firmensitz = eingabe;
        } else {
            System.out.println("Das Feld darf nicht leer sein und nur Buchstaben enthalten.");
            return setFirmensitz();
        }
        return firmensitz;
    }
}
