package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.AnnualPercentChangeStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.IAnalysisStrategy;
import ca.yorku.eecs3311.team09.data_fetchers.DataFactory;
import ca.yorku.eecs3311.team09.data_fetchers.DataFetcher;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Arrays;
import java.util.List;

public class APCCO2EnergyUseAirPollution implements Analysis {
    protected String title;
    protected IAnalysisStrategy strategy;
    protected List<Indicator> indicators;


    public APCCO2EnergyUseAirPollution() {
        this.title = "CO2 emissions vs energy use & air pollution";
        this.indicators = Arrays.asList(Indicator.CO2_EMISSIONS, Indicator.ENERGY_USE, Indicator.AIR_POLLUTION_MEAN);
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
