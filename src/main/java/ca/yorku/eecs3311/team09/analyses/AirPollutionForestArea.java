package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.visitors.AnalysisVisitor;
import ca.yorku.eecs3311.team09.analyses.visitors.PlotVisitor;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;

/**
 * Analyzes the annual percent change of
 * PM2.5 air pollution, mean annual exposure (micrograms per cubic meter) vs Forest area (% of land area).
 */
public class AirPollutionForestArea extends AnnualPercentChangeAnalysis {
    /**
     * Title of the Analysis class.
     */
    public static final String TITLE = "Air pollution vs forest area % change";

    /**
     * Returns a new instance of this analysis.
     *
     * @param country  country
     * @param fromDate start date of analysis
     * @param toDate   end date of analysis
     */
    public AirPollutionForestArea(Country country, Integer fromDate, Integer toDate) {
        this.title = AirPollutionForestArea.TITLE;
        this.indicators = Arrays.asList(Indicator.AIR_POLLUTION_MEAN, Indicator.FOREST_AREA);

        this.country = country;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public void accept(AnalysisVisitor visitor) {
        visitor.visitAnalysis(this);
    }

    @Override
    public void accept(PlotVisitor visitor) {
        visitor.plotAnalysis(this);
    }
}
