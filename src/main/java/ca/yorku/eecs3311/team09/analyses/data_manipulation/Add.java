package ca.yorku.eecs3311.team09.analyses.data_manipulation;

import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Map;

public class Add implements SeriesOperation {
    protected Indicator indicator;
    protected Double value;

    public Add(Indicator indicator, Double value) {
        this.indicator = indicator;
        this.value = value;
    }

    @Override
    public void calculate(Map<Indicator, Map<Integer, Double>> table) {
        Map<Integer, Double> series = table.get(this.indicator);

        series.replaceAll((k, v) -> series.get(k) * this.value);
    }
}
