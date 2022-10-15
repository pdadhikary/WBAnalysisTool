package ca.yorku.eecs3311.team09.helpers;

import ca.yorku.eecs3311.team09.analyses.analysis_strategy.AnnualPercentChangeStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.AverageStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.LazyStrategy;
import ca.yorku.eecs3311.team09.analyses.analysis_strategy.RatioStrategy;

public class AnalysisAdapter {
    public AnalysisAdapter() {
    }

    public String convert(AnnualPercentChangeStrategy strategy) {
        return strategy.getResult().toString();
    }

    public String convert(AverageStrategy strategy) {
        return strategy.getResult().toString();
    }

    public String convert(LazyStrategy strategy) {
        return strategy.getResult().toString();
    }

    public String convert(RatioStrategy strategy) {
        return strategy.getResult().toString();
    }
}
