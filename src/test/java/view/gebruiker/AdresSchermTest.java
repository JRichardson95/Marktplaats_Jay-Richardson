package view.gebruiker;

import model.Postcode;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class AdresSchermTest {

    @Test
    void postcodeEquals() {
        Postcode verwachtePostcode = new Postcode();
        verwachtePostcode.setPostcodeCijfers(1112);
        verwachtePostcode.setPostcodeLetters("WZ");

    Postcode postcode = AdresScherm.postcode("1112WZ");

        Assert.assertEquals(verwachtePostcode.toString(), postcode.toString());
    }

    @Test
    void PostcodeNotEquals(){
        Postcode verwachtePostcode = new Postcode();
        verwachtePostcode.setPostcodeCijfers(1112);
        verwachtePostcode.setPostcodeLetters("WZ");

        Postcode postcode = AdresScherm.postcode("1112GH");

        Assert.assertNotEquals(verwachtePostcode.toString(), postcode.toString());
    }
}