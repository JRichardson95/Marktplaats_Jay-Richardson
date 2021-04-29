package view.gebruiker;

import model.Adres;
import model.entity.Gebruiker;
import model.enums.Bezorgwijze;

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
        Set<Bezorgwijze> bezorgwijzeSet = new HashSet<>();

        Stream.of(keuzes.split(" "))
                .flatMapToInt(num -> IntStream.of(Integer.parseInt(num)))
                .forEach(i -> bezorgwijzeSet.add(Bezorgwijze.values()[i]));

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