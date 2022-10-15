package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.IAnalysisStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.RatioStrategy;
import ca.yorku.eecs3311.team09.analyses.data_manipulation.Multiply;
import ca.yorku.eecs3311.team09.analyses.data_manipulation.SeriesOperation;
import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;
import java.util.Collections;

/**
 * Analyzes the ratio of Current health expenditure per capita (current US$) to
 * Hospital beds (per 1,000 people).
 */
public class RatioHealthExpHospitalBeds implements Analysis {
    /**
     * numerator indicator of this analysis
     */
    protected Indicator indicatorsNumerator;
    /**
     * numerator indicator of this analysis
     */
    protected Indicator indicatorsDenominator;
    /**
     * title of this analysis.
     */
    protected String title;
    /**
     * analysis strategy for this analysis.
     */
    protected IAnalysisStrategy strategy;

    /**
     * Returns a new instance of this analysis.
     */
    public RatioHealthExpHospitalBeds() {
        this.title = "Ratio Health Expenditure to Hospital Beds";
        indicatorsNumerator = Indicator.HEALTH_EXPENDITURE_USD;
        indicatorsDenominator = Indicator.HOSPITAL_BEDS;
    }

    @Override
    public void setData(Country country, int fromDate, int toDate) {
        DataFetcher fetcher = DataFactory.getFetcher(
                Arrays.asList(indicatorsNumerator, indicatorsDenominator),
                country,
                fromDate,
                toDate
        );

        // tell strategy to multiply numerator by 1/1000
        SeriesOperation operation = new Multiply(this.indicatorsNumerator, 0.001);
        this.strategy = new RatioStrategy()
                .setNumerator(indicatorsNumerator)
                .setDenominator(indicatorsDenominator)
                .performCalculation(fetcher, Collections.singletonList(operation));
    }

    @Override
    public void showResult() {
        System.out.println(this.title + ":");
        this.strategy.printData();
    }

    @Override
    public IAnalysisStrategy getStrategy() {
        return this.strategy;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
