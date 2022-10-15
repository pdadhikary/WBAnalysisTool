package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.AnnualPercentChangeStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.IAnalysisStrategy;
import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;
import java.util.List;

/**
 * Analyzes the annual percent change of
 * PM2.5 air pollution, mean annual exposure (micrograms per cubic meter) vs Forest area (% of land area).
 */
public class APCAirPollutionForestArea implements Analysis {
    /**
     * title of this analysis.
     */
    protected String title;
    /**
     * analysis strategy for this analysis.
     */
    protected IAnalysisStrategy strategy;
    /**
     * list of indicators used for this analysis.
     */
    protected List<Indicator> indicators;

    /**
     * Returns a new instance of this analysis.
     */
    public APCAirPollutionForestArea() {
        this.title = "Air pollution vs forest area";
        this.indicators = Arrays.asList(Indicator.AIR_POLLUTION_MEAN, Indicator.FOREST_AREA);
    }

    @Override
    public void setData(Country country, int fromDate, int toDate) {
        DataFetcher fetcher = DataFactory.getFetcher(
                this.indicators,
                country,
                fromDate - 1,
                toDate
        );
        this.strategy = new AnnualPercentChangeStrategy().performCalculation(fetcher);
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
