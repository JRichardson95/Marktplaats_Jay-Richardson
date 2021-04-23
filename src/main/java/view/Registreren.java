package view;

import controller.dao.GebruikersDao;
import model.Adres;
import model.Bezorgwijze;
import model.Postcode;
import model.Status;
import model.entity.Gebruiker;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.*;

import static view.View.*;

public class Registreren {
    private final EntityManager em =
            Persistence.createEntityManagerFactory("MySQL-Marktplaats").createEntityManager();
    private final Gebruiker nieuweGebruiker = new Gebruiker();
    private boolean adresVragen = false;

    public void nieuweGebruiker() {
        GebruikersDao gebruikersDao = new GebruikersDao(em);

        header("Registreren nieuwe gebruiker");

        print("Voornaam:");
        nieuweGebruiker.setVoornaam(readLine());
        print("Achternaam:");
        nieuweGebruiker.setAchternaam(readLine());
        print("Email:");
        nieuweGebruiker.setEmail(readLine());
        divider();

        nieuweGebruiker.setBezorgwijzeSet(bezorgWijze());

        printBezorgwijze();

        adresVragen();

        nieuweGebruiker.setStatus(Status.ACTIEF);

        gebruikersDao.save(nieuweGebruiker);

        println(nieuweGebruiker.toString());

        new BeginScherm().start();
    }

    public Set<Bezorgwijze> bezorgWijze() {
        println("Welke bezorgwijze wilt u ondersteunen?");
        Arrays.asList(Bezorgwijze.values()).forEach(System.out::println);
        return bezorgwijzeSet(readLine());
    }

    public Set<Bezorgwijze> bezorgwijzeSet(String keuzes) {
        List<Integer> gekozenBezorgwijze = new ArrayList<>();

        for (int i = 0; i < keuzes.length(); i++) {
            if(Character.isDigit(keuzes.charAt(i))){
            gekozenBezorgwijze.add(Integer.parseInt(
                    String.valueOf(keuzes.charAt(i))) - 1);
            }
        }

        adresVragen = gekozenBezorgwijze.contains(1);

        Set<Bezorgwijze> bezorgwijzeSet = new HashSet<>();
        gekozenBezorgwijze.forEach(i -> bezorgwijzeSet.add(Bezorgwijze.values()[i]));
        return bezorgwijzeSet;
    }

    private void printBezorgwijze() {
        divider();
        println("U heeft de volgende bezorgwijzen geselecteerd:");
        nieuweGebruiker.getBezorgwijzeSet().forEach(System.out::println);
        divider();
    }

    public void adresVragen(){
        if (adresVragen){
            nieuweGebruiker.setAdres(adresPrompt());
        }else{
            nieuweGebruiker.setAdres(new Adres());
        }
    }


    public Adres adresPrompt() {
        Adres adres = new Adres();
        println("U heeft als bezorgwijze \"Thuis afhalen\" geselecteerd.");
        println("Adres: ");
        print("    Straat: ");
        adres.setStraat(readLine());
        print("    Huisnummer: ");
        adres.setHuisnummer(Integer.parseInt(readLine()));
        print("    Postcode: ");
        adres.setPostcode(postcode(readLine()));
        print("    Stad: ");
        adres.setStad(readLine());
        print("    Provincie: ");
        adres.setProvincie(readLine());
        return adres;
    }

    public Postcode postcode(String postcodeInput) {
        Postcode postcode = new Postcode();
        postcode.setPostcodeCijfers(Integer.parseInt(postcodeInput.substring(0, 4)));
        postcode.setPostcodeLetters(postcodeInput.substring(4, 6));
        postcode.setPostcode();
        return postcode;
    }
}
