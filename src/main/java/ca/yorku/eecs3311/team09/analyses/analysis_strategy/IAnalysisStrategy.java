package ca.yorku.eecs3311.team09.analyses.analysis_strategy;

import ca.yorku.eecs3311.team09.analyses.data_manipulation.SeriesOperation;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;

import java.util.List;

/**
 * Holds the strategy to perform an analysis.
 */
public interface IAnalysisStrategy {
    /**
     * performs and stores the result of the analysis strategy.
     *
     * @param fetcher {@link DataFetcher DataFetcher}
     * @return this analysis strategy object
     */
    IAnalysisStrategy performCalculation(DataFetcher fetcher);

    /**
     * Applies the series operations to the data before
     * performing and storing the result of the analysis strategy.
     *
     * @param fetcher    {@link DataFetcher DataFetcher}
     * @param operations list of operations
     * @return this analysis strategy object
     */
    IAnalysisStrategy performCalculation(DataFetcher fetcher, List<SeriesOperation> operations);

    /**
     * Prints the result of the analysis.
     */
    void printData();
}