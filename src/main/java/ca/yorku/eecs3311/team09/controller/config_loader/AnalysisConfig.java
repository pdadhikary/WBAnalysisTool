package ca.yorku.eecs3311.team09.controller.config_loader;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Analysis configurations.
 */
public class AnalysisConfig {

    /**
     * name of the analysis.
     */
    @SerializedName("name")
    protected String name;

    /**
     * code of the analysis.
     */
    @SerializedName("code")
    protected String code;

    /**
     * dates for which this analysis is restricted.
     */
    @SerializedName("excludedDates")
    protected List<Integer> excludedDates;

    /**
     * countries for which this analysis is restricted.
     */
    @SerializedName("excludedCountries")
    protected List<String> excludedCountries;

    /**
     * Initializes a Config object.
     */
    public AnalysisConfig() {
        this.excludedDates = new ArrayList<>();
        this.excludedCountries = new ArrayList<>();
    }

    /**
     * Get the analysis name.
     *
     * @return analysis name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the analysis code.
     *
     * @return analysis code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Get the list of dates for which this analysis is restricted.
     *
     * @return list of dates for which this analysis is restricted
     */
    public List<Integer> getExcludedDates() {
        return new ArrayList<>(excludedDates);
    }

    /**
     * Get the list of countries for which this analysis is restricted.
     *
     * @return countries for which this analysis is restricted.
     */
    public List<String> getExcludedCountries() {
        return new ArrayList<>(excludedCountries);
    }
}
