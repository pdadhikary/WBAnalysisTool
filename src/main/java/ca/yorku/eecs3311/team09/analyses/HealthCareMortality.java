package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.IAnalysisStrategy;
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


    public void Hospital2series() {
        this.indicators = Arrays.asList(Indicator.PROBLEM_ACCESSING_HC_WOMEN, Indicator.MORTALITY_RATE_INFANT);
        this.title = "Problems accessing health care (Women) Vs. Mortality Rate";

    }


    @Override
    public void setData(Country country, int startDate, int fromDate) {
        DataFetcher fetcher = DataFactory.getFetcher(this.indicators, country, fromDate, startDate);
        this.strategy.performCalculation(fetcher);
    }

    @Override
    public void showResult() {
        this.strategy.printData();
    }

    public String toString() {
        return title;
    }
}
