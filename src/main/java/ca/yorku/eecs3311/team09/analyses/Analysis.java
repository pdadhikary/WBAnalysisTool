package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.enums.Country;

public interface Analysis {

    /**
     * Uses the DataFactory to create a custom data fetcher,
     * passes the data fetcher to AnalysisStrategy.
     */
    void setData(Country country, int fromDate, int toDate);

    /**
     * Calls the print() method on the analysis strategy.
     */
    void showResult();
}
