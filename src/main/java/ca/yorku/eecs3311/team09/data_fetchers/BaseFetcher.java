package ca.yorku.eecs3311.team09.data_fetchers;

import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.HashMap;
import java.util.Map;

/**
 * Base component of the {@link DataFetcher DataFetcher}.
 */
public final class BaseFetcher implements DataFetcher {
    private final Country country;
    private final int fromDate;
    private final int toDate;

    /**
     * Returns a new BaseFetcher
     *
     * @param country  country to fetch data for
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
    @Override
    public Map<Indicator, Map<Integer, Double>> getData() {
        return new HashMap<>();
    }

    @Override
    public Country getCountry() {
        return this.country;
    }

    @Override
    public int getFromDate() {
        return this.fromDate;
    }

    @Override
    public int getToDate() {
        return this.toDate;
    }
}
