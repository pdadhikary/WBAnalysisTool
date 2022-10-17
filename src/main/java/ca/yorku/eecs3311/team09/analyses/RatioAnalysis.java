package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class RatioAnalysis extends Analysis {
    /**
     * holds the result of the analysis.
     */
    protected Map<Integer, Double> result;
    /**
     * holds the numerator indicator.
     */
    protected Indicator numerator;
    /**
     * holds the denominator indicator.
     */
    protected Indicator denominator;

    public Map<Integer, Double> getResult() {
        return new TreeMap<>(this.result);
    }

    @Override
    public List<Indicator> getIndicators() {
        return Arrays.asList(numerator, denominator);
    }

    @Override
    protected void calculate(Map<Indicator, Map<Integer, Double>> data) {
        this.result = new TreeMap<>();

        Map<Integer, Double> numerators = data.get(numerator);
        Map<Integer, Double> denominators = data.get(denominator);

        for (int year = this.fromDate; year <= this.toDate; year++) {
            this.result.put(
                    year,
                    numerators.get(year) / denominators.get(year)
            );
        }
    }

}
