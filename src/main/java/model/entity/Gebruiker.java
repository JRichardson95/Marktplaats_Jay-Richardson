package model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Adres;

import model.Bezorgwijze;
import model.Status;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name ="Gebruiker.findAll", query = "Select g from Gebruiker g")
})
public class Gebruiker implements Identifiable<Long> {
    @Id @GeneratedValue
    private Long id;
    
    private String voornaam;
    private String achternaam;

    @Email
    private String email;

    private String password;

    private boolean akkoordMetVoorwaarde;

    @Embedded
    private Adres adres;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ElementCollection
    private Set<Bezorgwijze> bezorgwijzeSet;

    public String getNaam() {
        return voornaam +  " " + achternaam;
    }

}
