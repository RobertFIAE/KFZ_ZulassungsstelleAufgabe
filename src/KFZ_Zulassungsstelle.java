public class KFZ_Zulassungsstelle {
    public void kfzAnmelden(Halter halter, KFZ kfz) {
        boolean personalausweis = halter instanceof PrivatPerson && ((PrivatPerson) halter).isPersonalausweisVorhanden();
        boolean handelsregisterauszug = halter instanceof Unternehmen && ((Unternehmen) halter).isHandelsregisterauszugVorhanden();
        boolean lastschriftMandat = halter.isLaschschriftMandatVorhanden();
        boolean evbNummer = halter.isEvbNummerVorhanden();
        boolean huAuNachweis = halter.isHuAuNachweisGueltig();
        boolean kennzeichen = KennzeichenVerwaltung.istAngemeldet(kfz.getKennzeichen());

        if ((personalausweis || handelsregisterauszug) && lastschriftMandat && evbNummer && huAuNachweis && !kennzeichen) {
            kfz.anmelden();
            halter.setFahrzeugscheinVorhanden(true);
            System.out.println("Ihr Auto: " + kfz.getMarke() + " " + kfz.getModell() + " wurde auf " + halter.getVorname() + " " + halter.getNachname() + " angemeldet."+
                    "\nAmtliches Kennzeichen: " + kfz.getKennzeichen()+" HU/AU bis: "+kfz.getHuAu()+"\n");
        } else {
            if (!personalausweis && !handelsregisterauszug) {
                System.out.println("Der Personalausweis oder Handelsregisterauszug fehlt oder ist ungültig.");
            } else if (!lastschriftMandat) {
                System.out.println("Des LastschriftMandat fehlt oder ist ungültig.");
            } else if (!evbNummer) {
                System.out.println("Die eVB Nummer fehlt oder ist ungültig.");
            } else if (!huAuNachweis) {
                System.out.println("Der HU/AU Nachweis fehlt oder ist abgelaufen.");
            } else if (kennzeichen) {
                System.out.println("Das Kennzeichen "+kfz.getKennzeichen()+" ist bereits vergeben."+
                        "\nBitte wählen sie ein anderes Kennzeichen aus");
                kfz.setKennzeichen(kfz.erstelleKennzeichen(halter));
                kfzAnmelden(halter,kfz);
            } else {
                System.out.println("FEHLER !");
            }
        }
    }

    public void kfzAbmelden(Halter halter, KFZ kfz) {
        boolean fahrzeugscheinVorhanden = halter.isFahrzeugscheinVorhanden();
        if(fahrzeugscheinVorhanden){
            kfz.abmelden();
            System.out.println("Ihr Auto: " + kfz.getMarke() + " " + kfz.getModell() + " mit dem Kennzeichen: "+kfz.getKennzeichen()+" wurde abgemeldet.");
        }
        else if (!fahrzeugscheinVorhanden){
            System.out.println("Der Fahrzeugschein fehlt.");
        }
        else {
            System.out.println("FEHLER !");
        }
    }
}
