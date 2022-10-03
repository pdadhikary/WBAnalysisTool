package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.data_fetchers.BaseFetcher;
import ca.yorku.eecs3311.team09.data_fetchers.ComponentFetcher;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesDataItem;

import java.util.Map;

public class AppData {

    static Country country = Country.CANADA;
    static int fromDate = 2010;
    static int toDate = 2015;

    public static void main(String[] args) {
        DataFetcher dataFetcher = new ComponentFetcher(
                Indicator.CO2_EMISSIONS,
                new ComponentFetcher(
                        Indicator.AIR_POLLUTION_MEAN,
                        new ComponentFetcher(
                                Indicator.FOREST_AREA,
                                new BaseFetcher(
                                        country,
                                        fromDate,
                                        toDate
                                )
                        )
                )
        );

        Map<Indicator, TimeSeries> map = dataFetcher.getData();

        printData(map);
    }

    public static void printData(Map<Indicator, TimeSeries> map) {
        int padding = 20;
        String fString = "%" + padding + "s";
        String fStringF = "%" + padding + ".4f";

        for (int i = fromDate; i <= toDate + 1; i++) {
            if (i == fromDate)
                System.out.printf(fString, "Indicator/Year");
            else
                System.out.printf(fString, i - 1);
        }
        System.out.print("\n");

        for (Indicator i : map.keySet()) {
            System.out.printf(fString, i.getIndicator_token());
            for (Object d : map.get(i).getItems()) {
                TimeSeriesDataItem item = (TimeSeriesDataItem) d;
                System.out.printf(fStringF, (Double) item.getValue());
            }
            System.out.print("\n");
        }
    }
}
