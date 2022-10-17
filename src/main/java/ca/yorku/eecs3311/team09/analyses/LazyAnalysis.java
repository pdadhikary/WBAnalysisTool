package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.*;

/**
 * A class that does not perform any analysis, simply creates a deep copy of the data and stores it as result.
 */
public abstract class LazyAnalysis extends Analysis {
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
    protected void calculate(Map<Indicator, Map<Integer, Double>> data) {
        this.result = data;
    }
}
