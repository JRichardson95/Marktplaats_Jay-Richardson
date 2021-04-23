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
import java.io.InputStreamReader;
import java.util.*;

public class Registreren {
    private final EntityManager em =
            Persistence.createEntityManagerFactory("MySQL-Marktplaats").createEntityManager();
    View view = new View();
    Gebruiker nieuweGebruiker = new Gebruiker();
    private final BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    private boolean adresVragen = false;

    public static void main(String[] args) {
        System.out.println("gelukt");
    }

    public void start() {
        nieuweGebruiker();
    }

    public void nieuweGebruiker() {

        view.printLine();
        System.out.println("Registreren nieuwe gebruiker\n");
        view.printLine();


        System.out.print("Voornaam:");
        nieuweGebruiker.setVoornaam(readLine());
        System.out.print("Achternaam:");
        nieuweGebruiker.setAchternaam(readLine());
        System.out.print("Email:");
        nieuweGebruiker.setEmail(readLine());
        view.printLine();

        nieuweGebruiker.setBezorgwijzeSet(bezorgWijze());

        printBezorgwijze();

        if (adresVragen) nieuweGebruiker.setAdres(adresPrompt());
        nieuweGebruiker.setStatus(Status.ACTIEF);

        GebruikersDao gebruikersDao = new GebruikersDao(em);
        gebruikersDao.save(nieuweGebruiker);

        System.out.println(nieuweGebruiker.toString());
    }

    public Set<Bezorgwijze> bezorgWijze() {
        System.out.println("Welke bezorgwijze wilt u ondersteunen?");
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
        view.printLine();
        System.out.println("U heeft de volgende bezorgwijzen geselecteerd:");
        nieuweGebruiker.getBezorgwijzeSet().forEach(System.out::println);
        view.printLine();
    }


    public Adres adresPrompt() {
        Adres adres = new Adres();
        System.out.println("U heeft als bezorgwijze \"Thuis afhalen\" geselecteerd.");
        System.out.println("Adres: ");
        System.out.print("    Straat: ");
        adres.setStraat(readLine());
        System.out.print("    Huisnummer: ");
        adres.setHuisnummer(Integer.parseInt(readLine()));
        System.out.print("    Postcode: ");
        adres.setPostcode(postcode(readLine()));
        System.out.print("    Stad: ");
        adres.setStad(readLine());
        System.out.print("    Provincie: ");
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

    public String readLine() {
        try {
            return read.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Er ging iets mis met het invoeren";
    }
}
