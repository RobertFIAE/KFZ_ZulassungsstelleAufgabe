public class Main {

    public static void main(String[] args) {

        PKW nissanMicra = new PKW("nissan", "micra", 82, 2002, 700, "grau", "12345678912345678", 5);
        PKW nissanPrimera = new PKW("nissan", "primera", 150, 1996, 700, "grün", "12345668912345678", 5);
        PKW nissanSkyline = new PKW("nissan", "Skyline", 280, 2000, 700, "blau", "69345678912345678", 5);
        Mofa hercules = new Mofa("Hercules", "Prima 5", 5, 1974, 200, "rot", "HERCULES123456789", 2);
        Motorrad suzuki = new Motorrad("Suzuki", "GS 750", 55, 1979, 350, "mint", "SUZUKI12345678912", 2);
        LKW isuzu = new LKW("Isuzu", "elf", 150, 1959, 10000, "weiß", "ISUZUTRUCK1234567", 3);

        while (true) {
            String menueAusgabe = "--------------KFZ-Zulassungsstelle Aufgabe Menü--------------" +
                    "\n Bitte folgende Ziffern für die jeweiligen Menüpunkte eingeben:" +
                    "\n 1: Privat Person erstellen.." +
                    "\n 2: Unternehmen erstellen." +
                    "\n 3: PKW erstellen." +
                    "\n 4: LKW erstellen." +
                    "\n 5: Motorrad erstellen." +
                    "\n 6: Mofa erstellen." +
                    "\n 7: LastschriftMandat erstellen." +
                    "\n 8: eVB Nummer erstellen.(einmal gültig pro Anmeldung)" +
                    "\n 9: HU/AU Nachweis erstellen." +
                    "\n 10: KFZ - Anmelden." +
                    "\n 11: KFZ - Abmelden." +
                    "\n 12: Liste alle zugelassenen KFZ's per Halter." +
                    "\n 13: Lister aller insgesamt zugelassenen KFZ's." +
                    "\n 14: Liste aller erstellten KFZ's." +
                    "\n 15: Liste aller erstellten Halter." +
                    "\n 0: Menü beenden.";
            int menuePunkt = Eingabe.getEingabeInt(menueAusgabe, 0, 15);
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
                        halter.erstelleHUAUNachweis(kfz);
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
                case (13) -> Verwaltung.iteriereAngemeldeteKFZMap();
                case (14) -> Verwaltung.iteriereErstellteKFZMap();
                case (15) -> Verwaltung.iteriereErstellteHalterMap();
                case (0) -> {
                    return;
                }
                default -> System.out.println("Das war keine Gültige eingabe.");
            }
        }
    }
}