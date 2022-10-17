package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;

/**
 * Analyzes the annual percent change of Government expenditure on education, total (% of GDP) vs
 * Current health expenditure (% of GDP).
 */
public class GovEducationHealthExpenditure extends AnnualPercentChangeAnalysis {
    /**
     * Title of the Analysis class.
     */
    public static final String TITLE = "Education vs health expenditure";

    public GovEducationHealthExpenditure(Country country, Integer fromDate, Integer toDate) {
        this.title = GovEducationHealthExpenditure.TITLE;
        this.indicators = Arrays.asList(
                Indicator.GOV_EXPENDITURE_EDU_GDP, Indicator.HEALTH_EXPENDITURE_GDP
        );

        this.country = country;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public void accept(AnalysisVisitor visitor) {
        visitor.visitAnalysis(this);
    }
}
