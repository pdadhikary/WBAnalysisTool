package ca.yorku.eecs3311.team09.data_fetchers;

import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.List;

public class DataFactory {

    private DataFactory() {
    }

    public static DataFetcher getFetcher(List<Indicator> indicators, Country country, int fromDate, int toDate) {
        DataFetcher totalFetcher = new BaseFetcher(country, fromDate, toDate);
        for (Indicator i : indicators) {

            totalFetcher = new ComponentFetcher(i, totalFetcher);
        }
        return totalFetcher;
    }
}
