package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.enums.Indicator;
import ca.yorku.eecs3311.team09.exceptions.MissingDataException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A class that calculates the ratio between two indicators over the range [fromDate, toDate].
 * i.e. data(numeratorIndicator) / data(denominatorIndicator)
 */
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

        Analysis.nanFilter(
                data,
                this.fromDate,
                this.toDate
        );

        Map<Integer, Double> numerators = data.get(numerator);
        Map<Integer, Double> denominators = data.get(denominator);

        if (numerators.isEmpty()) {
            throw new MissingDataException("Dataset is empty...");
        }

        for (Integer year : numerators.keySet()) {
            this.result.put(
                    year,
                    numerators.get(year) / denominators.get(year)
            );
        }
    }

}
