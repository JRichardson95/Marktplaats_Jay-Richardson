package view.gebruiker;

import controller.dao.GebruikersDao;
import lombok.extern.log4j.Log4j2;
import model.entity.Gebruiker;
import model.enums.Status;

import static config.EntityManager.EM;
import static model.GenereerWachtwoord.maakWachtwoord;
import static view.View.*;
import static view.gebruiker.BezorgwijzeScherm.*;

@Log4j2
public class Registreren {
    private final Gebruiker NIEUWE_GEBRUIKER = new Gebruiker();
    private final GebruikersDao GEBRUIKERS_DAO = new GebruikersDao(EM);

    public void nieuweGebruiker() {


        header("Registreren nieuwe gebruiker");

        print("Voornaam: ");
        NIEUWE_GEBRUIKER.setVoornaam(readLine());
        print("Achternaam: ");
        NIEUWE_GEBRUIKER.setAchternaam(readLine());

        vraagEmail();

        NIEUWE_GEBRUIKER.setBezorgwijzeSet(vraagBezorgWijze());

        printBezorgwijze(NIEUWE_GEBRUIKER);

        NIEUWE_GEBRUIKER.setAdres(adresVragen(NIEUWE_GEBRUIKER.getBezorgwijzeSet()));

        NIEUWE_GEBRUIKER.setStatus(Status.ACTIEF);

        log.info("Gegenereerd wachtwoord meegeven ipv Email versturen");
        NIEUWE_GEBRUIKER.setPassword(maakWachtwoord());

        NIEUWE_GEBRUIKER.setAkkoordMetVoorwaarde(new VoorwaardenScherm().start());

        GEBRUIKERS_DAO.save(NIEUWE_GEBRUIKER);

        divider();
        println("Wachtwoord = " + NIEUWE_GEBRUIKER.getPassword());

        new Inloggen().start();
    }

    private void vraagEmail() {
        print("Email: ");
        controleerEmail(readLine());
        divider();
    }

    private void controleerEmail(String email) {
        if (email.contains("@")) {
            if (GEBRUIKERS_DAO.existsByEmail(email)) {
                NIEUWE_GEBRUIKER.setEmail(email);
            } else {
                printErr("Er bestaat al een account met email: " + email);
                println("");
                vraagEmail();
            }
        } else {
            log.error("Ongeldig email adres ingevoerd");
            printErr("Ongeldig email adres ingevoerd: " + email);
            println("");
            vraagEmail();
        }
    }

}
