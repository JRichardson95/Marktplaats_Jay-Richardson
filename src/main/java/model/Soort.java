package model;

public enum Soort {
    DIENST("Dienst"),
    PRODUCT("Product"),
    UNKNOWN("Unknown");
    private final String soort;

    Soort(String soort) {
        this.soort = soort;
    }

    public String getSoort() {
        return soort;
    }
}
