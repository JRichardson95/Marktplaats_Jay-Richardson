package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Data @Builder @AllArgsConstructor @NoArgsConstructor @Embeddable
public class Postcode {

    private String postcode;

    @Transient
    @Size(max = 4)
    private int postcodeCijfers;

    @Transient
    @Size(max = 2)
    private String postcodeLetters;

    public void setPostcode() {
        this.postcode = postcodeCijfers + postcodeLetters;
    }

    @Override
    public String toString() {
        return postcodeCijfers + postcodeLetters;
    }
}
