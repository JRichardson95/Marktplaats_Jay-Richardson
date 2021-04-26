package view.gebruiker;

import model.Adres;
import model.Bezorgwijze;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;


class BezorgwijzeSchermTest {


    @Test
    void bezorgwijzeSetEquals() {
        Set<Bezorgwijze> bezorgwijzeSet = new HashSet<>();
        bezorgwijzeSet.add(Bezorgwijze.AFHALEN_MAGAZIJN);
        bezorgwijzeSet.add(Bezorgwijze.VERSTUREN);

        Set<Bezorgwijze> verkregenBezorgWijze = BezorgwijzeScherm.bezorgwijzeSet("13");

        Assert.assertEquals(bezorgwijzeSet, verkregenBezorgWijze);
    }

    @Test
    void bezorgwijzeSetNotEquals() {
        Set<Bezorgwijze> bezorgwijzeSet = new HashSet<>();
        bezorgwijzeSet.add(Bezorgwijze.AFHALEN_MAGAZIJN);
        bezorgwijzeSet.add(Bezorgwijze.VERSTUREN);

        Set<Bezorgwijze> verkregenBezorgWijze = BezorgwijzeScherm.bezorgwijzeSet("14");

        Assert.assertNotEquals(bezorgwijzeSet, verkregenBezorgWijze);
    }

    @Test
    void adresVragen() {
        Adres adres = new Adres();
        Assert.assertEquals(adres, BezorgwijzeScherm.adresVragen());
    }

//    @Test
//    void adresVragenAdres() {
//        BezorgwijzeScherm.bezorgwijzeSet("12");
//        Assert.assertNotEquals(new Adres(), BezorgwijzeScherm.adresVragen());
//    }
    }
