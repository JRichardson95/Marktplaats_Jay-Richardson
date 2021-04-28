package model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Advertentie implements Identifiable<Long>{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titel;
    private String omschrijving;


}
