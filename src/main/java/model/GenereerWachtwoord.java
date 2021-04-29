package model;

import net.bytebuddy.utility.RandomString;

public class GenereerWachtwoord {
    public static String maakWachtwoord(){
        String wachtwoord = RandomString.make();
        return wachtwoord;
    }
}
