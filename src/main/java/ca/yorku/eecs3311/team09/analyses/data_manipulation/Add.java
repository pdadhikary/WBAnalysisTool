package ca.yorku.eecs3311.team09.analyses.data_manipulation;

import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Map;

/**
 * Adds a constant to the indicator data.
 */
public class Add implements SeriesOperation {
    /**
     * indicator to perform operation on
     */
    protected Indicator indicator;
    /**
     * constant value to add
     */
    protected Double value;

    public Add(Indicator indicator, Double value) {
        this.indicator = indicator;
        this.value = value;
    }

    @Override
    public void calculate(Map<Indicator, Map<Integer, Double>> table) {
        Map<Integer, Double> series = table.get(this.indicator);

        series.replaceAll((k, v) -> series.get(k) + this.value);
    }
}
