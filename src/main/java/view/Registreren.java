package view;

import controller.dao.GebruikersDao;
import model.Adres;
import model.Bezorgwijze;
import model.Postcode;
import model.Status;
import model.entity.Gebruiker;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Registreren {
    private final EntityManager em =
            Persistence.createEntityManagerFactory("MySQL-Marktplaats").createEntityManager();


    private BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    private boolean adresVragen = false;

    public static void main(String[] args) {
        System.out.println("gelukt");
    }

    public void start() {
        nieuweGebruiker();
    }

    public void nieuweGebruiker() {

        System.out.printf("Registreren nieuwe gebruiker\n");

        Gebruiker nieuweGebruiker = new Gebruiker();

        try {
            System.out.println("Voornaam:");
            nieuweGebruiker.setVoornaam(read.readLine());
            System.out.println("Achternaam:");
            nieuweGebruiker.setAchternaam(read.readLine());
            System.out.println("Email:");
            nieuweGebruiker.setEmail(read.readLine());

            nieuweGebruiker.setBezorgwijzeSet(bezorgWijze());
            if (adresVragen) nieuweGebruiker.setAdres(adresPrompt());

            nieuweGebruiker.setStatus(Status.ACTIEF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        GebruikersDao gebruikersDao = new GebruikersDao(em);
        gebruikersDao.save(nieuweGebruiker);

        System.out.println(nieuweGebruiker.toString());
    }

    public Set<Bezorgwijze> bezorgWijze() {
        System.out.println("Welke bezorgwijze wilt u ondersteunen?");
        Arrays.asList(Bezorgwijze.values()).forEach(System.out::println);

        List<Integer> gekozenBezorgwijze = new ArrayList<Integer>();
        String keuze = null;
        try {
            keuze = read.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
        for (int i = 0; i < keuze.length(); i++) {
            gekozenBezorgwijze.add(Integer.parseInt(
                    String.valueOf(keuze.charAt(i))) - 1);
        }

        if (gekozenBezorgwijze.contains(1)) adresVragen = true;


        System.out.println("\nU heeft de volgende bezorgwijze geselecteerd:");
        Set<Bezorgwijze> bezorgwijzeSet = new HashSet<>();
        gekozenBezorgwijze.forEach(i -> bezorgwijzeSet.add(Bezorgwijze.values()[i]));
        return bezorgwijzeSet;
    }


    public Adres adresPrompt() {
        Adres adres = new Adres();
        try {
            Postcode postcode = new Postcode();
            System.out.println("-----------------------");
            System.out.println("Wat is uw adres?");
            System.out.println("Adres:");
            System.out.println("    Straat:");
            adres.setStraat(read.readLine());
            System.out.println("    Huisnummer:");
            adres.setHuisnummer(Integer.parseInt(read.readLine()));
            System.out.println("    Postcode:");
            String postcodeInput = read.readLine();
            postcode.setPostcodeCijfers(Integer.parseInt(postcodeInput.substring(0, 4)));
            postcode.setPostcodeLetters(postcodeInput.substring(4, 6));
            postcode.setPostcode();
            adres.setPostcode(postcode);
            System.out.println("    Stad:");
            adres.setStad(read.readLine());
            System.out.println("    Provincie");
            adres.setProvincie(read.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return adres;
    }
}
