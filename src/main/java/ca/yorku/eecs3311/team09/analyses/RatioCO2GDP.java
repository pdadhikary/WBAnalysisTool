package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.IAnalysisStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.RatioStrategy;
import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;
import java.util.List;

/**
 * Analyzes the ratio of CO2 emissions (as metric tons per capita) to GDP per capita (current US$).
 */
public class RatioCO2GDP implements Analysis {
    /**
     * numerator indicator of this analysis
     */
    protected Indicator indicatorsNumerator;
    /**
     * numerator indicator of this analysis
     */
    protected Indicator indicatorsDenominator;
    /**
     * title of this analysis.
     */
    protected String title;
    /**
     * analysis strategy for this analysis.
     */
    protected IAnalysisStrategy strategy;

    /**
     * Returns a new instance of this analysis.
     */
    public RatioCO2GDP() {
        this.indicatorsNumerator = Indicator.CO2_EMISSIONS;
        this.indicatorsDenominator = Indicator.GDP_PER_CAPITA_USD;
        this.title = "Ratio GDP in USD to CO2 emissions (per capita)";
    }

    @Override
    public void setData(Country country, int fromDate, int toDate) {
        List<Indicator> indicators = Arrays.asList(
                Indicator.CO2_EMISSIONS, Indicator.GDP_PER_CAPITA_USD
        );

        // Initialize fetcher with indicators, country, from and to dates
        DataFetcher fetcher = DataFactory.getFetcher(
                indicators,
                country,
                fromDate,
                toDate
        );

        // Initialize a strategy
        this.strategy = new RatioStrategy()
                .setNumerator(indicatorsNumerator)
                .setDenominator(indicatorsDenominator)
                .performCalculation(fetcher);
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
