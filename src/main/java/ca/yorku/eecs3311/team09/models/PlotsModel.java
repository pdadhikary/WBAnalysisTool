package ca.yorku.eecs3311.team09.models;

import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.plots.Plot;

import java.util.*;

public class PlotsModel implements IPlotsModel {
    protected static class PlotEntry {
        protected AnalysisFactory factory;
        protected Plot plot;

        public PlotEntry(AnalysisFactory factory, Plot plot) {
            this.factory = factory;
            this.plot = plot;
        }
    }

    Map<String, PlotEntry> plotMap;
    List<String> plotPosition;

    public PlotsModel() {
        this.plotMap = new HashMap<>();
        this.plotPosition = new ArrayList<>();
    }

    @Override
    public void addPlot(int index, String plotId, AnalysisFactory analysisFactory, Plot plot) {
        PlotEntry entry = new PlotEntry(analysisFactory, plot);
        this.plotPosition.add(index, plotId);
        this.plotMap.put(plotId, entry);
    }

    @Override
    public void removePlot(String plotId) {
        this.plotPosition.remove(plotId);
        this.plotMap.remove(plotId);
    }

    @Override
    public int getPlotIndex(String plotId) {
        return this.plotPosition.indexOf(plotId);
    }

    @Override
    public String getPlotId(int index) {
        return this.plotPosition.get(index);
    }

    @Override
    public AnalysisFactory getAnalysis(String plotId) {
        return this.plotMap.get(plotId).factory;
    }

    @Override
    public Plot getPlot(String plotId) {
        return this.plotMap.get(plotId).plot;
    }

    @Override
    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
