package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Adres;

import model.Bezorgwijze;
import model.Status;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Data @AllArgsConstructor
@NoArgsConstructor
@Entity
public class Gebruiker implements Identifiable<Long> {
    @Id @GeneratedValue
    private Long id;
    
    private String voornaam;
    private String achternaam;

    @Email
    private String email;

    @Embedded
    private Adres adres;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ElementCollection
    private Set<Bezorgwijze> bezorgwijzeSet;
}
