package view.gebruiker;

import controller.dao.GebruikersDao;
import model.entity.Gebruiker;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.gebruiker.Inloggen;

import javax.persistence.Persistence;


class InloggenTest {
    private final javax.persistence.EntityManager em =
            Persistence.createEntityManagerFactory("H2-MarktplaatsTest").createEntityManager();

    private Inloggen target;
    private Gebruiker testGebruiker;
    private GebruikersDao targetDao;

    @BeforeEach
    void setUp() {
        target = new Inloggen();
        targetDao = new GebruikersDao(em);
        testGebruiker = Gebruiker.builder()
                .voornaam("Jay")
                .achternaam("Richardson")
                .email("123@gmail.com")
                .password("testWachtwoord")
                .build();

        targetDao.save(testGebruiker);
    }

    @Test
    void testVerifiëren(){
        String email = "123@gmail.com";
        String password = "testWachtwoord";
        Assert.assertTrue(target.verifiëren(email, password, targetDao));
    }
}