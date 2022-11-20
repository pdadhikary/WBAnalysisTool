package ca.yorku.eecs3311.team09.controller;

import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.plots.factory.PlotFactory;

import javax.swing.*;
import java.util.List;

public interface IAppController {
    List<Country> getCountries();

    Integer getMaxDate();

    Integer getMinDate();

    List<AnalysisFactory> getAnalyses();

    List<PlotFactory> getPlotViews();

    JComponent handlePlotCreation();

    void handlePlotDeletion();

    void handleRecalculation();

    void handlePlotSelection(String plotId);
}
