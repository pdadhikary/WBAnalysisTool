package ca.yorku.eecs3311.team09.models;

import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.plots.factory.PlotFactory;

import java.util.*;

/**
 * Uses an ArrayList and Hashmap to keep track of plot position, and the
 * associated analysis and plot factories.
 */
public class PlotsModel implements IPlotsModel {
    /**
     * An entry object of the hashmap.
     */
    protected static class PlotEntry {
        /**
         * analysis factory.
         */
        protected AnalysisFactory factory;
        /**
         * plot factory.
         */
        protected PlotFactory plot;

        public PlotEntry(AnalysisFactory factory, PlotFactory plot) {
            this.factory = factory;
            this.plot = plot;
        }
    }

    /**
     * Map of plotId to entry.
     */
    protected Map<String, PlotEntry> plotMap;
    /**
     * Position of each plotId.
     */
    protected List<String> plotPosition;

    /**
     * Initialize a new PlotsModel.
     */
    public PlotsModel() {
        this.plotMap = new HashMap<>();
        this.plotPosition = new ArrayList<>();
    }

    @Override
    public void addPlot(int index, String plotId, AnalysisFactory analysisFactory, PlotFactory plot) {
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
    public PlotFactory getPlot(String plotId) {
        return this.plotMap.get(plotId).plot;
    }

    @Override
    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
