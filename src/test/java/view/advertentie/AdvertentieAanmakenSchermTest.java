package view.advertentie;

import model.Bezorgwijze;
import model.Categorie;
import model.entity.Gebruiker;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

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
        Assert.assertEquals(Categorie.KLUSSEN, advertentieAanmakenScherm.selecteerCategorie("klussen"));
        Assert.assertEquals(Categorie.SCHILDEREN, advertentieAanmakenScherm.selecteerCategorie("Schilderen"));
        Assert.assertEquals(Categorie.COMPUTERS, advertentieAanmakenScherm.selecteerCategorie("computers"));
        Assert.assertEquals(Categorie.MEUBELS, advertentieAanmakenScherm.selecteerCategorie("meubels"));
        Assert.assertEquals(null, advertentieAanmakenScherm.selecteerCategorie("meubel"));
    }

    @Test
    void selecteerBezorgwijze() {
        Set<Bezorgwijze> testSet = new HashSet<>();
        testSet.add(Bezorgwijze.VERSTUREN);
        testSet.add(Bezorgwijze.AFHALEN_MAGAZIJN);

        testGebruiker.setBezorgwijzeSet(testSet);

        Assert.assertTrue(advertentieAanmakenScherm.selecteerBezorgwijze("2").contains(Bezorgwijze.VERSTUREN));
        Assert.assertFalse(advertentieAanmakenScherm.selecteerBezorgwijze("2").contains(Bezorgwijze.AFHALEN_MAGAZIJN));
    }

}