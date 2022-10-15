package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.IAnalysisStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.LazyStrategy;
import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;
import java.util.List;

/**
 * Analyzes Problems in accessing health care (% of women): Q1 (lowest) vs
 * Mortality rate, infant (per 1,000 live births).
 */
public class HealthCareMortality implements Analysis {
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
    public HealthCareMortality() {
        this.indicators = Arrays.asList(Indicator.PROBLEM_ACCESSING_HC_WOMEN, Indicator.MORTALITY_RATE_INFANT);
        this.title = "Problems accessing health care (Women) Vs. mortality rate";
    }


    @Override
    public void setData(Country country, int fromDate, int toDate) {
        DataFetcher fetcher = DataFactory.getFetcher(this.indicators, country, fromDate, toDate);
        this.strategy = new LazyStrategy().performCalculation(fetcher);
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

    public String toString() {
        return title;
    }
}
