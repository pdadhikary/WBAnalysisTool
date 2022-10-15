package ca.yorku.eecs3311.team09.analyses.analysis_strategy;

import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Map;
import java.util.TreeMap;

/**
 * For each year in the date range, calculates the ration between the numerator indicator and the denominator indicator.
 * i.e. data(numeratorIndicator) / data(denominatorIndicator)
 */
public class RatioStrategy extends AnalysisStrategy {
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

    /**
     * Returns a new RatioStrategy.
     */
    public RatioStrategy() {
        this.result = new TreeMap<>();
    }

    /**
     * Returns the result of the analysis.
     *
     * @return result
     */
    public Map<Integer, Double> getResult() {
        return this.result;
    }

    /**
     * Sets the numerator indicator of the analysis.
     *
     * @param numerator numerator indicator
     * @return this RatioStrategy.
     */
    public RatioStrategy setNumerator(Indicator numerator) {
        this.numerator = numerator;
        return this;
    }

    /**
     * Sets the denominator of the analysis.
     *
     * @param denominator denominator indicator
     * @return this RatioStrategy.
     */
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

    /**
     * Helper function that defines the analysis algorithm and manipulates the data.
     *
     * @throws NullPointerException if the numerator and denominators are null.
     */
    @Override
    protected void calculate() throws NullPointerException {
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
