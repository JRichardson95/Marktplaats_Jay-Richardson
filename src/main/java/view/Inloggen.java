package view;

import controller.dao.GebruikersDao;
import exception.GebruikerNietGevonden;
import lombok.extern.log4j.Log4j2;
import model.entity.Gebruiker;
import view.gebruiker.GebruikerScherm;
import view.gebruiker.VoorwaardenScherm;

import javax.persistence.NoResultException;

import static config.EntityManager.em;
import static view.View.*;

@Log4j2
public class Inloggen {

    private String email;
    private String wachtwoord;
    private Gebruiker huidigeGebruiker;
    private final GebruikersDao gebruikersDao = GebruikersDao.instance(em);

    public void start(){
        log.info("Inloggen gebruiker");
        header("Inloggen");
        getGegevens();

        if(verifiëren(email, wachtwoord, gebruikersDao)){
            log.info("Controleren of gebruiker akkoord is gegaan met algemene voorwaarden");
            if (huidigeGebruiker.isAkkoordMetVoorwaarde()){
                log.info("Gebruiker heeft algemene voorwaarde reeds geaccepteerd");
                naarGebruikerScherm(huidigeGebruiker);
            }
            else{
                log.info("Gebruiker moet algemene voorwaarde nog accepteren");
                println("U heeft de algemene voorwaarden nog niet geaccepteerd");
                huidigeGebruiker.setAkkoordMetVoorwaarde(new VoorwaardenScherm().start());
                start();
            }
        }
        else {start();}
    }

    protected void getGegevens(){
        log.info("Gegevens van gebruiker vragen");
        print("Email: ");
        email = readLine();
        print("Wachtwoord: ");
        wachtwoord = readLine();
    }

    protected boolean verifiëren(String email, String wachtwoord, GebruikersDao gebruikersDao) {
        log.info("Gegevens verifiëren");
        try {
            log.info("Gebruiker zoeken");
            Gebruiker gebruiker = gebruikersDao.findByEmail(email);
            log.info("Wachtwoord controleren");
            if (gebruiker.getPassword().equals(wachtwoord)){
                huidigeGebruiker = gebruiker;
                log.info("Inloggegevens correct ingevuld");
                return true;
            }else{
                log.error("Fout wachtwoord");
                printErr("Inlog gegevens kloppen niet");
            }
        } catch (NoResultException e){
            log.error("Geen gebruiker gevonden met email: " + email);
            printErr(new GebruikerNietGevonden(email).getMessage());
        }
        return false;
    }

    private void naarGebruikerScherm(Gebruiker gebruiker) {
        new GebruikerScherm(gebruiker).start();
    }

}
