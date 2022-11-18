package ca.yorku.eecs3311.team09.models;

import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.plots.Plot;

public interface IPlotsModel {
    void addPlot(int index, String plotId, AnalysisFactory analysisFactory, Plot plot);

    void removePlot(String plotId);

    int getPlotIndex(String plotId);

    String getPlotId(int Index);

    AnalysisFactory getAnalysis(String plotId);

    Plot getPlot(String plotId);

    String generateUUID();
}
