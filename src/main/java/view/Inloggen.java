package view;

import controller.dao.GebruikersDao;
import exception.GebruikerNietGevonden;
import model.entity.Gebruiker;
import view.gebruiker.GebruikerScherm;

import javax.persistence.NoResultException;

import static config.EntityManager.em;
import static view.View.*;
public class Inloggen {

    private String email;
    private String wachtwoord;
    private Gebruiker huidigeGebruiker;
    private GebruikersDao gebruikersDao  = new GebruikersDao(em);

    public void start(){
        header("Inloggen");

        getGegevens();

        if(verifiëren(email, wachtwoord)){naarGebruikerScherm(huidigeGebruiker);}
        else {start();}
    }

    protected void getGegevens(){
        print("Email: ");
        email = readLine();
        print("Wachtwoord: ");
        wachtwoord = readLine();
    }

    protected boolean verifiëren(String email, String wachtwoord) {
        System.out.println("test1");
        try {
            gebruikersDao.findAll().forEach(System.out::println);
            Gebruiker gebruiker = gebruikersDao.findByEmail(email);
            print("test2");
            if (gebruiker.getPassword().equals(wachtwoord)){
                huidigeGebruiker = gebruiker;
                print("test3");
                return true;
            }
        } catch (NoResultException e){
            System.out.println(e);
            printException(new GebruikerNietGevonden(email));
        }
        return false;
    }

    private void naarGebruikerScherm(Gebruiker gebruiker) {
        new GebruikerScherm(gebruiker).start();
    }

}
