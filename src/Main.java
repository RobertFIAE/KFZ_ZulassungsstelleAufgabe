public class Main {

    public static void main(String[] args) {
        String menueAusgabe = "--------------KFZ-Zulassungsstelle Aufgabe Menü--------------" +
                "\n Bitte folgende Ziffern für die jeweiligen Menüpunkte eingeben:" +
                "\n  1: Privat Person erstellen.." +
                "\n  2: Unternehmen erstellen." +
                "\n  3: PKW erstellen." +
                "\n  4: LKW erstellen." +
                "\n  5: Motorrad erstellen." +
                "\n  6: Mofa erstellen." +
                "\n  7: LastschriftMandat erstellen." +
                "\n  8: eVB Nummer erstellen.(einmal gültig pro Anmeldung)" +
                "\n  9: HU/AU Nachweis erstellen." +
                "\n 10: KFZ - Anmelden." +
                "\n 11: KFZ - Abmelden." +
                "\n 12: Liste alle zugelassenen KFZ's per Halter." +
                "\n 13: Lister aller insgesamt zugelassenen KFZ's." +
                "\n 14: Liste aller erstellten KFZ's." +
                "\n 15: Liste aller erstellten Halter." +
                "\n 16: Aktualisiere KFZ HU/AU Nachweis." +
                "\n  0: Menü beenden.";

        while (true) {
            int menuePunkt = Eingabe.getEingabeInt(menueAusgabe, 0, 16);
            switch (menuePunkt) {
                case (1) -> Halter.erstelleHalter(Klassen_Enum.PRIVATPERSON);
                case (2) -> Halter.erstelleHalter(Klassen_Enum.UNTERNEHMEN);
                case (3) -> KFZ.erstelleKFZ(Klassen_Enum.PKW);
                case (4) -> KFZ.erstelleKFZ(Klassen_Enum.LKW);
                case (5) -> KFZ.erstelleKFZ(Klassen_Enum.MOTORRAD);
                case (6) -> KFZ.erstelleKFZ(Klassen_Enum.MOFA);
                case (7) -> {
                    Halter halter = Verwaltung.auswahlHalter();
                    if (halter != null) {
                        halter.erstelleLastschriftMandat();
                    } else return;
                }
                case (8) -> {
                    Halter halter = Verwaltung.auswahlHalter();
                    if (halter != null) {
                        halter.erstelleEvbNummer();
                    } else return;
                }
                case (9) -> {
                    Halter halter = Verwaltung.auswahlHalter();
                    KFZ kfz = Verwaltung.auswahlKFZ();
                    if (halter != null && kfz != null) {
                        halter.aktualisiereHUAUNachweis(kfz);
                    } else return;
                }
                case (10) -> Verwaltung.auswahlAnmelden();
                case (11) -> Verwaltung.auswahlAbmelden();
                case (12) -> {
                    Halter halter = Verwaltung.auswahlHalter();
                    if (halter != null) {
                        halter.getListeZugelasseneAutosPerHalter();
                    } else return;
                }
                case (13) -> Verwaltung.iteriereDurchAngemeldeteKFZMap();
                case (14) -> Verwaltung.iteriereDurchErstellteKFZMap();
                case (15) -> Verwaltung.iteriereDurchErstellteHalterMap();
                case (16) -> {
                    KFZ kfz = Verwaltung.auswahlKFZ();
                    if (kfz != null) {
                        kfz.pruefeUndErstelleHuAu(kfz.getBaujahr());
                    } else return;
                }
                case (0) -> {
                    return;
                }
                default -> System.out.println("Das war keine Gültige eingabe.");
            }
        }
    }
}