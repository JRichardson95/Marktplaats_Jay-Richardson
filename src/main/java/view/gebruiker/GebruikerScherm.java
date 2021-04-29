package view.gebruiker;
import lombok.extern.log4j.Log4j2;
import model.entity.Gebruiker;
import view.advertentie.AdvertentieAanmakenScherm;
import view.advertentie.AdvertentieBekijkenScherm;
import view.main.BeginScherm;

import static view.View.*;

@Log4j2
public class GebruikerScherm {

    private final Gebruiker HUIDIGE_GEBRUIKER;

    public GebruikerScherm(Gebruiker huidigeGebruiker) {
        this.HUIDIGE_GEBRUIKER = huidigeGebruiker;
    }

    public void start(){
        log.info("Start scherm gebruiker");

        header("Welkom " + HUIDIGE_GEBRUIKER.getNaam());

        println("Wat wilt u doen:\n" +
                "1: Gegevens bewerken\n" +
                "2: Advertentie aanmaken\n" +
                "3: Eigen advertenties bekijken\n" +
                "0: Uitloggen");

        printPointer();
        keuzeMenu(Integer.parseInt(readLine()));
    }

    private void keuzeMenu(int keuze) {
        log.info("Gebruiker naar juiste scherm sturen");
        switch (keuze) {
            case 1:
                new GebruikerGegevensBewerken(HUIDIGE_GEBRUIKER).start();
                break;
            case 2:
                new AdvertentieAanmakenScherm(HUIDIGE_GEBRUIKER).start();
                break;
            case 3:
                new AdvertentieBekijkenScherm(HUIDIGE_GEBRUIKER).eigenAdvertentiesBekijken();
                start();
                break;
            case 0:
                new BeginScherm().start();
            default:
                log.error("Ongeldige input van gebruiker");
                println("Ongeldige input");
                start();
        }
    }
}
