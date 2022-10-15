package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.IAnalysisStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.LazyStrategy;
import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;
import java.util.List;

public class HealthCareMortality implements Analysis {
    protected List<Indicator> indicators;
    protected String title;
    protected IAnalysisStrategy strategy;


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
