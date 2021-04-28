package view;

import controller.dao.GebruikersDao;
import exception.GebruikerNietGevonden;
import model.entity.Gebruiker;
import view.gebruiker.GebruikerScherm;
import view.gebruiker.VoorwaardenScherm;

import javax.persistence.NoResultException;

import static config.EntityManager.em;
import static view.View.*;
public class Inloggen {

    private String email;
    private String wachtwoord;
    private Gebruiker huidigeGebruiker;
    private final GebruikersDao gebruikersDao  = new GebruikersDao(em);

    public void start(){
        header("Inloggen");

        getGegevens();

        if(verifiëren(email, wachtwoord)){
            if (huidigeGebruiker.isAkkoordMetVoorwaarde())naarGebruikerScherm(huidigeGebruiker);
            else{
                println("U heeft de algemene voorwaarden nog niet geaccepteerd");
                huidigeGebruiker.setAkkoordMetVoorwaarde(new VoorwaardenScherm().start());
                start();
            }
        }
        else {start();}
    }

    protected void getGegevens(){
        print("Email: ");
        email = readLine();
        print("Wachtwoord: ");
        wachtwoord = readLine();
    }

    protected boolean verifiëren(String email, String wachtwoord) {
        try {
            gebruikersDao.findAll().forEach(System.out::println);
            Gebruiker gebruiker = gebruikersDao.findByEmail(email);
            if (gebruiker.getPassword().equals(wachtwoord)){
                huidigeGebruiker = gebruiker;
                return true;
            }
        } catch (NoResultException e){
            printException(new GebruikerNietGevonden(email));
        }
        return false;
    }

    private void naarGebruikerScherm(Gebruiker gebruiker) {
        new GebruikerScherm(gebruiker).start();
    }

}
