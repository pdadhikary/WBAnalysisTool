package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.analyses.CO2EnergyUseAirPollution;
import ca.yorku.eecs3311.team09.analyses.CO2GDP;
import ca.yorku.eecs3311.team09.analyses.ForestArea;
import ca.yorku.eecs3311.team09.analyses.IAnalysis;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;
import ca.yorku.eecs3311.team09.helpers.StringifyAnalysisVisitor;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class AnalysisTest {
    private StringifyAnalysisVisitor stringifyVisitor;

    @Before
    public void before() {
        this.stringifyVisitor = new StringifyAnalysisVisitor();
    }

    /**
     * Test ID: AnalysisTest01
     * <p>
     * Category: Tests an annual percent change analysis.
     * <p>
     * Requirements Coverage: UC3-Analysis-APC
     * <p>
     * Initial Condition: toDate is greater than or equal to fromDate and
     * the range [fromDate, toDate] is greater than one year.
     * <p>
     * Steps required for this test:
     * <p>
     * - 1. Create a new instance of the analysis.
     * <p>
     * - 2. Call the performCalculation() function of the analysis.
     * <p>
     * - 3. visit the analysis class to retrieve the result in String form.
     * <p>
     * Expected Outcome: Expected result matches the actual result.
     */
    @Test
    public void AnalysisTest01() {
        // Calculate expected value
        Map<Integer, Double> row1 = new TreeMap<Integer, Double>() {{
            put(2001, percentChange(3.3, 3.4));
            put(2002, percentChange(3.4, 4.1));
            put(2003, percentChange(4.1, 6.2));
        }};
        Map<Integer, Double> row2 = new TreeMap<Integer, Double>() {{
            put(2001, percentChange(1.4, 1.4));
            put(2002, percentChange(1.4, 2.1));
            put(2003, percentChange(2.1, 3.5));
        }};
        Map<Integer, Double> row3 = new TreeMap<Integer, Double>() {{
            put(2001, percentChange(3.9, 4.2));
            put(2002, percentChange(4.2, 3.7));
            put(2003, percentChange(3.7, 2.9));
        }};
        Map<Indicator, Map<Integer, Double>> map = new HashMap<Indicator, Map<Integer, Double>>() {{
            put(Indicator.CO2_EMISSIONS, row1);
            put(Indicator.ENERGY_USE, row2);
            put(Indicator.AIR_POLLUTION_MEAN, row3);
        }};
        String expected = map.toString();

        IAnalysis analysis = new MyCO2EnergyUseAirPollution();
        analysis.performCalculation();
        analysis.accept(stringifyVisitor);
        String actual = stringifyVisitor.analysisStringResult;

        assertEquals("The analysis result was incorrect", expected, actual);
    }

    /**
     * Test ID: AnalysisTest02
     * <p>
     * Category: Tests an average analysis.
     * <p>
     * Requirement Coverage: UC3-Analysis-Average.
     * <p>
     * Initial Condition: toDate is greater than or equal to fromDate.
     * <p>
     * Steps required for this test:
     * <p>
     * - 1. Create a new instance of the analysis.
     * <p>
     * - 2. Call the performCalculation() function of the analysis.
     * <p>
     * - 3. visit the analysis class to retrieve the result in String form.
     * <p>
     * Expected Outcome: Expected result matches the actual result.
     */
    @Test
    public void AnalysisTest02() {
        // Calculate expected value
        Map<Indicator, Double> map = new TreeMap<Indicator, Double>() {{
            put(Indicator.FOREST_AREA, average(3.5, 4.1, 4.4, 6.2));
        }};
        String expected = map.toString();

        // Run strategy algorithm
        IAnalysis analysis = new MyForestArea();
        analysis.performCalculation();
        analysis.accept(stringifyVisitor);
        String actual = stringifyVisitor.analysisStringResult;

        assertEquals("The analysis result was incorrect", expected, actual);
    }

    /**
     * Test ID: AnalysisTest03
     * <p>
     * Category: Tests a ratio analysis
     * <p>
     * Requirement Coverage: UC3-Analysis-Ratio
     * <p>
     * Initial Condition: toDate is greater than or equal to fromDate.
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
    public void AnalysisTest03() {
        // Calculate expected value
        Double c = 0.001;
        Map<Integer, Double> map = new TreeMap<Integer, Double>() {{
            put(2001, ratio(2.4, 2.4));
            put(2002, ratio(1.1, 5.1));
            put(2003, ratio(6.2, 3.5));
        }};
        String expected = map.toString();

        // Run strategy algorithm
        IAnalysis analysis = new MyCO2GDP();
        analysis.performCalculation();
        analysis.accept(stringifyVisitor);
        String actual = stringifyVisitor.analysisStringResult;

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

    private static class MyCO2GDP extends CO2GDP {
        public MyCO2GDP() {
            super(Country.INDIA, 2001, 2003);
        }

        @Override
        protected DataFetcher getFetcher() {
            return new DummyDataFetcherTwoSeries();
        }
    }

    private static class MyForestArea extends ForestArea {
        public MyForestArea() {
            super(Country.INDIA, 2001, 2003);
        }

        @Override
        protected DataFetcher getFetcher() {
            return new DummyDataFetcherOneSeries();
        }
    }

    private static class MyCO2EnergyUseAirPollution extends CO2EnergyUseAirPollution {
        public MyCO2EnergyUseAirPollution() {
            super(Country.INDIA, 2001, 2003);
        }

        @Override
        protected DataFetcher getFetcher() {
            return new DummyDataFetcherThreeSeries();
        }
    }

    // Hard code return data
    private static class DummyDataFetcherThreeSeries implements DataFetcher {
        @Override
        public Map<Indicator, Map<Integer, Double>> getData() {
            Map<Integer, Double> row1 = new TreeMap<Integer, Double>() {{
                put(2000, 3.3);
                put(2001, 3.4);
                put(2002, 4.1);
                put(2003, 6.2);
            }};

            Map<Integer, Double> row2 = new TreeMap<Integer, Double>() {{
                put(2000, 1.4);
                put(2001, 1.4);
                put(2002, 2.1);
                put(2003, 3.5);
            }};

            Map<Integer, Double> row3 = new TreeMap<Integer, Double>() {{
                put(2000, 3.9);
                put(2001, 4.2);
                put(2002, 3.7);
                put(2003, 2.9);
            }};

            return new HashMap<Indicator, Map<Integer, Double>>() {{
                put(Indicator.CO2_EMISSIONS, row1);
                put(Indicator.ENERGY_USE, row2);
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
                put(Indicator.CO2_EMISSIONS, row1);
                put(Indicator.GDP_PER_CAPITA_USD, row2);
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
