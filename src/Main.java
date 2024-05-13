import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        KFZ_Zulassungsstelle kfzZulassungsstelle = new KFZ_Zulassungsstelle();
        //KFZ auto1 = KFZ.erstelleKFZ(KFZ_Enum.PKW);
        //System.out.println(auto1.getFahrzeugschein());
        PKW nissanMicra = new PKW("nissan","micra",82,2002,700,"grau","12345678912345678",5);
        //PKW nissanPrimera = new PKW("nissan","primera",150,1996,700,"grün","12345668912345678",5);
        //PKW nissanSkyline = new PKW("nissan","Skyline",280,2000,700,"blau","69345678912345678",5);
        Mofa hercules = new Mofa("Hercules", "Prima 5", 5, 1974, 200, "rot", "HERCULES123456789", 2);
        Motorrad suzuki = new Motorrad("Suzuki", "GS 750", 55, 1979, 350, "mint", "SUZUKI12345678912", 2);
        LKW isuzu = new LKW("Isuzu", "elf", 150, 1959, 10000, "weiß", "ISUZUTRUCK1234567", 3);


        //PrivatPerson robert = new PrivatPerson("Robert", "Mai", true, true, true, true);
        //PrivatPerson max = new PrivatPerson("Max","Mustermann",true,true,true,true);
        //robert.erstellePersonalausweis();
        //System.out.println(robert.getPersonalausweis());
        Unternehmen dieFirma = new Unternehmen("Bobo", "Mai", true,true,true);
        dieFirma.erstelleHandelsregisterauszug();
        System.out.println(dieFirma.getHandelsregisterauszug());

        //nissanMicra.setKennzeichen(nissanMicra.erstelleKennzeichen(robert));
        //nissanPrimera.setKennzeichen(nissanPrimera.erstelleKennzeichen(max));
        //nissanSkyline.setKennzeichen(nissanSkyline.erstelleKennzeichen(robert));

        //robert.setZugelasseneAutosPerHalter(nissanMicra);
        //max.setZugelasseneAutosPerHalter(nissanPrimera);
        //robert.setZugelasseneAutosPerHalter(nissanSkyline);
        //robert.setZugelasseneAutosPerHalter(hercules);
        //robert.setZugelasseneAutosPerHalter(suzuki);
        //robert.setZugelasseneAutosPerHalter(isuzu);
        //robert.getListeZugelasseneAutosPerHalter();
        //robert.getZugelasseneKennzeichen();
        //kfzZulassungsstelle.kfzAnmelden(robert, nissanMicra);
        //kfzZulassungsstelle.kfzAnmelden(robert,nissanSkyline);
        //kfzZulassungsstelle.kfzAnmelden(max,nissanPrimera);

        //robert.getListeZugelasseneAutosPerHalter();
        //robert.getZugelasseneKennzeichen();
        //max.getListeZugelasseneAutosPerHalter();
        //kfzZulassungsstelle.kfzAbmelden(robert,nissanMicra);
        //kfzZulassungsstelle.kfzAbmelden(max,nissanPrimera);


    }
}