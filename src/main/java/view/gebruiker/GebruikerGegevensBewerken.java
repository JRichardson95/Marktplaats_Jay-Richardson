package view.gebruiker;

import controller.dao.GebruikersDao;
import model.entity.Gebruiker;

import static config.EntityManager.em;
import static view.View.*;
import static view.gebruiker.AdresScherm.adresPrompt;
import static view.gebruiker.BezorgwijzeScherm.adresVragen;
import static view.gebruiker.BezorgwijzeScherm.vraagBezorgWijze;

public class GebruikerGegevensBewerken {
    private Gebruiker huidigeGebruiker;
    private GebruikersDao gebruikersDao = new GebruikersDao(em);

    public GebruikerGegevensBewerken(Gebruiker huidigeGebruiker) {
        this.huidigeGebruiker = huidigeGebruiker;
    }

    public void start(){
        header("Gegevens bewerken");

        println("Welke gegevens wilt u bewerken:\n" +
                "1: Naam\n" +
                "2: Email\n" +
                "3: Wachtwoord\n" +
                "4: Adres\n" +
                "5: Bezorgwijze\n" +
                "6: Gegevens bekijken" +
                "0: Terug naar hoofdmenu");

        keuzeMenu(readInt());
        updateGebruiker();
        new GebruikerScherm(huidigeGebruiker).start();
    }

    private void keuzeMenu(int keuze) {
        switch (keuze){
            case 1:
                naamWijzigen();
                break;
            case 2:
                emailWijzigen();
                break;
            case 3:
                wachtwoordWijzigen();
                break;
            case 4:
                adresWijzigen();
                break;
            case 5:
                bezorgwijzeWijzigen();
                break;
            case 6:
                gegevensWeergeven();
                break;
            case 0:
                new GebruikerScherm(huidigeGebruiker).start();
                break;
            default:
                println("Er is iets misgegaan");
                start();
        }
    }

    private void naamWijzigen() {
        print("Voornaam: ");
        huidigeGebruiker.setVoornaam(readLine());
        print("Achternaam: ");
        huidigeGebruiker.setAchternaam(readLine());
    }

    private void emailWijzigen() {
        println("Huidig e-mailadres: " + huidigeGebruiker.getEmail());
        print("Nieuw e-mailadres: ");
        huidigeGebruiker.setEmail(readLine());
    }

    private void wachtwoordWijzigen() {
        print("Huidig wachtwoord:");
        if (readLine().equals(huidigeGebruiker.getPassword())){
            print("Nieuw wachtwoord: ");
            String wachtwoord = readLine();
            print("Herhaling wachtwoord:");
            if (wachtwoord.equals(readLine())){
                huidigeGebruiker.setPassword(wachtwoord);
            }else{
                print("Wachtwoorden komen niet overeen.");
            }
        }else{
            print("Wachtwoorden komen niet overeen.");
        }
        println("Wachtwoord gewijzigd");
    }

    private void adresWijzigen() {
        huidigeGebruiker.setAdres(adresPrompt());
    }

    private void bezorgwijzeWijzigen() {
        huidigeGebruiker.setBezorgwijzeSet(vraagBezorgWijze());
        if (huidigeGebruiker.getAdres() == null){
            huidigeGebruiker.setAdres(adresVragen());
        }
    }

    private void gegevensWeergeven() {
        println("Voornaam: " + huidigeGebruiker.getVoornaam());
        println("Achternaam: " + huidigeGebruiker.getAchternaam());
        println("E-mail: " + huidigeGebruiker.getEmail());
        if (huidigeGebruiker.getAdres() != null) println("Adres: " + huidigeGebruiker.getAdres().toString());
        println("Bezorgwijze: " + huidigeGebruiker.getBezorgwijzeSet());
        start();
    }

    private void updateGebruiker(){
        gebruikersDao.update(huidigeGebruiker);
        println("update");
    }





}
