package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.visitors.AnalysisVisitor;
import ca.yorku.eecs3311.team09.analyses.visitors.PlotVisitor;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Collections;

/**
 * Analyzes the average Forest area (% of land area).
 */
public class ForestArea extends AverageAnalysis {
    /**
     * Title of the Analysis class.
     */
    public static final String TITLE = "Forest area average";

    /**
     * Returns a new instance of this analysis.
     *
     * @param country  country
     * @param fromDate start date of the analysis
     * @param toDate   end date of the analysis
     */
    public ForestArea(Country country, Integer fromDate, Integer toDate) {
        this.title = ForestArea.TITLE;
        this.indicators = Collections.singletonList(Indicator.FOREST_AREA);

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
