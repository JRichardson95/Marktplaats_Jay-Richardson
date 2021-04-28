package view.gebruiker;
import static view.View.*;
public class VoorwaardenScherm {
    public boolean start(){
        header("Voorwaarde");

        println("algemene voorwaarde");
        println("Gaat u akkoord met de voorwaarde?");
        print("Ja/Nee: ");
        return (akkoordMetVoorwaarde(readLine()));

    }

    private boolean akkoordMetVoorwaarde(String keuze) {
        return keuze.toLowerCase().equals("ja");
    }
}
