package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Collections;

/**
 * Government expenditure on education, total (% of GDP).
 */
public class GovernmentExpenditure extends AverageAnalysis {
    /**
     * Title of the Analysis class.
     */
    public static final String TITLE = "Average government expenditure on education (% of GDP)";

    /**
     * Returns a new instance of this analysis.
     */
    public GovernmentExpenditure(Country country, Integer fromDate, Integer toDate) {
        this.title = GovernmentExpenditure.TITLE;
        this.indicators = Collections.singletonList(Indicator.GOV_EXPENDITURE_EDU_GDP);

        this.country = country;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public void accept(AnalysisVisitor visitor) {
        visitor.visitAnalysis(this);
    }
}
