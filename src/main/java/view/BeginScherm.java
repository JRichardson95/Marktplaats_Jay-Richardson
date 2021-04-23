package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BeginScherm {
    public static void main(String[] args){
        new BeginScherm().start();
    }
    View view = new View();
    public void start() {

        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        prompt();
        try {
            new BeginScherm().naarVolgendScherm(Integer.parseInt(read.readLine()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void prompt() {
        view.printLine();
        System.out.println("Welkom op marktplaats");
        System.out.println("Wat wilt u doen:");
        System.out.println("" +
                "1: registreren\n" +
                "2: inloggen");
        view.printLine();
    }

    public void naarVolgendScherm(int input) {
        switch(input){
            case 1:
                new Registreren().start();
                break;
            case 2:
                System.out.println("test");
                start();
                break;
            default:
                start();
                ;
        }
    }


}
