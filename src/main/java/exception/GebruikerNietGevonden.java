package exception;

public class GebruikerNietGevonden extends Exception{
    public GebruikerNietGevonden(String email){
        super("Geen gebruiker gevonden met email: " + email);
    }
}
