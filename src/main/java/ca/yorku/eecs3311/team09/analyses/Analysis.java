package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Analysis implements IAnalysis {
    protected String title;
    protected Country country;
    protected Integer fromDate;
    protected Integer toDate;

    @Override
    public String getTitle() {
        return String.format(
                "%s (%s) [%d - %d]",
                this.title,
                this.country,
                this.fromDate,
                this.toDate
        );
    }

    @Override
    public Country getCountry() {
        return this.country;
    }

    @Override
    public Integer getFromDate() {
        return this.fromDate;
    }

    @Override
    public Integer getToDate() {
        return this.toDate;
    }

    @Override
    public void performCalculation() {
        DataFetcher fetcher = this.getFetcher();
        this.calculate(fetcher.getData());
    }

    /**
     * Returns a {@link DataFetcher DataFetcher} instance from which data can be retrieved.
     * The country, fromDate and toDate of the fetcher is the same as this.
     *
     * @return a {@link DataFetcher DataFetcher} instance
     */
    protected DataFetcher getFetcher() {
        return DataFactory.getFetcher(this.getIndicators(), this.country, this.fromDate, this.toDate);
    }

    /**
     * Performs the analysis on the data.
     *
     * @param data data to perform analysis on
     */
    protected abstract void calculate(Map<Indicator, Map<Integer, Double>> data);

    /**
     * Multiplies the values of the indicator row by value.
     *
     * @param data      data
     * @param indicator indicator row to multiply
     * @param value     value to multiply by
     */
    protected static void multiply(
            Map<Indicator, Map<Integer, Double>> data,
            Indicator indicator,
            Double value) {
        Map<Integer, Double> series = data.get(indicator);

        series.replaceAll((k, v) -> series.get(k) * value);
    }

    /**
     * Utility method to filter out entire year rows if any of them contain a Double.NaN
     *
     * @param data     data to filter
     * @param fromDate start date to filter from
     * @param toDate   end data to filter to
     */
    protected static void nanFilter(
            Map<Indicator, Map<Integer, Double>> data,
            Integer fromDate,
            Integer toDate
    ) {
        List<Integer> years_to_delete = new ArrayList<>();

        for (int year = fromDate; year <= toDate; year++) {
            // for each year check if data is available for all indicators
            boolean data_available = true;
            for (Indicator indicator : data.keySet()) {
                Double value = data.get(indicator).get(year);
                data_available &= value != null && !Double.isNaN(value);
            }

            // if data is not available queue it up for deletion
            if (!data_available) {
                years_to_delete.add(year);
            }
        }

        // remove data rows for the years with missing data
        for (Integer year : years_to_delete) {
            for (Indicator indicator : data.keySet()) {
                data.get(indicator).remove(year);
            }
        }
    }
}
