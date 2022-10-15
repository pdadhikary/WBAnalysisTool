package ca.yorku.eecs3311.team09.analyses.analysis_strategy;

import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Map;
import java.util.TreeMap;

public class RatioStrategy extends AnalysisStrategy {
    protected Map<Integer, Double> result;
    protected Indicator numerator;
    protected Indicator denominator;

    public Map<Integer, Double> getResult() {
        return this.result;
    }

    public RatioStrategy setNumerator(Indicator numerator) {
        this.numerator = numerator;
        return this;
    }

    public RatioStrategy setDenominator(Indicator denominator) {
        this.denominator = denominator;
        return this;
    }

    @Override
    public void printData() {
        int padding = 20;
        String fStringLabel = "%" + padding + "s";
        String fStringValue = "%" + padding + ".3f";

        int fromDate = this.fetcher.getFromDate();
        int toDate = this.fetcher.getToDate();

        for (int year = fromDate; year <= toDate; year++) {
            System.out.printf(fStringLabel, year);
        }

        System.out.println();

        for (Integer year : this.result.keySet()) {
            System.out.printf(fStringValue, this.result.get(year));
        }
        System.out.println();
    }

    @Override
    protected void calculate() {
        this.result = new TreeMap<>();

        Map<Integer, Double> numerators = this.data.get(numerator);
        Map<Integer, Double> denominators = this.data.get(denominator);

        for (int year = fetcher.getFromDate(); year <= fetcher.getToDate(); year++) {
            this.result.put(
                    year,
                    numerators.get(year) / denominators.get(year)
            );
        }
    }
}
