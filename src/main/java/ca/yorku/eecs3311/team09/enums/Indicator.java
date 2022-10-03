package ca.yorku.eecs3311.team09.enums;

/**
 * Encapsulation of an Indicator (type of data) and its World Bank API token.
 */
public enum Indicator {

    CO2_EMISSIONS(
            "CO2 emmissions (as metric tons per capita)",
            "EN.ATM.CO2E.PC"
    ),

    ENERGY_USE(
            "Energy use (kg of oil equivalent per capita)",
            "EG.USE.PCAP.KG.OE"
    ),

    AIR_POLLUTION_MEAN(
            "PM2.5 air pollution, mean annual exposure (micrograms per cubic meter)",
            "EN.ATM.PM25.MC.M3"
    ),

    FOREST_AREA(
            "Forest area (% of land area)",
            "AG.LND.FRST.ZS"
    ),

    GDP_PER_CAPITA_USD(
            "GDP per capita (current US$)",
            "NY.GDP.PCAP.CD"
    ),

    GOV_EXPENDITURE_EDU_GDP(
            "Government expenditure on education, total (% of GDP)",
            "SE.XPD.TOTL.GD.ZS"
    ),

    HEALTH_EXPENDITURE_USD(
            "Current health expenditure per capita (current US$)",
            "SH.XPD.CHEX.PC.CD"
    ),

    HOSPITAL_BEDS(
            "Hospital beds (per 1,000 people)",
            "SH.MED.BEDS.ZS"
    ),

    PROBLEM_ACCESSING_HC_WOMEN(
            "Problems in accessing health care (% of women): Q1 (lowest)",
            "SH.ACS.MONY.Q1.ZS"
    ),

    MORTALITY_RATE_INFANT(
            "Mortality rate, infant (per 1,000 live births)",
            "SP.DYN.IMRT.IN"
    ),

    HEALTH_EXPENDITURE_GDP(
            "Current health expenditure (% of GDP)",
            "SH.XPD.CHEX.GD.ZS"
    );

    private final String label;
    private final String indicator_token;

    private Indicator(String label, String indicator_token) {
        this.label = label;
        this.indicator_token = indicator_token;
    }

    public String getLabel() {
        return this.label;
    }

    public String getIndicator_token() {
        return this.indicator_token;
    }

    @Override
    public String toString() {
        return this.getLabel();
    }
}
