package view;

import static view.View.*;
public class Inloggen {
    private String email;
    private String wachtwoord;

    public void start(){
        header("Inloggen");

        print("Email: ");
        email = readLine();
        print("Wachtwoord: ");
        wachtwoord = readLine();
    }
}
