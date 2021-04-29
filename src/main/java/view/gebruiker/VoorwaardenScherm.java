package view.gebruiker;
import lombok.extern.log4j.Log4j2;

import static view.View.*;

@Log4j2
public class VoorwaardenScherm {
    public boolean start(){
        log.info("Voorwaarde scherm");
        header("Voorwaarde");

        println("algemene voorwaarde");
        println("Gaat u akkoord met de voorwaarde?");
        print("Ja/Nee: ");
        return (akkoordMetVoorwaarde(readLine()));

    }

    private boolean akkoordMetVoorwaarde(String keuze) {
        return keuze.equalsIgnoreCase("ja");
    }
}
