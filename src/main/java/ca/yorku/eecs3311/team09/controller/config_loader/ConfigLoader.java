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

public class ConfigLoader {
    private static final String CONFIG_FILE = "src/main/resources/config/appsettings.json";

    protected Config config;

    public ConfigLoader() {
        this(ConfigLoader.CONFIG_FILE);
    }

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

    public List<Country> getCountries() {
        return this.config.getCountries();
    }

    public List<PlotFactory> getPlots() {
        List<PlotFactory> plots = new ArrayList<>();

        for (PlotConfig plotConfig : this.config.getPlots()) {
            plots.add(
                    new PlotFactory(plotConfig.getType(), plotConfig.getName())
            );
        }

        return plots;
    }

    public List<AnalysisFactory> getAnalyses() {
        List<AnalysisFactory> analyses = new ArrayList<>();

        for (AnalysisConfig analysisConfig : this.config.getAnalyses()) {
            analyses.add(
                    new AnalysisFactory(analysisConfig.getCode(), analysisConfig.getName())
            );
        }

        return analyses;
    }

    public Integer getMinDate() {
        return this.config.getDateMin();
    }

    public Integer getMaxDate() {
        return this.config.getDateMax();
    }

    public List<Integer> getDateExclusion(String analysisCode) {
        List<Integer> excludedDates = new ArrayList<>();
        for (AnalysisConfig analysisConfig : this.config.getAnalyses()) {
            if (analysisCode.equals(analysisConfig.getCode())) {
                excludedDates = analysisConfig.getExcludedDates();
            }
        }
        return excludedDates;
    }

    public List<String> getCountryCodeExclusion(String analysisCode) {
        List<String> excludedCountryCodes = new ArrayList<>();

        for (AnalysisConfig analysisConfig : this.config.getAnalyses()) {
            if (analysisCode.equals(analysisConfig.getCode())) {
                excludedCountryCodes = analysisConfig.getExcludedCountries();
            }
        }
        return excludedCountryCodes;
    }

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