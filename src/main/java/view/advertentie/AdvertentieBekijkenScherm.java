package view.advertentie;

import controller.dao.AdvertentieDao;
import controller.dao.GebruikersDao;
import model.entity.Advertentie;
import model.entity.Gebruiker;

import java.util.List;

import static config.EntityManager.em;
import static view.View.*;

public class AdvertentieBekijkenScherm {
    private final Gebruiker gebruiker;
    private final AdvertentieDao advertentieDao = AdvertentieDao.instance(em);

    public AdvertentieBekijkenScherm(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    public void eigenAdvertentiesBekijken(){
        header("Advertenties van: " + gebruiker.getNaam() );
        List<Advertentie> advertentieList = advertentieDao.findByGebruikerId(gebruiker.getId());
        advertentieList.forEach(System.out::println);
    }
}
