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
 * Government expenditure on education, total (% of GDP).
 */
public class AvgGovExp implements Analysis {
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
    public AvgGovExp() {
        this.title = "Average government expenditure on education (% of GDP)";
        this.indicators = Collections.singletonList(Indicator.GOV_EXPENDITURE_EDU_GDP);
    }

    @Override
    public void setData(Country country, int fromDate, int toDate) {
        DataFetcher fetcher = DataFactory.getFetcher(this.indicators, country, fromDate, toDate);
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
