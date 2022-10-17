package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;

/**
 * Analyzes Problems in accessing health care (% of women): Q1 (lowest) vs
 * Mortality rate, infant (per 1,000 live births).
 */
public class HealthCareMortality extends LazyAnalysis {
    /**
     * Title of the Analysis class.
     */
    public static final String TITLE = "Problems accessing health care (Women) Vs. mortality rate";

    /**
     * Returns a new instance of this analysis.
     *
     * @param country  country
     * @param fromDate start date of the analysis
     * @param toDate   end date of the analysis
     */
    public HealthCareMortality(Country country, Integer fromDate, Integer toDate) {
        this.title = HealthCareMortality.TITLE;
        this.indicators = Arrays.asList(Indicator.PROBLEM_ACCESSING_HC_WOMEN, Indicator.MORTALITY_RATE_INFANT);

        this.country = country;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public void accept(AnalysisVisitor visitor) {
        visitor.visitAnalysis(this);
    }
}
