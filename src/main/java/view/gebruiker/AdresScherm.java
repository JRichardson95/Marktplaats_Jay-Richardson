package view.gebruiker;

import model.Adres;
import model.Postcode;

import static view.View.*;

public class AdresScherm {

    public static Adres adresPrompt() {
        Adres adres = new Adres();
        println("U heeft als bezorgwijze \"Thuis afhalen\" geselecteerd.");
        println("Adres: ");
        print("    Straat: ");
        adres.setStraat(readLine());
        print("    Huisnummer: ");
        adres.setHuisnummer(Integer.parseInt(readLine()));
        print("    Postcode: ");
        adres.setPostcode(postcode(readLine()));
        print("    Stad: ");
        adres.setStad(readLine());
        print("    Provincie: ");
        adres.setProvincie(readLine());
        return adres;
    }

    public static Postcode postcode(String postcodeInput) {
        Postcode postcode = new Postcode();
        postcode.setPostcodeCijfers(Integer.parseInt(postcodeInput.substring(0, 4)));
        postcode.setPostcodeLetters(postcodeInput.substring(4, 6));
        postcode.setPostcode();
        return postcode;
    }

}
