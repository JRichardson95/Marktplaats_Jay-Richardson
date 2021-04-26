package view;

import controller.dao.GebruikersDao;
import model.entity.Gebruiker;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.persistence.Persistence;


class InloggenTest {
    private final javax.persistence.EntityManager em =
            Persistence.createEntityManagerFactory("H2-MarktplaatsTest").createEntityManager();

    private Inloggen target;
    private Gebruiker testGebruiker;

    @Mock
    private GebruikersDao targetDao;


    @BeforeEach
    void setUp() {
        target = new Inloggen();
        targetDao = new GebruikersDao(em);
        testGebruiker = Gebruiker.builder()
                .voornaam("Jay")
                .achternaam("Richardson")
                .email("test@gmail.com")
                .password("testWachtwoord")
                .build();

        targetDao.save(testGebruiker);
    }

    @Test
    void testVerifiëren(){
        String email = "test@gmail.com";
        String password = "testWachtwoord";

        Assert.assertEquals(true, target.verifiëren(email, password));

    }
}