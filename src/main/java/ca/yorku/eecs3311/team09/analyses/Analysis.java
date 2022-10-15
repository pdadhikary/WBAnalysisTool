package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.IAnalysisStrategy;
import ca.yorku.eecs3311.team09.enums.Country;

public interface Analysis {

    /**
     * Use a {@link IAnalysisStrategy IAnalysisStrategy} to perform analysis on a set of data.
     *
     * @param country  country for which to perform the analysis on.
     * @param fromDate start date of the data.
     * @param toDate   end date of the data.
     */
    void setData(Country country, int fromDate, int toDate);

    /**
     * Prints the result of the analysis to the standard output.
     */
    void showResult();

    /**
     * Returns the {@link IAnalysisStrategy IAnalysisStrategy} used to perform the calculation.
     *
     * @return analysis strategy
     */
    IAnalysisStrategy getStrategy();
}
