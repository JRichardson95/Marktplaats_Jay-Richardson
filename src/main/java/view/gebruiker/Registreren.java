package view.gebruiker;

import controller.dao.GebruikersDao;
import lombok.extern.log4j.Log4j2;
import model.Status;
import model.entity.Gebruiker;

import static config.EntityManager.em;
import static model.GenereerWachtwoord.maakWachtwoord;
import static view.View.*;
import static view.gebruiker.BezorgwijzeScherm.*;

@Log4j2
public class Registreren {
    private final Gebruiker nieuweGebruiker = new Gebruiker();
    private final GebruikersDao gebruikersDao = new GebruikersDao(em);

    public void nieuweGebruiker() {


        header("Registreren nieuwe gebruiker");

        print("Voornaam: ");
        nieuweGebruiker.setVoornaam(readLine());
        print("Achternaam: ");
        nieuweGebruiker.setAchternaam(readLine());

        vraagEmail();

        nieuweGebruiker.setBezorgwijzeSet(vraagBezorgWijze());

        printBezorgwijze(nieuweGebruiker);

        nieuweGebruiker.setAdres(adresVragen(nieuweGebruiker.getBezorgwijzeSet()));

        nieuweGebruiker.setStatus(Status.ACTIEF);

        log.info("Gegenereerd wachtwoord meegeven ipv Email versturen");
        nieuweGebruiker.setPassword(maakWachtwoord());

        nieuweGebruiker.setAkkoordMetVoorwaarde(new VoorwaardenScherm().start());

        gebruikersDao.save(nieuweGebruiker);

        divider();
        println("Wachtwoord = " + nieuweGebruiker.getPassword());

        new Inloggen().start();
    }

    private void vraagEmail() {
        print("Email: ");
        controleerEmail(readLine());
        divider();
    }

    private void controleerEmail(String email) {
        if (email.contains("@")) {
            if (gebruikersDao.existsByEmail(email)) {
                nieuweGebruiker.setEmail(email);
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
