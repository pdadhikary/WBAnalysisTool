package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;

public interface AnalysisStrategy {
    public void performCalculation(DataFetcher fetcher) throws Exception;

    public void printData();
}