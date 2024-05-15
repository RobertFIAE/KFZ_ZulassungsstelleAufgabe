import java.time.YearMonth;

public class PKW extends KFZ {

// To-Do: soll eigntl. nicht mehr so aufgerufen werden, sondern nur über KFZ erstelleKFZ() erstellt werden.
    public PKW(String marke, String modell, int motorleistung, int baujahr, int zulaessigesGesamtgewicht, String farbe, String identifikationsnummer, int sitzplaetze) {
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

