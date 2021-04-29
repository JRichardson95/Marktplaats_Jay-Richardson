package view.gebruiker;

import controller.dao.GebruikersDao;
import lombok.extern.log4j.Log4j2;
import model.Status;
import model.entity.Gebruiker;

import static config.EntityManager.em;
import static view.View.*;
import static model.GenereerWachtwoord.maakWachtwoord;
import static view.gebruiker.BezorgwijzeScherm.*;

@Log4j2
public class Registreren {
    private final Gebruiker nieuweGebruiker = new Gebruiker();

    public void nieuweGebruiker() {
        GebruikersDao gebruikersDao = new GebruikersDao(em);

        header("Registreren nieuwe gebruiker");

        print("Voornaam: ");
        nieuweGebruiker.setVoornaam(readLine());
        print("Achternaam: ");
        nieuweGebruiker.setAchternaam(readLine());
        print("Email: ");
        nieuweGebruiker.setEmail(readLine());
        divider();

        nieuweGebruiker.setBezorgwijzeSet(vraagBezorgWijze());

        printBezorgwijze(nieuweGebruiker);

        adresVragen(nieuweGebruiker.getBezorgwijzeSet());

        nieuweGebruiker.setStatus(Status.ACTIEF);

        log.info("Gegenereerd wachtwoord meegeven ipv Email versturen");
        nieuweGebruiker.setPassword(maakWachtwoord());

        nieuweGebruiker.setAkkoordMetVoorwaarde(new VoorwaardenScherm().start());

        gebruikersDao.save(nieuweGebruiker);

        divider();
        println("Wachtwoord = " + nieuweGebruiker.getPassword());

        new Inloggen().start();
    }
}
