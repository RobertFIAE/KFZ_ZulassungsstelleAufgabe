import java.time.Year;

public class Unternehmen extends Halter {
    private String handelsregisterauszug, firmenname, firmensitz, firmengruendung;

    public Unternehmen(String vorname, String nachname) {
        setVorname(vorname);
        setNachname(nachname);
        setFirmenname(Eingabe.getEingabeString("Bitte vollständigen Firmennamen eingeben: ", "Das Feld darf nicht leer sein.", ".*", 1, 50).toUpperCase());
        setFirmensitz(Eingabe.getEingabeString("Bitte Firmensitz eingeben: ", "nurA-Z+ÄÖÜ", "[A-Za-zÄÖÜäöü ]+", 1, 50).toUpperCase());
        setFirmengruendung(setFirmaGegruendetAm().toUpperCase());
        erstelleHandelsregisterauszug();
        Verwaltung.setErstellteHalterMap(getHandelsregisterauszug().trim(), this);
    }

    public String getHandelsregisterauszug() {
        return handelsregisterauszug;
    }

    public void setHandelsregisterauszug(String handelsregisterauszug) {
        this.handelsregisterauszug = handelsregisterauszug;
    }

    public void erstelleHandelsregisterauszug() {

        setHandelsregisterauszug((getVorname() + " : " + getNachname() + " : " + getFirmenname() + " : " + getFirmengruendung() + " : " +
                getFirmensitz()));
    }

    public boolean pruefeHandelsregisterauszugVorhanden() {
        return (getHandelsregisterauszug() != null) && !getHandelsregisterauszug().isEmpty() && !getHandelsregisterauszug().isBlank();
    }

    private String setFirmaGegruendetAm() {
        return setFirmaGegruendetAmTag() + "." + setFirmaGegruendetAmMonat() + "." + setFirmaGegruendetAmJahr();
    }

    private int setFirmaGegruendetAmTag() {
        return Eingabe.getEingabeInt("Bitte Gründungstag ein : (zwischen 1 und 31)", 1, 31);
    }

    private String setFirmaGegruendetAmMonat() {
        int eingabe = Eingabe.getEingabeInt("Bitte geben sie den Gründungsmonat ein: (zwischen 1 und 12)", 1, 12);
        return Monate_Enum.values()[eingabe - 1].name();
    }

    private int setFirmaGegruendetAmJahr() {
        int aktuellesJahr = Year.now().getValue();
        return Eingabe.getEingabeInt("Bitte Gründungsjahr ein : (zwischen " + (aktuellesJahr - 1100) + " und " + aktuellesJahr + ".", (aktuellesJahr - 1100), aktuellesJahr);
    }

    public String getFirmenname() {
        return firmenname;
    }

    public void setFirmenname(String firmenname) {
        this.firmenname = firmenname;
    }

    public String getFirmensitz() {
        return firmensitz;
    }

    public void setFirmensitz(String firmensitz) {
        this.firmensitz = firmensitz;
    }

    public String getFirmengruendung() {
        return firmengruendung;
    }

    public void setFirmengruendung(String firmengruendung) {
        this.firmengruendung = firmengruendung;
    }

    public void getListeZugelasseneAutosPerHalter() {
        for (KFZ kfz : getZugelasseneAutosPerHalter()) {
            System.out.println(kfz.getClass().getName() + "\n" + kfz.getFahrzeugschein() + " | zugelassen auf die Firma: " + getFirmenname() + " in " + getFirmensitz() + "\n");
        }
    }

    public void getListOfZugelasseneKennzeichen() {
        for (String zugelassenesKennzeichen : getZugelasseneKennzeichen()) {
            System.out.println(zugelassenesKennzeichen + " | zugelassen auf die Firma: " + getFirmenname() + " in " + getFirmensitz() + "\n");
        }
    }
}
