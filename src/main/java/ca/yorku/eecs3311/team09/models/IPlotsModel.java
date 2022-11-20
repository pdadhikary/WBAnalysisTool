package ca.yorku.eecs3311.team09.models;

import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.plots.factory.PlotFactory;

public interface IPlotsModel {
    void addPlot(int index, String plotId, AnalysisFactory analysisFactory, PlotFactory plotFactory);

    void removePlot(String plotId);

    int getPlotIndex(String plotId);

    String getPlotId(int index);

    AnalysisFactory getAnalysis(String plotId);

    PlotFactory getPlot(String plotId);

    String generateUUID();
}
