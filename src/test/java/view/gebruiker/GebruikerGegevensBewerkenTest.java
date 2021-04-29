package view.gebruiker;

import controller.dao.GebruikersDao;
import model.entity.Gebruiker;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Persistence;

class GebruikerGegevensBewerkenTest {

    private final javax.persistence.EntityManager em =
            Persistence.createEntityManagerFactory("H2-MarktplaatsTest").createEntityManager();

    private GebruikerGegevensBewerken target;
    private Gebruiker testGebruiker;

    private final GebruikersDao gebruikersDao = new GebruikersDao(em);

    @BeforeEach
    void setUp() {
        target = new GebruikerGegevensBewerken(new Gebruiker());
        testGebruiker = Gebruiker.builder()
                .voornaam("Jay")
                .achternaam("Richardson")
                .email("test@gmail.com")
                .password("testWachtwoord")
                .build();

        gebruikersDao.save(testGebruiker);
    }

    @Test
    void testUpdate(){
        Gebruiker voorUpdate = gebruikersDao.find(1L);

        Assert.assertTrue(voorUpdate.getNaam().equals("Jay Richardson"));

        testGebruiker.setAchternaam("Test");
        target.updateGebruiker(gebruikersDao, testGebruiker);

        Gebruiker naUpdate = gebruikersDao.find(1L);

        Assert.assertTrue(naUpdate.getNaam().equals("Jay Test"));
    }

}