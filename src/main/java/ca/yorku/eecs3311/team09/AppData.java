package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AppData {
    static Country country = Country.USA;
    static int fromDate = 2001;
    static int toDate = 2005;

    public static void main(String[] args) {

        List<Indicator> indicators = Arrays.asList(Indicator.CO2_EMISSIONS, Indicator.ENERGY_USE, Indicator.AIR_POLLUTION_MEAN);

        DataFetcher fetcher = DataFactory.getFetcher(
                indicators,
                country,
                fromDate,
                toDate
        );

        Map<Indicator, Map<Integer, Double>> data = fetcher.getData();

        printData(data);
    }

    public static void printData(Map<Indicator, Map<Integer, Double>> map) {
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
            for (Double d : map.get(i).values()) {
                System.out.printf(fStringF, d);
            }
            System.out.print("\n");
        }
    }
}
