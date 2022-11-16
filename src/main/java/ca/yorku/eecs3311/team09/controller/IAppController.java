package ca.yorku.eecs3311.team09.controller;

import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.enums.Country;
import org.jfree.chart.plot.Plot;

import java.util.List;

public interface IAppController {
    List<Country> getCountries();

    Integer getMaxDate();

    Integer getMinDate();

    List<AnalysisFactory> getAnalyses();

    List<Plot> getPlotViews();
}
