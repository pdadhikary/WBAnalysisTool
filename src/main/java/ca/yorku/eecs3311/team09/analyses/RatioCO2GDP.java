package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.IAnalysisStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.RatioStrategy;
import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;
import java.util.List;

public class RatioCO2GDP implements Analysis {
    protected Indicator indicatorsNumerator;
    protected Indicator indicatorsDenominator;
    protected DataFetcher data;
    protected String title;
    protected IAnalysisStrategy strategy;


    public RatioCO2GDP() {
        this.indicatorsNumerator = Indicator.CO2_EMISSIONS;
        this.indicatorsDenominator = Indicator.GDP_PER_CAPITA_USD;
        this.title = "Ratio CO2 Emissions to GDP per capita";
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
        this.data = fetcher;

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
    public String toString() {
        return this.title;
    }
}
