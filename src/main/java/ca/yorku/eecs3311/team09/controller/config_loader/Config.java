package ca.yorku.eecs3311.team09.controller.config_loader;

import ca.yorku.eecs3311.team09.enums.Country;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Config {
    @SerializedName("countries")
    protected List<Country> countries;
    @SerializedName("dateMin")
    protected Integer dateMin;
    @SerializedName("dateMax")
    protected Integer dateMax;
    @SerializedName("analyses")
    protected List<AnalysisConfig> analyses;
    @SerializedName("plots")
    protected List<PlotConfig> plots;

    public Config() {
        this.countries = new ArrayList<>();
        this.analyses = new ArrayList<>();
        this.dateMin = 2010;
        this.dateMax = 2020;
    }

    public List<Country> getCountries() {
        return new ArrayList<>(countries);
    }

    public Integer getDateMin() {
        return dateMin;
    }

    public Integer getDateMax() {
        return dateMax;
    }

    public List<AnalysisConfig> getAnalyses() {
        return new ArrayList<>(analyses);
    }

    public List<PlotConfig> getPlots() {
        return new ArrayList<>(plots);
    }
}
