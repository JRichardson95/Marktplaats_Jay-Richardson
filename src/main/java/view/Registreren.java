package view;

import controller.dao.GebruikersDao;
import model.Status;
import model.entity.Gebruiker;
import view.gebruiker.VoorwaardenScherm;


import static config.EntityManager.em;
import static view.View.*;
import static view.gebruiker.BezorgwijzeScherm.*;

public class Registreren {
    private final Gebruiker nieuweGebruiker = new Gebruiker();

    public void nieuweGebruiker() {
        GebruikersDao gebruikersDao = new GebruikersDao(em);

        header("Registreren nieuwe gebruiker");

        print("Voornaam:");
        nieuweGebruiker.setVoornaam(readLine());
        print("Achternaam:");
        nieuweGebruiker.setAchternaam(readLine());
        print("Email:");
        nieuweGebruiker.setEmail(readLine());
        divider();

        nieuweGebruiker.setBezorgwijzeSet(vraagBezorgWijze());

        printBezorgwijze(nieuweGebruiker);

        adresVragen();

        nieuweGebruiker.setStatus(Status.ACTIEF);

        nieuweGebruiker.setPassword("testWachtwoord");

        nieuweGebruiker.setAkkoordMetVoorwaarde(new VoorwaardenScherm().start());

        gebruikersDao.save(nieuweGebruiker);

        println(nieuweGebruiker.toString());

        new BeginScherm().start();
    }
}
