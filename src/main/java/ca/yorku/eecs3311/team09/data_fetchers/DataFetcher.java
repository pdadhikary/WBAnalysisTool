package ca.yorku.eecs3311.team09.data_fetchers;

import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Map;

/**
 * Date fetching interface, uses Decorator Pattern.
 */
public interface DataFetcher {
    /**
     * Retrieves the requested data
     *
     * @return data
     */
    public Map<Indicator, Map<Integer, Double>> getData();

    /**
     * Returns the country of this DataFetcher
     *
     * @return country
     */
    public Country getCountry();

    /**
     * Returns the start date of this DataFetcher
     *
     * @return start date
     */
    public int getFromDate();

    /**
     * Returns the end date of this DataFetcher
     *
     * @return end date
     */
    public int getToDate();
}
