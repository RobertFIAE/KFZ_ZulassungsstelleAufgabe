public class Mofa extends KFZ{
    public Mofa(String marke, String modell, int motorleistung, int baujahr, int zulaessigesGesamtgewicht, String farbe, String identifikationsnummer, int sitzplaetze) {
        setMarke(marke);
        setModell(modell);
        setMotorleistung(motorleistung);
        setBaujahr(baujahr);
        setZulaessigesGesamtgewicht(zulaessigesGesamtgewicht);
        setFarbe(farbe);
        setIdentifikationsnummer(identifikationsnummer);
        setSitzplaetze(sitzplaetze);
        setHuAu(setzeHuAu(baujahr));
    }
}
