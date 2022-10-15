package ca.yorku.eecs3311.team09.analyses.analysis_strategy;

import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Does not perform any analysis, simple creates a deep copy of the data and stores it as result.
 */
public class LazyStrategy extends AnalysisStrategy {
    /**
     * holds the result of the analysis.
     */
    protected Map<Indicator, Map<Integer, Double>> result;

    /**
     * Returns a new LazyStrategy.
     */
    public LazyStrategy() {
        this.result = new HashMap<>();
    }

    /**
     * Returns the result of the analysis.
     *
     * @return result
     */
    public Map<Indicator, Map<Integer, Double>> getResult() {
        return this.result;
    }

    @Override
    public void printData() {
        int padding = 20;
        String fStringLabel = "%" + padding + "s";
        String fStringValue = "%" + padding + ".3f";

        int fromDate = this.fetcher.getFromDate();
        int toDate = this.fetcher.getToDate();

        for (int i = fromDate; i <= toDate + 1; i++) {
            if (i == fromDate)
                System.out.printf(fStringLabel, "Indicator/Year");
            else
                System.out.printf(fStringLabel, i - 1);
        }
        System.out.print("\n");

        for (Indicator i : this.data.keySet()) {
            System.out.printf(fStringLabel, i.getIndicator_token());
            for (Double d : this.data.get(i).values()) {
                System.out.printf(fStringValue, d);
            }
            System.out.print("\n");
        }
    }

    @Override
    protected void calculate() {
        // create deep copy of data
        this.result = new HashMap<>();

        for (Indicator indicator : this.data.keySet()) {
            Map<Integer, Double> dataSeries = new TreeMap<>(this.data.get(indicator));
            this.result.put(indicator, dataSeries);
        }
    }
}
