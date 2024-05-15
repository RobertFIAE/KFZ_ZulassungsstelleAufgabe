public class KFZ_Zulassungsstelle {

    public static void kfzAnmelden(Halter halter, KFZ kfz) {
        if (pruefeBenoetigteDokumente(halter, kfz)) {
            if (pruefeKFZbereitsAngemeldet(kfz) && pruefeKennzeichenVergeben(halter, kfz)) {
                kfz.anmelden();
                halter.setFahrzeugschein(kfz.getFahrzeugschein());
                halter.setZugelasseneAutosPerHalter(kfz);
                halter.setZugelasseneKennzeichen(kfz.getKennzeichen());
                if (halter instanceof PrivatPerson) {
                    System.out.println("Ihr Fahrzeug: " + kfz.getMarke() + " " + kfz.getModell() + " wurde auf " + halter.getVorname() + " " + halter.getNachname() + " angemeldet." +
                            "\nAmtliches Kennzeichen: " + kfz.getKennzeichen() + " | HU/AU bis: " + kfz.getHuAu() + "\n");
                } else {
                    System.out.println("Ihr Fahrzeug: " + kfz.getMarke() + " " + kfz.getModell() + " wurde auf die Firma " + ((Unternehmen) halter).getFirmenname() + " in " + ((Unternehmen) halter).getFirmensitz() + " angemeldet." +
                            "\nAmtliches Kennzeichen: " + kfz.getKennzeichen() + " | HU/AU bis: " + kfz.getHuAu() + "\n");
                }
                halter.setEvbNummer("");
            }
        }
    }


    private static boolean pruefeKennzeichenVergeben(Halter halter, KFZ kfz) {
        boolean kennzeichen = AngemeldeteKFZVerwaltung.istKennzeichenAngemeldet(kfz.getKennzeichen());
        if (kennzeichen) {
            System.out.println("Das Kennzeichen " + kfz.getKennzeichen() + " ist bereits vergeben." +
                    "\nBitte wählen sie ein anderes Kennzeichen aus");
            kfz.erstelleKennzeichen(halter);
        }
        return !kennzeichen;
    }

    private static boolean pruefeKFZbereitsAngemeldet(KFZ kfz) {
        boolean kfzAngemeldet = AngemeldeteKFZVerwaltung.istKFZAngemeldet(kfz);
        if (kfzAngemeldet) {
            System.out.println("Das KFZ: " + kfz.getMarke()+" "+kfz.getModell()+" ist bereits mit dem Kennzeichen: "+kfz.getKennzeichen()  + " angemeldet.");
        }
        return !kfzAngemeldet;
    }

    private static boolean pruefeKennzeichenErstellt(Halter halter, KFZ kfz) {
        if (kfz.getKennzeichen() == null || kfz.getKennzeichen().isEmpty() || kfz.getKennzeichen().isBlank()) {
            System.out.println("Es wurde noch kein Kennzeichen erstellt.");
            kfz.erstelleKennzeichen(halter);
        }
        return !(kfz.getKennzeichen().isEmpty() || kfz.getKennzeichen().isBlank());
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
        kfz.erstelleHuAuNachweis();
        halter.erstelleHUAUNachweis(kfz);
        if (!(halter.isHuAuNachweisGueltig() && kfz.isHuAuNachweisGueltig())) {
            System.out.println("Der HU/AU Nachweis fehlt oder ist abgelaufen.");
        }
        return (halter.isHuAuNachweisGueltig() && kfz.isHuAuNachweisGueltig());
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

    public static void kfzAbmelden(Halter halter, KFZ kfz) {
        if (halter.pruefeFahrzeugschein()) {
            kfz.abmelden();
            System.out.println("Ihr Auto: " + kfz.getMarke() + " " + kfz.getModell() + " mit dem Kennzeichen: " + kfz.getKennzeichen() + " wurde abgemeldet.");
        } else {
            System.out.println("Der Fahrzeugschein fehlt. Auto konnte nicht abgemeldet werden.");
        }
    }

}

