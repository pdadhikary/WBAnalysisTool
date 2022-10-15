package ca.yorku.eecs3311.team09.data_fetchers;

import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Map;

/**
 * Provides an interface to fetch data from the World Bank API, uses the decorator pattern.
 */
public interface DataFetcher {
    /**
     * Retrieves the requested data
     *
     * @return data
     */
    public Map<Indicator, Map<Integer, Double>> getData();

    /**
     * Returns the country of which the data belongs to.
     *
     * @return country
     */
    public Country getCountry();

    /**
     * Returns the start date of the data.
     *
     * @return start date
     */
    public int getFromDate();

    /**
     * Returns the end date of the data.
     *
     * @return end date
     */
    public int getToDate();
}
