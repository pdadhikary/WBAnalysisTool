package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.data_fetchers.BaseFetcher;
import ca.yorku.eecs3311.team09.data_fetchers.ComponentFetcher;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;
import org.jfree.data.time.TimeSeries;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DataFetcherTest {

    @Test
    public void test01() {
        BaseFetcher b = new BaseFetcher(
                Country.CANADA,
                2001,
                2004
        );

        assert new HashMap<Indicator, TimeSeries>().equals(b.getData());
    }

    @Test
    public void test02() {
        Indicator i = Indicator.FOREST_AREA;
        Country c = Country.INDIA;

        DataFetcher d = new ComponentFetcher(
                i,
                new BaseFetcher(
                        c,
                        2001, 2001
                )
        );

        assert d.getCountry().equals(c);
    }

    @Test
    public void test03() {
        Indicator i = Indicator.FOREST_AREA;
        Country c = Country.INDIA;
        int year = 2001;

        double exp_value = 38.787;
        double epsilon = 0.0001;

        DataFetcher d = new ComponentFetcher(
                i,
                new BaseFetcher(
                        c,
                        year, year
                )
        );

        Map<Indicator, TimeSeries> data = d.getData();
        
        assert ((Double) data.get(i).getValue(0) - exp_value) < epsilon;
    }
}
