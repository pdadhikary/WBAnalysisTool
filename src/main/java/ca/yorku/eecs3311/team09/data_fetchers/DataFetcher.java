package ca.yorku.eecs3311.team09.data_fetchers;

import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;
import org.jfree.data.time.TimeSeries;

import java.util.Map;

public interface DataFetcher {
    public Map<Indicator, TimeSeries> getData();

    public Country getCountry();

    public int getFromDate();

    public int getToDate();
}
