package view;

import lombok.extern.log4j.Log4j2;
import view.gebruiker.Inloggen;
import view.gebruiker.Registreren;

import static view.View.*;

@Log4j2
public class BeginScherm {
    public static void main(String[] args){
        new BeginScherm().start();
    }

    public void start() {
        prompt();
        printPointer();
        new BeginScherm().naarVolgendScherm(Integer.parseInt(readLine()));
    }

    public void prompt() {
        header("Welkom op marktplaats");
        println("Wat wilt u doen:");
        println("" +
                "1: registreren\n" +
                "2: inloggen");
        divider();
    }

    public void naarVolgendScherm(int input) {
        switch(input){
            case 1:
                new Registreren().nieuweGebruiker();
                break;
            case 2:
                new Inloggen().start();
                break;
            default:
                println("Verkeerde input");
                start();

        }
    }


}
