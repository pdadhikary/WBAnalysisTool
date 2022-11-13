package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.visitors.AnalysisVisitor;
import ca.yorku.eecs3311.team09.analyses.visitors.PlotVisitor;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

/**
 * Analyzes the ratio of CO2 emissions (as metric tons per capita) to GDP per capita (current US$).
 */
public class CO2GDP extends RatioAnalysis {
    /**
     * Title of the Analysis class.
     */
    public static final String TITLE = "Ratio GDP in USD to CO2 emissions (per capita)";

    /**
     * Returns a new instance of this analysis.
     *
     * @param country  country
     * @param fromDate start date of the analysis
     * @param toDate   end date of the analysis
     */
    public CO2GDP(Country country, Integer fromDate, Integer toDate) {
        this.title = CO2GDP.TITLE;
        this.numerator = Indicator.CO2_EMISSIONS;
        this.denominator = Indicator.GDP_PER_CAPITA_USD;

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
