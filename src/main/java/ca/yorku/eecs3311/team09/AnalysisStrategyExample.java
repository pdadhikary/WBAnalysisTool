package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.*;
import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AnalysisStrategyExample {
    static Country country = Country.USA;
    static int fromDate = 2010;
    static int toDate = 2015;

    public static void main(String[] args) {
        test_AnnualPercentageChange();
        test_Average();
        test_Ratio();
        test_Lazy();
    }

    public static void test_AnnualPercentageChange() {
        // Date we are interested in
        List<Indicator> indicators = Arrays.asList(
                Indicator.CO2_EMISSIONS, Indicator.ENERGY_USE, Indicator.AIR_POLLUTION_MEAN
        );

        // Initialize fetcher with indicators, country, from and to dates
        DataFetcher fetcher = DataFactory.getFetcher(
                indicators,
                country,
                fromDate,
                toDate
        );

        // Initialize a strategy
        IAnalysisStrategy strategy = new AnnualPercentChangeStrategy().performCalculation(fetcher);

        // print calculation results
        System.out.println("Annual Percentage Change:");
        System.out.println("CO2 vs Energy Use & Air Pollution Annual Percentage Change");
        strategy.printData();
        System.out.println("---------------------------------------------------------------------------------------\n");
    }

    public static void test_Average() {
        // Date we are interested in
        List<Indicator> indicators = Collections.singletonList(
                Indicator.FOREST_AREA
        );

        // Initialize fetcher with indicators, country, from and to dates
        DataFetcher fetcher = DataFactory.getFetcher(
                indicators,
                country,
                fromDate,
                toDate
        );

        // Initialize a strategy
        IAnalysisStrategy strategy = new AverageStrategy().performCalculation(fetcher);

        // print calculation results
        System.out.println("Average:");
        System.out.println("Average Forest Area (% land area)");
        strategy.printData();
        System.out.println("---------------------------------------------------------------------------------------\n");
    }

    public static void test_Ratio() {
// Date we are interested in
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
        IAnalysisStrategy strategy = new RatioStrategy()
                .setNumerator(Indicator.CO2_EMISSIONS)
                .setDenominator(Indicator.GDP_PER_CAPITA_USD)
                .performCalculation(fetcher);

        // print calculation results
        System.out.println("Ratio:");
        System.out.println("Ratio CO2 Emissions to GDP per capita");
        strategy.printData();
        System.out.println("---------------------------------------------------------------------------------------\n");
    }

    public static void test_Lazy() {
        // Date we are interested in
        List<Indicator> indicators = Arrays.asList(
                Indicator.PROBLEM_ACCESSING_HC_WOMEN,
                Indicator.MORTALITY_RATE_INFANT
        );

        // Initialize fetcher with indicators, country, from and to dates
        DataFetcher fetcher = DataFactory.getFetcher(
                indicators,
                country,
                fromDate,
                toDate
        );

        // Initialize a strategy
        IAnalysisStrategy strategy = new LazyStrategy().performCalculation(fetcher);

        // print calculation results
        System.out.println("Lazy:");
        System.out.println("Problem accessing healthcare (Women) vs Infant mortality rate");
        strategy.printData();
        System.out.println("---------------------------------------------------------------------------------------\n");
    }
}
