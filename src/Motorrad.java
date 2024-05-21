public class Motorrad extends KFZ {
    public Motorrad(String marke, String modell, int motorleistung, int baujahr, int zulaessigesGesamtgewicht, String farbe, String identifikationsnummer, int sitzplaetze) {
        konstruktor(marke, modell, motorleistung, baujahr, zulaessigesGesamtgewicht, farbe, identifikationsnummer, sitzplaetze);
        Verwaltung.setErstellteKFZMap((marke + modell + identifikationsnummer).trim().toUpperCase(), this);
    }
}
