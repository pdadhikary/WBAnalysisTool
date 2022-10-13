package ca.yorku.eecs3311.team09.analyses.analysis_strategy;

import ca.yorku.eecs3311.team09.analyses.data_manipulation.SeriesOperation;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;

import java.util.List;

public interface IAnalysisStrategy {
    IAnalysisStrategy performCalculation(DataFetcher fetcher);

    /**
     * Applies the manipulators before performing calculations
     */
    IAnalysisStrategy performCalculation(DataFetcher fetcher, List<SeriesOperation> operations);

    void printData();
}