package view.gebruiker;

import model.Adres;
import model.Bezorgwijze;
import model.entity.Gebruiker;

import java.util.*;

import static view.View.*;
import static view.View.readLine;
import static view.gebruiker.AdresScherm.adresPrompt;

public class BezorgwijzeScherm {
    private static boolean adresVragen = false;

    public static Set<Bezorgwijze> vraagBezorgWijze() {
        println("Welke bezorgwijze wilt u ondersteunen?");
        Arrays.asList(Bezorgwijze.values()).forEach(System.out::println);
        return bezorgwijzeSet(readLine());
    }

    public static Set<Bezorgwijze> bezorgwijzeSet(String keuzes) {
        List<Integer> gekozenBezorgwijze = new ArrayList<>();

        for (int i = 0; i < keuzes.length(); i++) {
            if(Character.isDigit(keuzes.charAt(i))){
                gekozenBezorgwijze.add(Integer.parseInt(
                        String.valueOf(keuzes.charAt(i))) - 1);
            }
        }

        Set<Bezorgwijze> bezorgwijzeSet = new HashSet<>();
        gekozenBezorgwijze.forEach(i -> bezorgwijzeSet.add(Bezorgwijze.values()[i]));
        return bezorgwijzeSet;
    }

    public static void printBezorgwijze(Gebruiker gebruiker) {
        divider();
        println("U heeft de volgende bezorgwijzen geselecteerd:");
        gebruiker.getBezorgwijzeSet().forEach(System.out::println);
        divider();
    }

    public static Adres adresVragen(Set<Bezorgwijze> bezorgwijzeSet){
        if (isAdresVragen(bezorgwijzeSet)){return adresPrompt();}
        return new Adres();
    }

    public static boolean isAdresVragen(Set<Bezorgwijze> bezorgwijzeSet){
        return bezorgwijzeSet.contains(Bezorgwijze.THUIS_AFHALEN);
    }
}