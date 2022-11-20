package ca.yorku.eecs3311.team09.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Encapsulation of a Country and its country code.
 */
public class Country {

    public static final Country CANADA = new Country("Canada", "can");
    public static final Country USA = new Country("USA", "usa");
    public static final Country CHINA = new Country("China", "chn");
    public static final Country INDIA = new Country("India", "ind");
    public static final Country BRAZIL = new Country("Brazil", "br");

    @SerializedName("name")
    private final String name;
    @SerializedName("code")
    private final String code;

    public Country(String name, String code) {
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
