public class KFZ_Zulassungsstelle {

    public static void kfzAnmelden(Halter halter, KFZ kfz) {
        if (pruefeObKFZundKennzeichenFrei(halter, kfz,"anmelden")) {
            if (pruefeBenoetigteDokumente(halter, kfz)) {
                kfz.anmelden();
                halter.setFahrzeugschein(kfz.getFahrzeugschein());
                halter.addZugelasseneAutosPerHalter(kfz);
                halter.addZugelasseneKennzeichen(kfz.getAngemeldetesKennzeichen());
                if (halter instanceof Unternehmen) {
                    System.out.println("Ihr Fahrzeug: " + kfz.getMarke() + " " + kfz.getModell() + " wurde auf die Firma " + ((Unternehmen) halter).getFirmenname() + " in " + ((Unternehmen) halter).getFirmensitz() + " angemeldet." +
                            "\nAmtliches Kennzeichen: " + kfz.getAngemeldetesKennzeichen() + " | HU/AU bis: " + kfz.getHuAu() + "\n");
                } else {
                    System.out.println("Ihr Fahrzeug: " + kfz.getMarke() + " " + kfz.getModell() + " wurde auf " + halter.getVorname() + " " + halter.getNachname() + " angemeldet." +
                            "\nAmtliches Kennzeichen: " + kfz.getAngemeldetesKennzeichen() + " | HU/AU bis: " + kfz.getHuAu() + "\n");
                }
                halter.aktualisiereHUAUNachweis(kfz);
                halter.setEvbNummer("");
            }
        }
    }
    public static void kfzAbmelden(Halter halter, KFZ kfz) {
        if (pruefeKFZbereitsAngemeldet(kfz,"abmelden")) {
            if(halter.pruefeFahrzeugschein() && halter.getFahrzeugschein().equals(kfz.getFahrzeugschein())){
                kfz.abmelden();
                halter.setFahrzeugschein("");
                halter.removeZugelasseneAutosPerHalter(kfz);
                halter.removeZugelasseneKennzeichen(kfz.getAngemeldetesKennzeichen());
                System.out.println("Ihr KFZ: " + kfz.getMarke() + " " + kfz.getModell() + " mit dem Kennzeichen: " + kfz.getKennzeichen() + " wurde abgemeldet.");
            }else{
                System.out.println("Der Fahrzeugschein fehlt, oder gehört nicht zum Halter.");
            }
        } else {
            System.out.println("Das KFZ ist nicht auf "+halter.getVorname()+" "+halter.getNachname()+" angemeldet.");
        }
    }

    private static boolean pruefeObKFZundKennzeichenFrei(Halter halter, KFZ kfz, String anmelden) {
        return !pruefeKFZbereitsAngemeldet(kfz,anmelden) && !pruefeKennzeichenVergeben(halter, kfz);
    }

    private static boolean pruefeKennzeichenVergeben(Halter halter, KFZ kfz) {
        boolean kennzeichen = Verwaltung.istKennzeichenAngemeldet(kfz.getKennzeichen());
        if (kennzeichen) {
            System.out.println("Das Kennzeichen " + kfz.getKennzeichen() + " ist bereits vergeben." +
                    "\nBitte wählen sie ein anderes Kennzeichen aus");
            kfz.erstelleKennzeichen(halter);
        }
        return kennzeichen;
    }

    private static boolean pruefeKennzeichenErstellt(Halter halter, KFZ kfz) {
        if (kfz.getKennzeichen() == null || kfz.getKennzeichen().isEmpty() || kfz.getKennzeichen().isBlank()) {
            System.out.println("Es wurde noch kein Kennzeichen erstellt.");
            kfz.erstelleKennzeichen(halter);
        }
        return !(kfz.getKennzeichen().isEmpty() || kfz.getKennzeichen().isBlank());
    }

    private static boolean pruefeKFZbereitsAngemeldet(KFZ kfz,String anmelden) {
        boolean kfzAngemeldet = Verwaltung.istKFZAngemeldet(kfz);
        if (kfzAngemeldet && anmelden.equals("anmelden")) {
            System.out.println("Das KFZ: " + kfz.getMarke() + " " + kfz.getModell() + " ist bereits mit dem Kennzeichen: " + kfz.getAngemeldetesKennzeichen() + " angemeldet.");
        }
        return kfzAngemeldet;
    }

    private static boolean pruefeLastschriftMandat(Halter halter) {
        if (!halter.pruefeLastschriftMandat()) {
            System.out.println("Des LastschriftMandat fehlt oder ist ungültig.");
        }
        return halter.pruefeLastschriftMandat();
    }

    private static boolean pruefeEvbNummer(Halter halter) {
        if (!halter.pruefeEvbNummer()) {
            System.out.println("Die eVB Nummer fehlt oder ist ungültig.");
        }
        return halter.pruefeEvbNummer();
    }

    private static boolean pruefeHUAUNachweis(Halter halter, KFZ kfz) {
        halter.aktualisiereHUAUNachweis(kfz);
        if (!(halter.isHuAuNachweisGueltig() && kfz.isHUAUNachweisGueltig())) {
            System.out.println("Der HU/AU Nachweis ungültig oder abgelaufen.");
        }
        return (halter.isHuAuNachweisGueltig() && kfz.isHUAUNachweisGueltig());
    }

    private static boolean pruefeAusweis(Halter halter) {
        boolean personalausweis = halter instanceof PrivatPerson && ((PrivatPerson) halter).pruefePersonalausweisVorhanden();
        boolean handelsregisterauszug = halter instanceof Unternehmen && ((Unternehmen) halter).pruefeHandelsregisterauszugVorhanden();
        if (personalausweis || handelsregisterauszug) {
            return true;
        } else {
            if (halter instanceof PrivatPerson) {
                System.out.println("Der Personalausweis fehlt.");
            } else {
                System.out.println("Der Handelsregisterauszug fehlt.");
            }
            return false;
        }
    }

    private static boolean pruefeBenoetigteDokumente(Halter halter, KFZ kfz) {
        boolean ausweis = pruefeAusweis(halter);
        boolean kennzeichen = pruefeKennzeichenErstellt(halter, kfz);
        boolean lastschriftMandat = pruefeLastschriftMandat(halter);
        boolean evbNummer = pruefeEvbNummer(halter);
        boolean huAuNachweis = pruefeHUAUNachweis(halter, kfz);
        return ausweis && kennzeichen && lastschriftMandat && evbNummer && huAuNachweis;
    }
}

