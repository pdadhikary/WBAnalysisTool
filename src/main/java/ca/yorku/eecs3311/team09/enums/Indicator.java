package ca.yorku.eecs3311.team09.enums;

/**
 * Encapsulation of an Indicator (type of data) and its World Bank API token.
 */
public enum Indicator {

    CO2_EMISSIONS(
            "CO2 emissions t/capita)",
            "EN.ATM.CO2E.PC"
    ),

    ENERGY_USE(
            "Energy use kg/capita",
            "EG.USE.PCAP.KG.OE"
    ),

    AIR_POLLUTION_MEAN(
            "Air pollution (ig/m3)",
            "EN.ATM.PM25.MC.M3"
    ),

    FOREST_AREA(
            "Forest area (% land area)",
            "AG.LND.FRST.ZS"
    ),

    GDP_PER_CAPITA_USD(
            "GDP per capita (US$)",
            "NY.GDP.PCAP.CD"
    ),

    GOV_EXPENDITURE_EDU_GDP(
            "Government education expenditure (% of GDP)",
            "SE.XPD.TOTL.GD.ZS"
    ),

    HEALTH_EXPENDITURE_USD(
            "Health expenditure/capita (US$)",
            "SH.XPD.CHEX.PC.CD"
    ),

    HOSPITAL_BEDS(
            "Hospital beds/1000)",
            "SH.MED.BEDS.ZS"
    ),

    PROBLEM_ACCESSING_HC_WOMEN(
            "Problems accessing health care (% of women)",
            "SH.ACS.MONY.Q1.ZS"
    ),

    MORTALITY_RATE_INFANT(
            "Infant Mortality rate/1000",
            "SP.DYN.IMRT.IN"
    ),

    HEALTH_EXPENDITURE_GDP(
            "Health expenditure (% of GDP)",
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
