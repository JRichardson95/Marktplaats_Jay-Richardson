package view.advertentie;

import controller.dao.AdvertentieDao;
import model.Bezorgwijze;
import model.Categorie;
import model.entity.Advertentie;
import model.entity.Gebruiker;
import view.gebruiker.GebruikerScherm;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static config.EntityManager.em;
import static view.View.*;

public class AdvertentieAanmakenScherm {
    private final Gebruiker huidigeGebruiker;
    private final AdvertentieDao advertentieDao = new AdvertentieDao(em);
    private Advertentie advertentie = new Advertentie();
    private Set<Bezorgwijze> bezorgwijzeSet = new HashSet<>();

    public AdvertentieAanmakenScherm(Gebruiker huidigeGebruiker) {
        this.huidigeGebruiker = huidigeGebruiker;
    }

    public void start() {
        header("Advertentie aanmaken");
        advertentie.setGebruiker(huidigeGebruiker);
        getGegevens();
        new GebruikerScherm(huidigeGebruiker).start();
    }

    public void getGegevens() {
        print("Titel: ");
        advertentie.setTitel(readLine());
        divider();

        toonCategorieën();

        printPointer();
        advertentie.setCategorie(selecteerCategorie(readLine()));

        print("Omschrijving: ");
        advertentie.setOmschrijving(readLine());

        print("prijs: ");
        advertentie.setPrijs(readBigDecimal());

        toonBezorgwijze();
        printPointer();
        advertentie.setOndersteundeBezorgwijze(selecteerBezorgwijze(readLine()));

        advertentieDao.save(advertentie);
    }

    private void toonCategorieën() {
        println("Welke categorie wilt u selecteren: ");
        for (Categorie categorie : Categorie.values()) {
            println(categorie.toString());
        }
        divider();
    }

    private Categorie selecteerCategorie(String categorie) {
        switch (categorie.toLowerCase()) {
            case "klussen":
                return Categorie.KLUSSEN;
            case "schilderen":
                return Categorie.SCHILDEREN;
            case "computers":
                return Categorie.COMPUTERS;
            case "meubels":
                return Categorie.MEUBELS;
            default:
                return null;
        }
    }

    private void toonBezorgwijze() {
        println("Welk van uw bezorgwijze wilt u voor deze advertentie ondersteunen:");
        huidigeGebruiker.getBezorgwijzeSet().forEach(System.out::println);
        println("Gescheiden door een spatie");
        divider();
    }

    public Set<Bezorgwijze> selecteerBezorgwijze(String keuzes) {
        Stream.of(keuzes.split(" "))
                .flatMapToInt(num -> IntStream.of(Integer.parseInt(num)))
                .forEach(i -> controleerBezorgwijze(Bezorgwijze.values()[i]));
        return bezorgwijzeSet;
    }

    private void controleerBezorgwijze(Bezorgwijze bezorgwijze){
        if (huidigeGebruiker.getBezorgwijzeSet().contains(bezorgwijze)){
            bezorgwijzeSet.add(bezorgwijze);
        }else{
            println("U heeft een bezorgwijze geselecteerd die u niet in uw account heeft opgenomen");
            toonBezorgwijze();
            selecteerBezorgwijze(readLine());
        }
    }

}
