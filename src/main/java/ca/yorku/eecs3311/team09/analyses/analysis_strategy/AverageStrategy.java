package ca.yorku.eecs3311.team09.analyses.analysis_strategy;

import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.HashMap;
import java.util.Map;

/**
 * For each indicator calculates the average value of all data range.
 */
public class AverageStrategy extends AnalysisStrategy {
    /**
     * holds the result of the analysis.
     */
    protected Map<Indicator, Double> result;

    /**
     * Returns a new AverageStrategy
     */
    public AverageStrategy() {
        this.result = new HashMap<>();
    }

    /**
     * Returns the result of the analysis.
     *
     * @return result
     */
    public Map<Indicator, Double> getResult() {
        return this.result;
    }

    @Override
    public void printData() {
        int padding = 20;
        String fStringLabel = "%" + padding + "s";
        String fStringValue = "%" + padding + ".3f";

        System.out.printf(fStringLabel + fStringLabel, "Indicator", "Average");
        System.out.print('\n');

        for (Map.Entry<Indicator, Double> entry : result.entrySet()) {
            System.out.printf(
                    fStringLabel + fStringValue + "\n",
                    entry.getKey().getIndicator_token(), entry.getValue());
        }
    }

    @Override
    protected void calculate() {
        this.result = new HashMap<>();

        for (Indicator indicator : this.data.keySet()) {
            Double average = this.data.get(indicator)
                    .values()
                    .stream()
                    .mapToDouble(d -> d)
                    .average()
                    .orElse(Double.NaN);

            this.result.put(indicator, average);
        }
    }
}
