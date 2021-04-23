package model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data @NoArgsConstructor
@Embeddable
public class Adres {

    private String straat;

    private Integer huisnummer;

    @Embedded
    private Postcode postcode;

    private String stad;

    private String provincie;
}
