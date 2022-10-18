package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.*;

/**
 * A class that calculates the annual percent change of indicators of each year from the previous.
 * i.e. ( data(thisYear) / data(previousYear) - 1 ) * 100
 */
public abstract class AnnualPercentChangeAnalysis extends Analysis {
    /**
     * holds the result of the analysis.
     */
    protected Map<Indicator, Map<Integer, Double>> result;

    /**
     * Holds the indicators for which the analysis is being performed.
     */
    protected List<Indicator> indicators;

    public Map<Indicator, Map<Integer, Double>> getResult() {

        Map<Indicator, Map<Integer, Double>> output = new HashMap<>();

        for (Indicator indicator : this.result.keySet()) {
            Map<Integer, Double> dataSeries = new TreeMap<>(this.result.get(indicator));
            output.put(indicator, dataSeries);
        }

        return output;
    }

    @Override
    public List<Indicator> getIndicators() {
        return new ArrayList<>(this.indicators);
    }

    @Override
    protected DataFetcher getFetcher() {
        return DataFactory.getFetcher(this.getIndicators(), this.country, this.fromDate - 1, this.toDate);
    }

    @Override
    protected void calculate(Map<Indicator, Map<Integer, Double>> data) {
        this.result = new HashMap<>();

        int start = this.fromDate;
        int end = this.toDate;

        for (Indicator indicator : data.keySet()) {
            Map<Integer, Double> dataSeries = data.get(indicator);
            Map<Integer, Double> resultSeries = new HashMap<>();


            for (int year = start; year <= end; year++) {
                resultSeries.put(
                        year,
                        (dataSeries.get(year) / dataSeries.get(year - 1) - 1) * 100
                );
            }

            this.result.put(indicator, resultSeries);
        }
    }
}
