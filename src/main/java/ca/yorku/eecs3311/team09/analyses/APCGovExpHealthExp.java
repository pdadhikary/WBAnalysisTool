package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.AnnualPercentChangeStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.IAnalysisStrategy;
import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;
import java.util.List;

public class APCGovExpHealthExp implements Analysis {
    protected List<Indicator> indicators;
    protected String title;
    protected IAnalysisStrategy strategy;

    public void GovExpVSCurHealthExp() {
        this.indicators = Arrays.asList(
                Indicator.GOV_EXPENDITURE_EDU_GDP, Indicator.HEALTH_EXPENDITURE_GDP
        );
        ;
        this.title = "Education vs Health Expenditure";
    }

    @Override
    public void setData(Country country, int fromDate, int toDate) {
        // Initialize fetcher with indicators, country, from and to dates
        DataFetcher fetcher = DataFactory.getFetcher(
                this.indicators,
                country,
                fromDate,
                toDate
        );
        // Initialize a strategy
        this.strategy = new AnnualPercentChangeStrategy().performCalculation(fetcher);
    }

    @Override
    public void showResult() {
        System.out.println(this.title + ":");
        this.strategy.printData();
    }

    @Override
    public String toString() {
        return this.title;
    }
}
