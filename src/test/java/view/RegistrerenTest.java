package view;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

class RegistrerenTest {

    private static final EntityManager entityManager = Persistence.
            createEntityManagerFactory("H2-MarktplaatsTest")
            .createEntityManager();


//    @BeforeAll

    @Test
    void nieuweGebruiker() {

    }

    @Test
    void bezorgWijze() {
    }

    @Test
    void bezorgwijzeSet() {
    }

    @Test
    void adresVragen() {
    }

    @Test
    void adresPrompt() {
    }

    @Test
    void postcode() {
    }
}