package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.visitors.AnalysisVisitor;
import ca.yorku.eecs3311.team09.analyses.visitors.PlotVisitor;
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
    public static final String TITLE = "Average education expenditure";

    /**
     * Returns a new instance of this analysis.
     *
     * @param country  country
     * @param fromDate start date of the analysis
     * @param toDate   end date of the analysis
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

    @Override
    public void accept(PlotVisitor visitor) {
        visitor.plotAnalysis(this);
    }
}
