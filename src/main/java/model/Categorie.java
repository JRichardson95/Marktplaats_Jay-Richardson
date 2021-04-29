package model;

public enum Categorie {
    KLUSSEN("Klussen", Soort.DIENST),
    SCHILDEREN("Schilderen", Soort.DIENST),
    COMPUTERS("Computers", Soort.PRODUCT),
    MEUBELS("Meubels", Soort.PRODUCT);


    private final String categorie;
    private final Soort soort;

     Categorie(String categorie, Soort soort) {
        this.categorie = categorie;
        this.soort = soort;
    }

    @Override
    public String toString() {
        return categorie + " - " + soort.getSoort();
    }
}
