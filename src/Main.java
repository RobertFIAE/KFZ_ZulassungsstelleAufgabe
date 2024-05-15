public class Main {


    public static void main(String[] args) {
//        KFZ_Zulassungsstelle kfzZulassungsstelle = new KFZ_Zulassungsstelle();
//        KFZ auto1 = KFZ.erstelleKFZ(KFZ_Enum.PKW);
//        KFZ auto2 = KFZ.erstelleKFZ(KFZ_Enum.PKW);
//        System.out.println(auto1.getFahrzeugschein());
        PKW nissanMicra = new PKW("nissan","micra",82,2002,700,"grau","12345678912345678",5);
        PKW nissanPrimera = new PKW("nissan","primera",150,1996,700,"grün","12345668912345678",5);
        System.out.println("Nissan Micra\n"+nissanMicra.getFahrzeugschein());
        PKW nissanSkyline = new PKW("nissan","Skyline",280,2000,700,"blau","69345678912345678",5);
        Mofa hercules = new Mofa("Hercules", "Prima 5", 5, 1974, 200, "rot", "HERCULES123456789", 2);
        Motorrad suzuki = new Motorrad("Suzuki", "GS 750", 55, 1979, 350, "mint", "SUZUKI12345678912", 2);
        LKW isuzu = new LKW("Isuzu", "elf", 150, 1959, 10000, "weiß", "ISUZUTRUCK1234567", 3);
        PrivatPerson robert = new PrivatPerson();
        Unternehmen mistertee = new Unternehmen();

        robert.erstelleLastschriftMandat();
        mistertee.erstelleLastschriftMandat();
        robert.erstelleEvbNummer();
        KFZ_Zulassungsstelle.kfzAnmelden(robert,nissanMicra);
        robert.erstelleEvbNummer();
        KFZ_Zulassungsstelle.kfzAnmelden(robert,nissanPrimera);
        robert.erstelleEvbNummer();
        KFZ_Zulassungsstelle.kfzAnmelden(robert,nissanPrimera);
        robert.erstelleEvbNummer();
        mistertee.erstelleEvbNummer();
        KFZ_Zulassungsstelle.kfzAnmelden(robert,nissanSkyline);

        KFZ_Zulassungsstelle.kfzAnmelden(mistertee,nissanSkyline);
        mistertee.erstelleEvbNummer();
        KFZ_Zulassungsstelle.kfzAnmelden(mistertee,nissanSkyline);

        robert.erstelleEvbNummer();
        mistertee.erstelleEvbNummer();
        KFZ_Zulassungsstelle.kfzAnmelden(mistertee,hercules);
        mistertee.erstelleEvbNummer();
        KFZ_Zulassungsstelle.kfzAnmelden(mistertee,suzuki);
        mistertee.erstelleEvbNummer();
        KFZ_Zulassungsstelle.kfzAnmelden(mistertee,isuzu);


        robert.getListeZugelasseneAutosPerHalter();
        robert.getListOfZugelasseneKennzeichen();
        mistertee.getListeZugelasseneAutosPerHalter();
        mistertee.getListOfZugelasseneKennzeichen();
    }
}