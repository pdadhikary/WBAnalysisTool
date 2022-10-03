package ca.yorku.eecs3311.team09.data_fetchers;

import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;
import org.jfree.data.time.TimeSeries;

import java.util.HashMap;
import java.util.Map;

public final class BaseFetcher implements DataFetcher {
    private final Country country;
    private final int fromDate;
    private final int toDate;

    public BaseFetcher(Country country, int fromDate, int toDate) {
        this.country = country;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Map<Indicator, TimeSeries> getData() {
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
