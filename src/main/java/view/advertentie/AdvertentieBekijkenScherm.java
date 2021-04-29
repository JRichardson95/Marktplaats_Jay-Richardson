package view.advertentie;

import controller.dao.AdvertentieDao;
import lombok.extern.log4j.Log4j2;
import model.entity.Advertentie;
import model.entity.Gebruiker;

import java.util.List;

import static config.EntityManager.em;
import static view.View.header;

@Log4j2
public class AdvertentieBekijkenScherm {
    private final Gebruiker gebruiker;
    private final AdvertentieDao advertentieDao = AdvertentieDao.instance(em);

    public AdvertentieBekijkenScherm(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    public void eigenAdvertentiesBekijken(){
        log.info("Advertenties van de gebruiker weergeven");
        header("Advertenties van: " + gebruiker.getNaam());
        List<Advertentie> advertentieList = advertentieDao.findByGebruikerId(gebruiker.getId());
        advertentieList.forEach(System.out::println);
        new view.gebruiker.GebruikerScherm(gebruiker);
    }
}
