package ca.yorku.eecs3311.team09.models;

import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.plots.factory.PlotFactory;

/**
 * Models the plots generated in the app view.
 * Keeps track of the location of each view.
 */
public interface IPlotsModel {
    /**
     * Record the position, analysis factory and plot factories associated
     * the given plotId.
     *
     * @param index           position
     * @param plotId          id
     * @param analysisFactory analysis factory
     * @param plotFactory     plot factory
     */
    void addPlot(int index, String plotId, AnalysisFactory analysisFactory, PlotFactory plotFactory);

    /**
     * Remove the plot with the given id.
     *
     * @param plotId id
     */
    void removePlot(String plotId);

    /**
     * Get the position of the given plot.
     *
     * @param plotId id
     * @return position of the plot.
     */
    int getPlotIndex(String plotId);

    /**
     * Get the id of the plot at the given position.
     *
     * @param index position.
     * @return id of the plot
     */
    String getPlotId(int index);

    /**
     * Get the analysis factory associated with the given
     * plot id.
     *
     * @param plotId id
     * @return analysis factory
     */
    AnalysisFactory getAnalysis(String plotId);

    /**
     * Get the plot factory associated with the given
     * plot id.
     *
     * @param plotId id
     * @return plot factory
     */
    PlotFactory getPlot(String plotId);

    /**
     * Generate a random unique id.
     *
     * @return id
     */
    String generateUUID();
}
