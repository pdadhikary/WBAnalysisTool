package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Map;

/**
 * Analyzes the ratio of Current health expenditure (per 1,000 people) to
 * Hospital beds (per 1,000 people).
 */
public class HealthExpenditureHospitalBeds extends RatioAnalysis {
    /**
     * Title of the Analysis class.
     */
    public static final String TITLE = "Ratio Health Expenditure to Hospital Beds";

    /**
     * Returns a new instance of this analysis.
     */
    public HealthExpenditureHospitalBeds(Country country, Integer fromDate, Integer toDate) {
        this.title = HealthExpenditureHospitalBeds.TITLE;
        this.numerator = Indicator.HEALTH_EXPENDITURE_USD;
        this.denominator = Indicator.HOSPITAL_BEDS;

        this.country = country;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public void performCalculation() {
        DataFetcher fetcher = this.getFetcher();
        Map<Indicator, Map<Integer, Double>> data = fetcher.getData();

        // divide health expenditure by 1000 to covert (per capita) to (per 1000 people)
        Analysis.multiply(data, this.numerator, 0.001);

        this.calculate(data);
    }

    @Override
    public void accept(AnalysisVisitor visitor) {
        visitor.visitAnalysis(this);
    }
}
