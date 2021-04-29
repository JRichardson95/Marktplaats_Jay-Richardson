package view.advertentie;

import model.entity.Gebruiker;
import model.enums.Bezorgwijze;
import model.enums.Categorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AdvertentieAanmakenSchermTest {

    private Gebruiker testGebruiker;
    private AdvertentieAanmakenScherm advertentieAanmakenScherm;

    @BeforeEach
    void setUp() {
        testGebruiker = new Gebruiker();
        advertentieAanmakenScherm = new AdvertentieAanmakenScherm(testGebruiker);
    }

    @Test
    void selecteerCategorie() {
        assertEquals(Categorie.KLUSSEN, advertentieAanmakenScherm.selecteerCategorie("klussen"));
        assertEquals(Categorie.SCHILDEREN, advertentieAanmakenScherm.selecteerCategorie("Schilderen"));
        assertEquals(Categorie.COMPUTERS, advertentieAanmakenScherm.selecteerCategorie("computers"));
        assertEquals(Categorie.MEUBELS, advertentieAanmakenScherm.selecteerCategorie("meubels"));
        assertNull(advertentieAanmakenScherm.selecteerCategorie("meubel"));
    }

    @Test
    void selecteerBezorgwijze() {
        Set<Bezorgwijze> testSet = new HashSet<>();
        testSet.add(Bezorgwijze.VERSTUREN);
        testSet.add(Bezorgwijze.AFHALEN_MAGAZIJN);

        testGebruiker.setBezorgwijzeSet(testSet);

        assertTrue(advertentieAanmakenScherm.selecteerBezorgwijze("2").contains(Bezorgwijze.VERSTUREN));
        assertFalse(advertentieAanmakenScherm.selecteerBezorgwijze("2").contains(Bezorgwijze.AFHALEN_MAGAZIJN));
    }

}