package ca.yorku.eecs3311.team09.data_fetchers;

import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.List;

/**
 * Provides a static factory method for initializing a DataFetcher instance.
 */
public class DataFactory {

    private DataFactory() {
    }

    /**
     * Returns a {@link DataFetcher DataFetcher} containing the requested data.
     *
     * @param indicators data indications
     * @param country    country for which to get data for
     * @param fromDate   start date of the data
     * @param toDate     end date of the data
     * @return a data fetcher
     */
    public static DataFetcher getFetcher(List<Indicator> indicators, Country country, int fromDate, int toDate) {
        DataFetcher totalFetcher = new BaseFetcher(country, fromDate, toDate);
        for (Indicator i : indicators) {

            totalFetcher = new ComponentFetcher(i, totalFetcher);
        }
        return totalFetcher;
    }
}
