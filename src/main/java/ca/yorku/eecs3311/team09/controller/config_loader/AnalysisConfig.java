package ca.yorku.eecs3311.team09.controller.config_loader;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AnalysisConfig {

    @SerializedName("name")
    protected String name;
    @SerializedName("code")
    protected String code;
    @SerializedName("excludedDates")
    protected List<Integer> excludedDates;
    @SerializedName("excludedCountries")
    protected List<String> excludedCountries;

    public AnalysisConfig() {
        this.excludedDates = new ArrayList<>();
        this.excludedCountries = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<Integer> getExcludedDates() {
        return new ArrayList<>(excludedDates);
    }

    public List<String> getExcludedCountries() {
        return new ArrayList<>(excludedCountries);
    }
}
