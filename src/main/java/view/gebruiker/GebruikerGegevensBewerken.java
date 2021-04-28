package view.gebruiker;

import controller.dao.GebruikersDao;
import lombok.extern.log4j.Log4j2;
import model.entity.Gebruiker;

import static config.EntityManager.em;
import static view.View.*;
import static view.gebruiker.AdresScherm.adresPrompt;
import static view.gebruiker.BezorgwijzeScherm.adresVragen;
import static view.gebruiker.BezorgwijzeScherm.vraagBezorgWijze;

@Log4j2
public class GebruikerGegevensBewerken {
    private Gebruiker huidigeGebruiker;
    private final GebruikersDao gebruikersDao = new GebruikersDao(em);

    public GebruikerGegevensBewerken(Gebruiker huidigeGebruiker) {
        this.huidigeGebruiker = huidigeGebruiker;
    }

    public void start(){
        log.info("Gegevens bewerken scherm");

        header("Gegevens bewerken");

        println("Welke gegevens wilt u bewerken:\n" +
                "1: Naam\n" +
                "2: Email\n" +
                "3: Wachtwoord\n" +
                "4: Adres\n" +
                "5: Bezorgwijze\n" +
                "6: Gegevens bekijken\n" +
                "0: Terug naar hoofdmenu");

        keuzeMenu(readInt());
        updateGebruiker(gebruikersDao, huidigeGebruiker);
        new GebruikerScherm(huidigeGebruiker).start();
    }

    private void keuzeMenu(int keuze) {
        log.info("Gebruiker keuze controleren.");
        switch (keuze){
            case 1:
                naamWijzigen();
                break;
            case 2:
                emailWijzigen();
                break;
            case 3:
                wachtwoordWijzigen();
                break;
            case 4:
                adresWijzigen();
                break;
            case 5:
                bezorgwijzeWijzigen();
                break;
            case 6:
                gegevensWeergeven();
                break;
            case 0:
                new GebruikerScherm(huidigeGebruiker).start();
                break;
            default:
                println("Ongeldige input");
                start();
        }
    }

    private void naamWijzigen() {
        log.info("Naam gebruiker wijzigen");
        print("Voornaam: ");
        huidigeGebruiker.setVoornaam(readLine());
        print("Achternaam: ");
        huidigeGebruiker.setAchternaam(readLine());
    }

    private void emailWijzigen() {
        log.info("E-mail gebruiker wijzigen");
        println("Huidig e-mailadres: " + huidigeGebruiker.getEmail());
        print("Nieuw e-mailadres: ");
        huidigeGebruiker.setEmail(readLine());
    }

    private void wachtwoordWijzigen() {
        log.info("Wachtwoord wijzigen");
        print("Huidig wachtwoord:");
        log.info("Huidig wachtwoord vragen ter controle");
        if (readLine().equals(huidigeGebruiker.getPassword())){
            log.info("Huidig wachtwoord bevestigd, 2x nieuw wachtwoord vragen");
            print("Nieuw wachtwoord: ");
            String wachtwoord = readLine();
            print("Herhaling wachtwoord:");
            if (wachtwoord.equals(readLine())){
                huidigeGebruiker.setPassword(wachtwoord);
            }else{
                log.error("Nieuwe wachtwoord niet 2x hetzelfde ingevuld");
                print("Wachtwoorden komen niet overeen.");
            }
        }else{
            log.error("Huidig wachtwoord niet correct ingevuld");
            print("Wachtwoorden komen niet overeen.");
        }
        log.info("Wachtwoord gewijzigd");
        println("Wachtwoord gewijzigd");
    }

    private void adresWijzigen() {
        log.info("Adres wijzigen");
        huidigeGebruiker.setAdres(adresPrompt());
    }

    private void bezorgwijzeWijzigen() {
        log.info("Bezorgwijze wijzigen");
        huidigeGebruiker.setBezorgwijzeSet(vraagBezorgWijze());
    }

    private void gegevensWeergeven() {
        log.info("Gebruiker gegevens weergeven");
        println("Voornaam: " + huidigeGebruiker.getVoornaam());
        println("Achternaam: " + huidigeGebruiker.getAchternaam());
        println("E-mail: " + huidigeGebruiker.getEmail());
        if (huidigeGebruiker.getAdres() != null) println("Adres: " + huidigeGebruiker.getAdres().toString());
        println("Bezorgwijze: " + huidigeGebruiker.getBezorgwijzeSet());
        start();
    }

    protected void updateGebruiker(GebruikersDao gebruikersDao, Gebruiker gebruiker){
        log.info("Gebruiker gegevens updaten...");
        gebruikersDao.update(gebruiker);
        println("update");
    }

}
