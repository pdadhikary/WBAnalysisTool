package ca.yorku.eecs3311.team09.analyses.analysis_strategy;

import ca.yorku.eecs3311.team09.analyses.data_manipulation.SeriesOperation;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.List;
import java.util.Map;

public abstract class AnalysisStrategy implements IAnalysisStrategy {
    protected DataFetcher fetcher;
    protected Map<Indicator, Map<Integer, Double>> data;

    @Override
    public AnalysisStrategy performCalculation(DataFetcher fetcher) {
        this.fetcher = fetcher;
        this.data = fetcher.getData();

        this.calculate();
        return this;
    }

    @Override
    public AnalysisStrategy performCalculation(DataFetcher fetcher, List<SeriesOperation> operations) {
        this.fetcher = fetcher;
        this.data = fetcher.getData();

        for (SeriesOperation operation : operations) {
            operation.calculate(this.data);
        }

        this.calculate();
        return this;
    }

    @Override
    public abstract void printData();

    protected abstract void calculate();
}
