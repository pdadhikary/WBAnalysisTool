package ca.yorku.eecs3311.team09.analyses.analysis_strategy;

import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.HashMap;
import java.util.Map;

/**
 * Calculates the annual percentage change of each indication from the previous year.
 * i.e. ( data(thisYear) / data(previousYear) - 1 ) * 100
 */
public class AnnualPercentChangeStrategy extends AnalysisStrategy {
    /**
     * holds the result of the analysis.
     */
    protected Map<Indicator, Map<Integer, Double>> result;

    /**
     * Returns a new AnnualPercentChangeStrategy.
     */
    public AnnualPercentChangeStrategy() {
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

        for (int i = fromDate; i <= toDate; i++) {
            if (i == fromDate)
                System.out.printf(fStringLabel, "Indicator/Year");
            else
                System.out.printf(fStringLabel, i);
        }
        System.out.print("\n");

        for (Indicator i : this.result.keySet()) {
            System.out.printf(fStringLabel, i.getIndicator_token());
            for (Double d : this.result.get(i).values()) {
                System.out.printf(fStringValue, d);
            }
            System.out.print("\n");
        }
    }

    @Override
    protected void calculate() {
        this.result = new HashMap<>();

        int fromDate = this.fetcher.getFromDate() + 1;
        int toDate = this.fetcher.getToDate();

        for (Indicator indicator : this.data.keySet()) {
            Map<Integer, Double> dataSeries = this.data.get(indicator);
            Map<Integer, Double> resultSeries = new HashMap<>();


            for (int year = fromDate; year <= toDate; year++) {
                resultSeries.put(
                        year,
                        (dataSeries.get(year) / dataSeries.get(year - 1) - 1) * 100
                );
            }

            this.result.put(indicator, resultSeries);
        }
    }
}
