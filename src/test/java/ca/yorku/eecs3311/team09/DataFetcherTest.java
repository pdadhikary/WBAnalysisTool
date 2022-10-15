package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.data_fetchers.BaseFetcher;
import ca.yorku.eecs3311.team09.data_fetchers.ComponentFetcher;
import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class DataFetcherTest {
    private static final double EPSILON = 0.0001;

    /**
     * Test ID: DataFetcherTest01
     * <p>
     * Category: Tests the base class of the DateFetcher decorator.
     * <p>
     * Requirement Coverage: UC3-DataFetcher-BaseFetcher
     * <p>
     * Initial Condition: toDate greater than or equal to fromDate
     * <p>
     * Steps required for this test:
     * <p>
     * - 1. Initialize a BaseFetcher with Country, fromDate and toDate
     * <p>
     * - 2. Call the getData() method on the fetcher.
     * <p>
     * - 3. Check if the data container is empty.
     * <p>
     * Expected Outcome: returns an empty container
     */
    @Test
    public void DataFetcherTest01() {
        int expected = 0;

        BaseFetcher b = new BaseFetcher(
                Country.CANADA,
                2001,
                2004
        );

        assertEquals(
                "The size of the data container should be " + expected,
                expected,
                b.getData().size()
        );
    }

    /**
     * Test ID: DataFetcherTest02
     * <p>
     * Category: Tests the component class of the DateFetcher decorator.
     * <p>
     * Requirement Coverage: UC3-DataFetcher-ComponentFetcher
     * <p>
     * Initial Condition: the BaseFetcher is initialized with a country,
     * fromDate and toDate (toDate greater than or equal to fromDate)
     * <p>
     * Steps required for this test:
     * <p>
     * - 1. Initialize a ComponentFetcher with an indicator and BaseFetcher
     * <p>
     * - 2. Call the getData() method on the fetcher.
     * <p>
     * - 3. Check if the value of the provided years matches upto 3 decimal points.
     * <p>
     * Expected Outcome: returns the corresponding values on the WorldBank API.
     */
    @Test
    public void DataFetcherTest02() {
        Country c = Country.INDIA;
        int fromDate = 2000;
        int toDate = 2005;
        Indicator i = Indicator.FOREST_AREA;
        Map<Integer, Double> expected = new TreeMap<>();
        expected.put(2005, 23.0538);
        expected.put(2004, 22.9897);
        expected.put(2003, 22.9257);
        expected.put(2002, 22.8616);
        expected.put(2001, 22.7975);
        expected.put(2000, 22.7334);

        DataFetcher d = new ComponentFetcher(
                i,
                new BaseFetcher(c, fromDate, toDate)
        );

        Map<Indicator, Map<Integer, Double>> data = d.getData();

        for (int year = fromDate; year <= toDate; year++) {
            assertEquals(
                    String.format("The %s of %s in the year %d should be %.3f", i, c, year, expected.get(year)),
                    expected.get(year),
                    data.get(i).get(year),
                    EPSILON
            );
        }
    }

    /**
     * Test ID: DataFetcherTest03
     * <p>
     * Category: Tests the DataFactory with multiple indicators.
     * <p>
     * Requirement Coverage: UC3-DataFetcher-DataFactory-Multiple-Indicators
     * <p>
     * Initial Condition: fromDate less than or equal to toDate
     * <p>
     * Steps required for this test:
     * <p>
     * - 1. Call the static DataFactory method with country, fromDate and toDate to get the DataFetcher.
     * <p>
     * - 2. Call the getData() method on the fetcher.
     * <p>
     * - 3. Check if the value of each indicator matches upto 3 decimal points.
     * <p>
     * Expected Outcome: returns the corresponding values on the WorldBank api
     */
    @Test
    public void DataFetcherTest03() {
        Country c = Country.BRAZIL;
        int year = 2013;
        Indicator i1 = Indicator.CO2_EMISSIONS;
        double expected1 = 2.4216;
        Indicator i2 = Indicator.AIR_POLLUTION_MEAN;
        double expected2 = 14.6099;

        DataFetcher d = DataFactory.getFetcher(
                Arrays.asList(i1, i2),
                c,
                year,
                year
        );

        Map<Indicator, Map<Integer, Double>> data = d.getData();

        assertEquals(
                String.format("The %s of %s in the year %d should be %.3f", i1, c, year, expected1),
                expected1,
                data.get(i1).get(year),
                EPSILON
        );

        assertEquals(
                String.format("The %s of %s in the year %d should be %.3f", i2, c, year, expected2),
                expected2,
                data.get(i2).get(year),
                EPSILON
        );
    }
}
