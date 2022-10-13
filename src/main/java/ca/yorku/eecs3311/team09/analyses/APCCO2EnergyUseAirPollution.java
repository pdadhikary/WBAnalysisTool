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
    protected List<Indicator> indicators;
    protected String title;
    protected IAnalysisStrategy strategy;


    public APCCO2EnergyUseAirPollution() {
        this.indicators = Arrays.asList(Indicator.CO2_EMISSIONS, Indicator.ENERGY_USE, Indicator.AIR_POLLUTION_MEAN);
        this.title = "CO2 Emissions vs Energy Use & Air Pollution ";
    }


    @Override
    public void setData(Country country, int startDate, int fromDate) {
        DataFetcher fetcher = DataFactory.getFetcher(this.indicators, country, fromDate - 1, startDate);
        this.strategy = new AnnualPercentChangeStrategy().performCalculation(fetcher);
    }

    @Override
    public void showResult() {
        this.strategy.printData();
    }

    @Override
    public String toString() {
        return this.title;
    }
}
