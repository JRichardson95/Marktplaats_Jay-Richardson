package view.gebruiker;

import model.Adres;
import model.Bezorgwijze;
import model.entity.Gebruiker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static view.View.*;
import static view.gebruiker.AdresScherm.adresPrompt;

public class BezorgwijzeScherm {
    public static Set<Bezorgwijze> vraagBezorgWijze() {
        println("Welke bezorgwijze wilt u ondersteunen?");
        Arrays.asList(Bezorgwijze.values()).forEach(System.out::println);
        println("Keuze scheiden met een spatie.");
        printPointer();
        return bezorgwijzeSet(readLine());
    }

    public static Set<Bezorgwijze> bezorgwijzeSet(String keuzes) {
//        List<Integer> gekozenBezorgwijze = new ArrayList<>();
        Set<Bezorgwijze> bezorgwijzeSet = new HashSet<>();

        Stream.of(keuzes.split(" "))
                .flatMapToInt(num -> IntStream.of(Integer.parseInt(num)))
                .forEach(i -> bezorgwijzeSet.add(Bezorgwijze.values()[i]));

//        for (int i = 0; i < keuzes.length(); i++) {
//            if(Character.isDigit(keuzes.charAt(i))){
//                gekozenBezorgwijze.add(Integer.parseInt(
//                        String.valueOf(keuzes.charAt(i))));
//            }
//        }
//
//
//        gekozenBezorgwijze.forEach(i -> bezorgwijzeSet.add(Bezorgwijze.values()[i]));
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