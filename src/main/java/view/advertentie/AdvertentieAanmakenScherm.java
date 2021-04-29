package view.advertentie;

import controller.dao.AdvertentieDao;
import lombok.extern.log4j.Log4j2;
import model.entity.Advertentie;
import model.entity.Gebruiker;
import model.enums.Bezorgwijze;
import model.enums.Categorie;
import view.gebruiker.GebruikerScherm;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static config.EntityManager.em;
import static view.View.*;

@Log4j2
public class AdvertentieAanmakenScherm {
    private final Gebruiker huidigeGebruiker;
    private final AdvertentieDao advertentieDao = new AdvertentieDao(em);
    private Advertentie advertentie = new Advertentie();
    private Set<Bezorgwijze> bezorgwijzeSet = new HashSet<>();

    public AdvertentieAanmakenScherm(Gebruiker huidigeGebruiker) {
        this.huidigeGebruiker = huidigeGebruiker;
    }

    public void start() {
        log.info("Advertentie aanmaken");
        header("Advertentie aanmaken");

        log.info("Ingelogde gebruiker toevoegen aan advertentie");
        advertentie.setGebruiker(huidigeGebruiker);

        log.info("Gegevens voor advertentie opvragen");
        getGegevens();

        log.info("Terug naar hoofdscherm gebruiker");
        new GebruikerScherm(huidigeGebruiker).start();
    }

    public void getGegevens() {
        print("Titel: ");
        log.info("Titel van advertentie opslaan");
        advertentie.setTitel(readLine());
        divider();

        toonCategorieën();

        printPointer();
        advertentie.setCategorie(selecteerCategorie(readLine()));

        print("Omschrijving: ");
        log.info("Omschrijving toevoegen aan advertentie");
        advertentie.setOmschrijving(readLine());

        print("prijs: ");
        log.info("Prijs aan advertentie toevoegen");
        advertentie.setPrijs(readBigDecimal());

        toonBezorgwijze();
        printPointer();
        advertentie.setOndersteundeBezorgwijze(selecteerBezorgwijze(readLine()));

        advertentieDao.save(advertentie);
    }

    protected void toonCategorieën() {
        log.info("Categorieën tonen");
        println("Welke categorie wilt u selecteren: ");
        for (Categorie categorie : Categorie.values()) {
            println(categorie.toString());
        }
        divider();
    }

    protected Categorie selecteerCategorie(String categorie) {
        log.info("Juiste categorie aan advertentie toevoegen");
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
                log.error("Foute categorie gekozen");
                return null;
        }
    }

    protected void toonBezorgwijze() {
        log.info("Door gebruiker ondersteunde bezorgwijze weergeven");
        println("Welk van uw bezorgwijze wilt u voor deze advertentie ondersteunen:");
        huidigeGebruiker.getBezorgwijzeSet().forEach(System.out::println);
        println("Gescheiden door een spatie");
        divider();
    }

    protected Set<Bezorgwijze> selecteerBezorgwijze(String keuzes) {
        log.info("Bezorgwijze toevoegen aan advertentie.");
        Stream.of(keuzes.split(" "))
                .flatMapToInt(num -> IntStream.of(Integer.parseInt(num)))
                .forEach(i -> controleerBezorgwijze(Bezorgwijze.values()[i]));
        return bezorgwijzeSet;
    }

    protected void controleerBezorgwijze(Bezorgwijze bezorgwijze) {
        log.info("Controleren of gekozen bezorgwijze in het account van de gebruiker wordt ondersteund");
        if (huidigeGebruiker.getBezorgwijzeSet().contains(bezorgwijze)) {
            bezorgwijzeSet.add(bezorgwijze);
        } else {
            log.error("Niet ondersteunde bezorgwijze gekozen");
            println("U heeft een bezorgwijze geselecteerd die u niet in uw account heeft opgenomen");
            toonBezorgwijze();
            selecteerBezorgwijze(readLine());
        }
    }

}
