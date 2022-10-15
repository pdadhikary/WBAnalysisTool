package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.AnnualPercentChangeStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.IAnalysisStrategy;
import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;
import java.util.List;

/**
 * Analyzes the annual percent change of Government expenditure on education, total (% of GDP) vs
 * Current health expenditure (% of GDP).
 */
public class APCGovExpHealthExp implements Analysis {
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
    public APCGovExpHealthExp() {
        this.indicators = Arrays.asList(
                Indicator.GOV_EXPENDITURE_EDU_GDP, Indicator.HEALTH_EXPENDITURE_GDP
        );
        this.title = "Education vs health expenditure";
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
    public IAnalysisStrategy getStrategy() {
        return this.strategy;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
