package view.gebruiker;
import model.entity.Gebruiker;

import static view.View.*;

public class GebruikerScherm {

    private final Gebruiker huidigeGebruiker;

    public GebruikerScherm(Gebruiker huidigeGebruiker) {
        this.huidigeGebruiker = huidigeGebruiker;
    }

    public void start(){
        header("Welkom " + huidigeGebruiker.getNaam());

        println("Wat wilt u doen:\n" +
                "1: Gegevens bewerken\n" +
                "2: Advertentie aanmaken\n" +
                "3: Advertenties bekijken");

        keuzeMenu(Integer.parseInt(readLine()));
    }

    private void keuzeMenu(int keuze) {
        switch (keuze){
            case 1:
                new GebruikerGegevensBewerken(huidigeGebruiker).start();
                break;
//            case 2:
//                new AdvertentieAanmakenScherm().start();
//                break;
//            case 3:
//                new AdvertentiesBekijkenScherm().start();
//                break;
            default:
                println("Ongeldige input");
                start();
        }
    }


}
