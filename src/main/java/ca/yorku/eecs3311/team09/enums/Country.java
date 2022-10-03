package ca.yorku.eecs3311.team09.enums;

/**
 * Encapsulation of a Country and its country code.
 */
public enum Country {

    CANADA("Canada", "can"),
    USA("USA", "usa"),
    CHINA("China", "chn"),
    INDIA("India", "ind"),
    BRAZIL("Brazil", "br");
    private final String name;
    private final String code;

    private Country(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
