package view.advertentie;

import controller.dao.AdvertentieDao;
import lombok.extern.log4j.Log4j2;
import model.entity.Advertentie;
import model.entity.Gebruiker;

import java.util.List;

import static config.EntityManager.EM;
import static view.View.header;

@Log4j2
public class AdvertentieBekijkenScherm {
    private final Gebruiker HUIDIGE_GEBRUIKER;
    private final AdvertentieDao ADVERTENTIE_DAO = AdvertentieDao.instance(EM);

    public AdvertentieBekijkenScherm(Gebruiker huidigeGebruiker) {
        this.HUIDIGE_GEBRUIKER = huidigeGebruiker;
    }

    public void eigenAdvertentiesBekijken() {
        log.info("Advertenties van de gebruiker weergeven");
        header("Advertenties van: " + HUIDIGE_GEBRUIKER.getNaam());
        List<Advertentie> advertentieList = ADVERTENTIE_DAO.findByGebruikerId(HUIDIGE_GEBRUIKER.getId());
        advertentieList.forEach(System.out::println);
        new view.gebruiker.GebruikerScherm(HUIDIGE_GEBRUIKER);
    }
}
