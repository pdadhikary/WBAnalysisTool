package ca.yorku.eecs3311.team09.controller.config_loader;

import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.exceptions.SettingsFormatException;
import ca.yorku.eecs3311.team09.plots.factory.PlotFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads the app configurations from a config json file.
 */
public class ConfigLoader {

    /**
     * default app config file.
     */
    public static final String CONFIG_FILE = "src/main/resources/config/appsettings.json";

    /**
     * config object
     */
    protected Config config;

    /**
     * Initializes the config object with the
     * default CONFIG_FILE.
     */
    public ConfigLoader() {
        this(ConfigLoader.CONFIG_FILE);
    }

    /**
     * Initializes the config object with the
     * provided config file.
     *
     * @param path path to config file
     */
    public ConfigLoader(String path) {
        File file = new File(path);
        Config loadedConfig = ConfigLoader.loadConfig(file);

        if (loadedConfig != null) {
            this.config = loadedConfig;
        } else {
            this.config = new Config();
            throw new SettingsFormatException("Could not read from settings file...");
        }
    }

    /**
     * Get the list of selectable countries.
     *
     * @return list of countries
     */
    public List<Country> getCountries() {
        return this.config.getCountries();
    }

    /**
     * Get the list of factories for selectable plots.
     *
     * @return list of plot factories
     */
    public List<PlotFactory> getPlots() {
        List<PlotFactory> plots = new ArrayList<>();

        for (PlotConfig plotConfig : this.config.getPlots()) {
            plots.add(
                    new PlotFactory(plotConfig.getType(), plotConfig.getName())
            );
        }

        return plots;
    }

    /**
     * Get the list of factories for selectable analyses.
     *
     * @return list of analysis factories
     */
    public List<AnalysisFactory> getAnalyses() {
        List<AnalysisFactory> analyses = new ArrayList<>();

        for (AnalysisConfig analysisConfig : this.config.getAnalyses()) {
            analyses.add(
                    new AnalysisFactory(analysisConfig.getCode(), analysisConfig.getName())
            );
        }

        return analyses;
    }

    /**
     * Get the minimum date range.
     *
     * @return min date range
     */
    public Integer getMinDate() {
        return this.config.getDateMin();
    }

    /**
     * Get the maximum date range.
     *
     * @return max date range
     */
    public Integer getMaxDate() {
        return this.config.getDateMax();
    }

    /**
     * Get the list of dates excluded for the given analysis code. Returns
     * an empty list if no restriction is specified.
     *
     * @param analysisCode analysis code
     * @return list of dates excluded for the given analysis code
     */
    public List<Integer> getDateExclusion(String analysisCode) {
        List<Integer> excludedDates = new ArrayList<>();
        for (AnalysisConfig analysisConfig : this.config.getAnalyses()) {
            if (analysisCode.equals(analysisConfig.getCode())) {
                excludedDates = analysisConfig.getExcludedDates();
            }
        }
        return excludedDates;
    }

    /**
     * Get the list of countries excluded for the given analysis code. Returns
     * an empty list if no restriction is specified.
     *
     * @param analysisCode analysis code
     * @return list of countries excluded for the given analysis code
     */
    public List<String> getCountryCodeExclusion(String analysisCode) {
        List<String> excludedCountryCodes = new ArrayList<>();

        for (AnalysisConfig analysisConfig : this.config.getAnalyses()) {
            if (analysisCode.equals(analysisConfig.getCode())) {
                excludedCountryCodes = analysisConfig.getExcludedCountries();
            }
        }
        return excludedCountryCodes;
    }

    /**
     * Read and load the given config file.
     *
     * @param file config file
     * @return a new Config object
     */
    private static Config loadConfig(File file) {
        Config configLoaded;
        try {
            InputStream stream = Files.newInputStream(file.toPath());
            InputStreamReader reader = new InputStreamReader(stream);

            Gson gson = new GsonBuilder().setLenient().create();
            configLoaded = gson.fromJson(reader, Config.class);

            reader.close();
        } catch (IOException exception) {
            configLoaded = null;
        }

        return configLoaded;
    }
}