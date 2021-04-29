package model.entity;

import lombok.Data;
import model.enums.Bezorgwijze;
import model.enums.Categorie;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
public class Advertentie implements Identifiable<Long>{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String titel;

    @Enumerated
    private Categorie categorie;

    private String omschrijving;

    @NotNull
    private BigDecimal prijs;

    @ElementCollection
    private Set<Bezorgwijze> ondersteundeBezorgwijze;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Gebruiker gebruiker;

    @Override
    public String toString() {

        return  "Advertentie:\n"+
                "Titel: " + titel + "\n" +
                "Categorie: " + categorie.toString() + "\n" +
                "Omschrijving: " + omschrijving + "\n" +
                "Prijs: " + prijs + "\n" +
                "Ondersteunde bezorgwijze: " + ondersteundeBezorgwijze +"\n";
    }
}
