package view.gebruiker;

import controller.dao.GebruikersDao;
import lombok.extern.log4j.Log4j2;
import model.entity.Gebruiker;

import static config.EntityManager.EM;
import static view.View.*;
import static view.gebruiker.AdresScherm.adresPrompt;
import static view.gebruiker.BezorgwijzeScherm.adresVragen;
import static view.gebruiker.BezorgwijzeScherm.vraagBezorgWijze;

@Log4j2
public class GebruikerGegevensBewerken {
    private final Gebruiker HUIDIGE_GEBRUIKER;
    private final GebruikersDao GEBRUIKERS_DAO = new GebruikersDao(EM);

    public GebruikerGegevensBewerken(Gebruiker huidigeGebruiker) {
        this.HUIDIGE_GEBRUIKER = huidigeGebruiker;
    }

    public void start() {
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

        printPointer();
        keuzeMenu(readInt());
        updateGebruiker(GEBRUIKERS_DAO, HUIDIGE_GEBRUIKER);
        new GebruikerScherm(HUIDIGE_GEBRUIKER).start();
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
                new GebruikerScherm(HUIDIGE_GEBRUIKER).start();
                break;
            default:
                println("Ongeldige input");
                start();
        }
    }

    private void naamWijzigen() {
        log.info("Naam gebruiker wijzigen");
        print("Voornaam: ");
        HUIDIGE_GEBRUIKER.setVoornaam(readLine());
        print("Achternaam: ");
        HUIDIGE_GEBRUIKER.setAchternaam(readLine());
    }

    private void emailWijzigen() {
        log.info("E-mail gebruiker wijzigen");
        println("Huidig e-mailadres: " + HUIDIGE_GEBRUIKER.getEmail());
        print("Nieuw e-mailadres: ");
        HUIDIGE_GEBRUIKER.setEmail(readLine());
    }

    private void wachtwoordWijzigen() {
        log.info("Wachtwoord wijzigen");
        print("Huidig wachtwoord:");
        log.info("Huidig wachtwoord vragen ter controle");
        if (readLine().equals(HUIDIGE_GEBRUIKER.getPassword())) {
            log.info("Huidig wachtwoord bevestigd, 2x nieuw wachtwoord vragen");
            print("Nieuw wachtwoord: ");
            String wachtwoord = readLine();
            print("Herhaling wachtwoord:");
            if (wachtwoord.equals(readLine())) {
                HUIDIGE_GEBRUIKER.setPassword(wachtwoord);
            } else {
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
        HUIDIGE_GEBRUIKER.setAdres(adresPrompt());
    }

    private void bezorgwijzeWijzigen() {
        log.info("Bezorgwijze wijzigen");
        HUIDIGE_GEBRUIKER.setBezorgwijzeSet(vraagBezorgWijze());
        HUIDIGE_GEBRUIKER.setAdres(adresVragen(HUIDIGE_GEBRUIKER.getBezorgwijzeSet()));
    }

    private void gegevensWeergeven() {
        log.info("Gebruiker gegevens weergeven");
        println("Voornaam: " + HUIDIGE_GEBRUIKER.getVoornaam());
        println("Achternaam: " + HUIDIGE_GEBRUIKER.getAchternaam());
        println("E-mail: " + HUIDIGE_GEBRUIKER.getEmail());
        if (HUIDIGE_GEBRUIKER.getAdres() != null) println("Adres: " + HUIDIGE_GEBRUIKER.getAdres().toString());
        println("Bezorgwijze: " + HUIDIGE_GEBRUIKER.getBezorgwijzeSet());
        start();
    }

    protected void updateGebruiker(GebruikersDao gebruikersDao, Gebruiker gebruiker){
        log.info("Gebruiker gegevens updaten...");
        gebruikersDao.update(gebruiker);
    }

}
