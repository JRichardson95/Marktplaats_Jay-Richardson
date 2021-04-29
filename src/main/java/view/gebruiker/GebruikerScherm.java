package view.gebruiker;
import lombok.extern.log4j.Log4j2;
import model.entity.Gebruiker;
import view.advertentie.AdvertentieAanmakenScherm;
import view.advertentie.AdvertentieBekijkenScherm;

import static view.View.*;

@Log4j2
public class GebruikerScherm {

    private final Gebruiker huidigeGebruiker;

    public GebruikerScherm(Gebruiker huidigeGebruiker) {
        this.huidigeGebruiker = huidigeGebruiker;
    }

    public void start(){
        log.info("Start scherm gebruiker");

        header("Welkom " + huidigeGebruiker.getNaam());

        println("Wat wilt u doen:\n" +
                "1: Gegevens bewerken\n" +
                "2: Advertentie aanmaken\n" +
                "3: Eigen advertenties bekijken");

        printPointer();
        keuzeMenu(Integer.parseInt(readLine()));
    }

    private void keuzeMenu(int keuze) {
        log.info("Gebruiker naar juiste scherm sturen");
        switch (keuze){
            case 1:
                new GebruikerGegevensBewerken(huidigeGebruiker).start();
                break;
            case 2:
                new AdvertentieAanmakenScherm(huidigeGebruiker).start();
                break;
            case 3:
                new AdvertentieBekijkenScherm(huidigeGebruiker).eigenAdvertentiesBekijken();
                break;
            default:
                log.error("Ongeldige input van gebruiker");
                println("Ongeldige input");
                start();
        }
    }

}
