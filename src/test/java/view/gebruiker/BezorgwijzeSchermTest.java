package view.gebruiker;

import model.Adres;
import model.enums.Bezorgwijze;
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

        Set<Bezorgwijze> verkregenBezorgWijze = BezorgwijzeScherm.bezorgwijzeSet("0 2");

        Assert.assertEquals(bezorgwijzeSet, verkregenBezorgWijze);
    }

    @Test
    void bezorgwijzeSetNotEquals() {
        Set<Bezorgwijze> bezorgwijzeSet = new HashSet<>();
        bezorgwijzeSet.add(Bezorgwijze.AFHALEN_MAGAZIJN);
        bezorgwijzeSet.add(Bezorgwijze.VERSTUREN);

        Set<Bezorgwijze> verkregenBezorgWijze = BezorgwijzeScherm.bezorgwijzeSet("0 3");

        Assert.assertNotEquals(bezorgwijzeSet, verkregenBezorgWijze);
    }

    @Test
    void adresVragen() {
        Set<Bezorgwijze> setZonderThuisAfhalen = new HashSet<>();
        Adres adres = new Adres();
        Assert.assertEquals(adres, BezorgwijzeScherm.adresVragen(setZonderThuisAfhalen));
    }

//    @Test
//    void adresVragenAdres() {
//        BezorgwijzeScherm.bezorgwijzeSet("12");
//        Assert.assertNotEquals(new Adres(), BezorgwijzeScherm.adresVragen());
//    }
    }
