package ca.yorku.eecs3311.team09.analyses.analysis_strategy;

import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.HashMap;
import java.util.Map;

public class AverageStrategy extends AnalysisStrategy {
    protected Map<Indicator, Double> result;

    @Override
    public void printData() {
        int padding = 20;
        String fStringLabel = "%" + padding + "s";
        String fStringValue = "%" + padding + ".3f";

        System.out.printf(fStringLabel + fStringLabel, "Indicator", "Average\n");

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
