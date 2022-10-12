package ca.yorku.eecs3311.team09.data_fetchers;

import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.HashMap;
import java.util.Map;

/**
 * Base component of the decorator data fetcher.
 */
public final class BaseFetcher implements DataFetcher {
    private final Country country;
    private final int fromDate;
    private final int toDate;

    /**
     * Returns a new BaseFetcher
     *
     * @param country  country to fetch data from
     * @param fromDate start date for data
     * @param toDate   end date for data
     */
    public BaseFetcher(Country country, int fromDate, int toDate) {
        this.country = country;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * return a new empty map
     *
     * @return empty map
     */
    public Map<Indicator, Map<Integer, Double>> getData() {
        return new HashMap<>();
    }

    public Country getCountry() {
        return this.country;
    }

    public int getFromDate() {
        return this.fromDate;
    }

    public int getToDate() {
        return this.toDate;
    }
}
