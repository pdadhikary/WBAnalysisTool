package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.AnnualPercentChangeStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.IAnalysisStrategy;
import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;
import java.util.List;

public class APCAirPollutionForestArea implements Analysis {

    protected String title;
    protected IAnalysisStrategy strategy;
    protected List<Indicator> indicators;

    public APCAirPollutionForestArea() {
        this.title = "Air Pollution vs Forest Area";
        this.indicators = Arrays.asList(Indicator.AIR_POLLUTION_MEAN, Indicator.FOREST_AREA);
    }

    @Override
    public void setData(Country country, int startDate, int fromDate) {
        DataFetcher fetcher = DataFactory.getFetcher(
                this.indicators,
                country,
                startDate - 1,
                fromDate
        );

        this.strategy = new AnnualPercentChangeStrategy().performCalculation(fetcher);
    }

    @Override
    public void showResult() {
        System.out.println(this.title + ":");
        this.strategy.printData();
    }

    @Override
    public String toString() {
        return this.title;
    }
}
