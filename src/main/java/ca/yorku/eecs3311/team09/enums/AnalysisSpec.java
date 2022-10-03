package ca.yorku.eecs3311.team09.enums;

import java.util.Arrays;

/**
 * Encapsulation of an Analysis and its specification, i.e, what indicators are needed to complete
 * the analysis.
 */
public enum AnalysisSpec {
    APC_CO2_ENG_APOLL(
            "Annual percentage change in CO2 emissions vs energy use & air pollution",
            new Indicator[]{Indicator.CO2_EMISSIONS, Indicator.ENERGY_USE, Indicator.AIR_POLLUTION_MEAN}
    ),

    APC_APOLL_FAREA(
            "Annual percentage change in air pollution vs forest area",
            new Indicator[]{Indicator.AIR_POLLUTION_MEAN, Indicator.FOREST_AREA}
    ),

    RATIO_CO2_GDP(
            "Ratio of CO2 emissions to GDP per capita",
            new Indicator[]{Indicator.CO2_EMISSIONS, Indicator.GDP_PER_CAPITA_USD}
    ),

    AVG_FAREA(
            "Average of forest area (% of land area)",
            new Indicator[]{Indicator.FOREST_AREA}
    ),

    AVG_GOVEXP_EDU_GDP(
            "Average of government expenditure on education (% of GDP)",
            new Indicator[]{Indicator.GOV_EXPENDITURE_EDU_GDP}
    ),

    RATIO_CURRHEXP_HOSBEDS(
            "Ratio of current health expenditure to hospital beds (per 1,000 people)",
            new Indicator[]{Indicator.HEALTH_EXPENDITURE_GDP, Indicator.HOSPITAL_BEDS}
    ),

    APC_WOMEN_HCPROBS_MORTALITY_INF(
            "Annual percentage change in problem accessing health care (% women) vs infant mortality (per 1,000 live births)",
            new Indicator[]{Indicator.PROBLEM_ACCESSING_HC_WOMEN, Indicator.MORTALITY_RATE_INFANT}
    ),

    APC_GOVEXP_EDU_GDP_CURRHEXP_GDP(
            "Annual percentage change in government expenditure on education vs current health expenditure (% of GDP)",
            new Indicator[]{Indicator.GOV_EXPENDITURE_EDU_GDP, Indicator.HEALTH_EXPENDITURE_GDP}
    );


    private final String name;
    private final Indicator[] indicators;

    private AnalysisSpec(String name, Indicator[] indicators) {
        this.name = name;
        this.indicators = indicators;
    }

    public String getName() {
        return this.name;
    }

    public Indicator[] getIndicators() {
        return Arrays.copyOf(indicators, indicators.length);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
