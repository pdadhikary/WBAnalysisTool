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

public class RatioHealthExpHospitalBeds implements Analysis {

    protected String title;
    protected IAnalysisStrategy strategy;
    protected Indicator numerator;
    protected Indicator denominator;

    public RatioHealthExpHospitalBeds() {
        this.title = "Health Expenditure to Hospital Beds";
        numerator = Indicator.HEALTH_EXPENDITURE_USD;
        denominator = Indicator.HOSPITAL_BEDS;
    }

    @Override
    public void setData(Country country, int startDate, int fromDate) {
        DataFetcher fetcher = DataFactory.getFetcher(
                Arrays.asList(numerator, denominator),
                country,
                startDate,
                fromDate
        );

        // tell strategy to multiply numerator by 1/1000
        SeriesOperation operation = new Multiply(this.numerator, 0.001);
        this.strategy = new RatioStrategy()
                .setNumerator(numerator)
                .setDenominator(denominator)
                .performCalculation(fetcher, Collections.singletonList(operation));
    }

    @Override
    public void showResult() {
        System.out.println(":");
        this.strategy.printData();
    }

    @Override
    public String toString() {
        return this.title;
    }
}
