package model;

import net.bytebuddy.utility.RandomString;

public class GenereerWachtwoord {
    public static String maakWachtwoord(){
        return RandomString.make();
    }
}
