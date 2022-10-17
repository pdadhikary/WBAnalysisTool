package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Map;

public abstract class Analysis implements IAnalysis {
    protected String title;
    protected Country country;
    protected Integer fromDate;
    protected Integer toDate;

    @Override
    public String getTitle() {
        return this.title;
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

    protected DataFetcher getFetcher() {
        return DataFactory.getFetcher(this.getIndicators(), this.country, this.fromDate, this.toDate);
    }

    protected abstract void calculate(Map<Indicator, Map<Integer, Double>> data);

    protected static void multiply(
            Map<Indicator, Map<Integer, Double>> data,
            Indicator indicator,
            Double value) {
        Map<Integer, Double> series = data.get(indicator);

        series.replaceAll((k, v) -> series.get(k) * value);
    }
}
