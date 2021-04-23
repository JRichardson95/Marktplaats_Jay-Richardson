package view;

import static view.View.*;

public class BeginScherm {
    public static void main(String[] args){
        new BeginScherm().start();
    }

    public void start() {
        prompt();
        new BeginScherm().naarVolgendScherm(Integer.parseInt(readLine()));
    }

    public void prompt() {
        header("Welkom op marktplaats");
        System.out.println("Wat wilt u doen:");
        System.out.println("" +
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
                System.out.println("Verkeerde input");
                start();

        }
    }


}
