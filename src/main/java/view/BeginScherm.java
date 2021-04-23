package view;


public class BeginScherm {
    private final View view = new View();
    public static void main(String[] args){
        new BeginScherm().start();
    }

    public void start() {
        prompt();
        new BeginScherm().naarVolgendScherm(Integer.parseInt(view.readLine()));
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

        }
    }


}
