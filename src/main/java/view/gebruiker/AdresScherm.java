package view.gebruiker;

import lombok.extern.log4j.Log4j2;
import model.Adres;
import model.Postcode;

import static view.View.*;

@Log4j2
public class AdresScherm {

    public static Adres adresPrompt() {
        log.info("Adres vragen");
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
