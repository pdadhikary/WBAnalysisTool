package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that calculates the average of each indicator over the range [fromDate, toDate].
 */
public abstract class AverageAnalysis extends Analysis {
    /**
     * holds the result of the analysis.
     */
    protected Map<Indicator, Double> result;

    /**
     * Holds the indicators for which the analysis is being performed.
     */
    protected List<Indicator> indicators;

    public Map<Indicator, Double> getResult() {
        return new HashMap<>(this.result);
    }

    @Override
    public List<Indicator> getIndicators() {
        return new ArrayList<>(this.indicators);
    }

    @Override
    protected void calculate(Map<Indicator, Map<Integer, Double>> data) {
        this.result = new HashMap<>();

        for (Indicator indicator : data.keySet()) {
            Double average = data.get(indicator)
                    .values()
                    .stream()
                    .mapToDouble(d -> d)
                    .average()
                    .orElse(Double.NaN);

            this.result.put(indicator, average);
        }
    }
}
