package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.AnnualPercentChangeStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.AverageStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.IAnalysisStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.RatioStrategy;
import ca.yorku.eecs3311.team09.analyses.data_manipulation.Add;
import ca.yorku.eecs3311.team09.analyses.data_manipulation.Multiply;
import ca.yorku.eecs3311.team09.analyses.data_manipulation.SeriesOperation;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;
import ca.yorku.eecs3311.team09.helpers.AnalysisAdapter;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class AnalysisStrategyTest {
    private static final AnalysisAdapter ADAPTER = new AnalysisAdapter();

    /**
     * Test ID: AnalysisStrategyTest01
     * <p>
     * Category: Tests the annual percent change strategy.
     * <p>
     * Requirements Coverage: UC3-Analysis-Strategy-APC
     * <p>
     * Initial Condition: DataFetcher returns the data to perform analysis on.
     * <p>
     * Steps required for this test:
     * <p>
     * - 1. Create a fetcher containing the indicator data.
     * <p>
     * - 2. Pass the data fetcher to the IAnalysisStrategy.performCalculation() method.
     * <p>
     * Expected Outcome: Expected result matches the actual result.
     */
    @Test
    public void AnalysisStrategyTest01() {
        // Calculate expected value
        Map<Integer, Double> row1 = new TreeMap<Integer, Double>() {{
            put(2002, percentChange(3.4, 4.1));
            put(2003, percentChange(4.1, 6.2));
        }};
        Map<Integer, Double> row2 = new TreeMap<Integer, Double>() {{
            put(2002, percentChange(1.4, 2.1));
            put(2003, percentChange(2.1, 3.5));
        }};
        Map<Integer, Double> row3 = new TreeMap<Integer, Double>() {{
            put(2002, percentChange(4.2, 3.7));
            put(2003, percentChange(3.7, 2.9));
        }};
        Map<Indicator, Map<Integer, Double>> map = new HashMap<Indicator, Map<Integer, Double>>() {{
            put(Indicator.FOREST_AREA, row1);
            put(Indicator.CO2_EMISSIONS, row2);
            put(Indicator.AIR_POLLUTION_MEAN, row3);
        }};
        String expected = map.toString();

        // Run strategy algorithm
        IAnalysisStrategy strategy = new AnnualPercentChangeStrategy()
                .performCalculation(new DummyDataFetcherThreeSeries());
        String actual = ADAPTER.convert((AnnualPercentChangeStrategy) strategy);

        assertEquals("The analysis result was incorrect", expected, actual);
    }

    /**
     * Test ID: AnalysisStrategyTest02
     * <p>
     * Category: Tests the ratio strategy.
     * <p>
     * Requirement Coverage: UC3-Analysis-Strategy-Ratio.
     * <p>
     * Initial Condition: DataFetcher returns the data to perform analysis on.
     * <p>
     * Steps required for this test:
     * <p>
     * - 1. Create a fetcher containing the indicator data.
     * <p>
     * - 2. Pass the data fetcher to the IAnalysisStrategy.performCalculation() method.
     * <p>
     * Expected Outcome: Expected result matches the actual result.
     */
    @Test
    public void AnalysisStrategyTest02() {
        // Calculate expected value
        Map<Integer, Double> map = new TreeMap<Integer, Double>() {{
            put(2001, ratio(2.4, 2.4));
            put(2002, ratio(1.1, 5.1));
            put(2003, ratio(6.2, 3.5));
        }};
        String expected = map.toString();

        // Run strategy algorithm
        IAnalysisStrategy strategy = new RatioStrategy()
                .setNumerator(Indicator.HEALTH_EXPENDITURE_GDP)
                .setDenominator(Indicator.AIR_POLLUTION_MEAN)
                .performCalculation(new DummyDataFetcherTwoSeries());
        String actual = ADAPTER.convert((RatioStrategy) strategy);

        assertEquals("The analysis result was incorrect", expected, actual);
    }

    /**
     * Test ID: AnalysisStrategyTest03
     * <p>
     * Category: Tests the series operations on an analysis strategy.
     * <p>
     * Requirement Coverage: UC3-Analysis-Strategy-SeriesOperation
     * <p>
     * Initial Condition: DataFetcher returns the data, the list of operations
     * contains an ordered set of operations.
     * <p>
     * Steps required for this test:
     * <p>
     * - 1. Create a fetcher containing the indicator data.
     * <p>
     * - 2. Create a list of operations to be performed before analysis calculation.
     * <p>
     * - 3. Pass the data fetcher and list of operations to the IAnalysisStrategy.performCalculation() method.
     * <p>
     * Expected Outcome: Expected result matches the actual result.
     */
    @Test
    public void AnalysisStrategyTest03() {
        // Calculate expected value
        Double c = 1.5;
        Double d = 1.7;
        Map<Integer, Double> map = new TreeMap<Integer, Double>() {{
            put(2001, ratio(2.4 * c, 2.4 + d));
            put(2002, ratio(1.1 * c, 5.1 + d));
            put(2003, ratio(6.2 * c, 3.5 + d));
        }};
        String expected = map.toString();

        // Run strategy algorithm
        List<SeriesOperation> operations = Arrays.asList(
                new Multiply(Indicator.HEALTH_EXPENDITURE_GDP, c),
                new Add(Indicator.AIR_POLLUTION_MEAN, d)
        );
        IAnalysisStrategy strategy = new RatioStrategy()
                .setNumerator(Indicator.HEALTH_EXPENDITURE_GDP)
                .setDenominator(Indicator.AIR_POLLUTION_MEAN)
                .performCalculation(new DummyDataFetcherTwoSeries(), operations);
        String actual = ADAPTER.convert((RatioStrategy) strategy);

        assertEquals("The analysis result was incorrect", expected, actual);
    }

    /**
     * Test ID: AnalysisStrategyTest04
     * <p>
     * Category: Tests the average strategy.
     * <p>
     * Requirement Coverage: UC3-Analysis-Strategy-Average.
     * <p>
     * Initial Condition: DataFetcher returns the data to perform analysis on.
     * <p>
     * Steps required for this test:
     * <p>
     * - 1. Create a fetcher containing the indicator data.
     * <p>
     * - 2. Pass the data fetcher to the IAnalysisStrategy.performCalculation() method.
     * <p>
     * Expected Outcome: Expected result matches the actual result.
     */
    @Test
    public void AnalysisStrategyTest04() {
        // Calculate expected value
        Map<Indicator, Double> map = new TreeMap<Indicator, Double>() {{
            put(Indicator.FOREST_AREA, average(3.5, 4.1, 4.4, 6.2));
        }};
        String expected = map.toString();

        // Run strategy algorithm
        IAnalysisStrategy strategy = new AverageStrategy()
                .performCalculation(new DummyDataFetcherOneSeries());
        String actual = ADAPTER.convert((AverageStrategy) strategy);

        assertEquals("The analysis result was incorrect", expected, actual);
    }

    private Double percentChange(Double a, Double b) {
        return (b / a - 1.0d) * 100.0d;
    }

    private Double ratio(Double a, Double b) {
        return a / b;
    }

    private Double average(Double... numbers) {
        Double sum = 0.0;
        for (Double number : numbers) {
            sum += number;
        }
        return sum / (double) numbers.length;
    }

    // Hard code return data
    private static class DummyDataFetcherThreeSeries implements DataFetcher {

        @Override
        public Map<Indicator, Map<Integer, Double>> getData() {
            Map<Integer, Double> row1 = new TreeMap<Integer, Double>() {{
                put(2001, 3.4);
                put(2002, 4.1);
                put(2003, 6.2);
            }};

            Map<Integer, Double> row2 = new TreeMap<Integer, Double>() {{
                put(2001, 1.4);
                put(2002, 2.1);
                put(2003, 3.5);
            }};

            Map<Integer, Double> row3 = new TreeMap<Integer, Double>() {{
                put(2001, 4.2);
                put(2002, 3.7);
                put(2003, 2.9);
            }};

            return new HashMap<Indicator, Map<Integer, Double>>() {{
                put(Indicator.FOREST_AREA, row1);
                put(Indicator.CO2_EMISSIONS, row2);
                put(Indicator.AIR_POLLUTION_MEAN, row3);
            }};
        }

        @Override
        public Country getCountry() {
            return Country.INDIA;
        }

        @Override
        public int getFromDate() {
            return 2001;
        }

        @Override
        public int getToDate() {
            return 2003;
        }
    }

    // Hard code return data
    private static class DummyDataFetcherTwoSeries implements DataFetcher {

        @Override
        public Map<Indicator, Map<Integer, Double>> getData() {
            Map<Integer, Double> row1 = new TreeMap<Integer, Double>() {{
                put(2001, 2.4);
                put(2002, 1.1);
                put(2003, 6.2);
            }};

            Map<Integer, Double> row2 = new TreeMap<Integer, Double>() {{
                put(2001, 2.4);
                put(2002, 5.1);
                put(2003, 3.5);
            }};

            return new HashMap<Indicator, Map<Integer, Double>>() {{
                put(Indicator.HEALTH_EXPENDITURE_GDP, row1);
                put(Indicator.AIR_POLLUTION_MEAN, row2);
            }};
        }

        @Override
        public Country getCountry() {
            return Country.CANADA;
        }

        @Override
        public int getFromDate() {
            return 2001;
        }

        @Override
        public int getToDate() {
            return 2003;
        }
    }

    // Hard code return data
    private static class DummyDataFetcherOneSeries implements DataFetcher {

        @Override
        public Map<Indicator, Map<Integer, Double>> getData() {
            Map<Integer, Double> row1 = new TreeMap<Integer, Double>() {{
                put(2001, 3.5);
                put(2002, 4.1);
                put(2003, 4.4);
                put(2004, 6.2);
            }};

            return new HashMap<Indicator, Map<Integer, Double>>() {{
                put(Indicator.FOREST_AREA, row1);
            }};
        }

        @Override
        public Country getCountry() {
            return Country.CANADA;
        }

        @Override
        public int getFromDate() {
            return 2001;
        }

        @Override
        public int getToDate() {
            return 2003;
        }
    }
}
