package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.AverageStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.IAnalysisStrategy;
import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Collections;
import java.util.List;

/**
 * Analyzes the average Forest area (% of land area).
 */
public class AvgForestArea implements Analysis {
    /**
     * title of this analysis.
     */
    protected String title;
    /**
     * analysis strategy for this analysis.
     */
    protected IAnalysisStrategy strategy;
    /**
     * list of indicators used for this analysis.
     */
    protected List<Indicator> indicators;

    /**
     * Returns a new instance of this analysis.
     */
    public AvgForestArea() {
        this.title = "Average Forest area (% of land area)";
        this.indicators = Collections.singletonList(Indicator.FOREST_AREA);
    }


    @Override
    public void setData(Country country, int startDate, int endDate) {
        DataFetcher fetcher = DataFactory.getFetcher(this.indicators, country, startDate, endDate);
        this.strategy = new AverageStrategy().performCalculation(fetcher);
    }

    @Override
    public void showResult() {
        System.out.println(this.title + ":");
        this.strategy.printData();
    }

    @Override
    public IAnalysisStrategy getStrategy() {
        return this.strategy;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
