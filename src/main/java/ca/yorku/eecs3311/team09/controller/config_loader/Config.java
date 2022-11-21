package ca.yorku.eecs3311.team09.controller.config_loader;

import ca.yorku.eecs3311.team09.enums.Country;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * A serializable class containing the app
 * configurations.
 */
public class Config {

    /**
     * selectable countries.
     */
    @SerializedName("countries")
    protected List<Country> countries;

    /**
     * minimum date range.
     */
    @SerializedName("dateMin")
    protected Integer dateMin;

    /**
     * maximum date range.
     */
    @SerializedName("dateMax")
    protected Integer dateMax;

    /**
     * selectable analyses and their configurations.
     */
    @SerializedName("analyses")
    protected List<AnalysisConfig> analyses;

    /**
     * selectable plot views and their configurations.
     */
    @SerializedName("plots")
    protected List<PlotConfig> plots;

    /**
     * Initialize a new Config object.
     */
    public Config() {
        this.countries = new ArrayList<>();
        this.analyses = new ArrayList<>();
        this.dateMin = 2010;
        this.dateMax = 2020;
    }

    /**
     * Get a list of selectable countries.
     *
     * @return list of countries
     */
    public List<Country> getCountries() {
        return new ArrayList<>(countries);
    }

    /**
     * Get the minimum date range.
     *
     * @return min date range
     */
    public Integer getDateMin() {
        return dateMin;
    }

    /**
     * Get the maximum date range.
     *
     * @return max date range
     */
    public Integer getDateMax() {
        return dateMax;
    }

    /**
     * Return the list of analysis configurations.
     *
     * @return list of analysis configurations
     */
    public List<AnalysisConfig> getAnalyses() {
        return new ArrayList<>(analyses);
    }

    /**
     * Return the list of plot configurations.
     *
     * @return list of plot configurations
     */
    public List<PlotConfig> getPlots() {
        return new ArrayList<>(plots);
    }
}
