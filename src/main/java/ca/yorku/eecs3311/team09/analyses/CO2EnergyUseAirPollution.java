package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;

/**
 * Analyzes the annual percent change of CO2 emissions (as metric tons per capita)
 * vs Energy use (kg of oil equivalent per capita) and PM2.5 air pollution, mean
 * annual exposure (micrograms per cubic meter).
 */
public class CO2EnergyUseAirPollution extends AnnualPercentChangeAnalysis {
    /**
     * Title of the Analysis class.
     */
    public static final String TITLE = "CO2 emissions vs energy use & air pollution";

    /**
     * Returns a new instance of this analysis.
     *
     * @param country  country
     * @param fromDate start date of the analysis
     * @param toDate   end date of the analysis
     */
    public CO2EnergyUseAirPollution(Country country, Integer fromDate, Integer toDate) {
        this.title = CO2EnergyUseAirPollution.TITLE;
        this.indicators = Arrays.asList(Indicator.CO2_EMISSIONS, Indicator.ENERGY_USE, Indicator.AIR_POLLUTION_MEAN);

        this.country = country;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public void accept(AnalysisVisitor visitor) {
        visitor.visitAnalysis(this);
    }
}
