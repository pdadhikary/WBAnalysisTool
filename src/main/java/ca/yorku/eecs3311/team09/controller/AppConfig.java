package ca.yorku.eecs3311.team09.controller;

import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.plots.Plot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class AppConfig {
    private static final String CONFIG_FILE = "/src/main/resources/config/appsettings.json";

    protected Gson reader;

    public AppConfig() {
        GsonBuilder builder = new GsonBuilder();
        Gson reader = builder.create();

        // read the file
    }

    public List<Country> getCountries() {
        return null;
    }

    public List<Plot> getPlots() {
        return null;

    }

    public List<AnalysisFactory> getAnalyses() {
        return null;
    }

    public Integer getMinDate() {
        return null;
    }

    public Integer getMaxDate() {
        return null;
    }

    public List<Integer> getDateExclusion(String analysisName) {
        return null;
    }

    public List<Country> getCountryExclusion(String analysisName) {
        return null;
    }
}
