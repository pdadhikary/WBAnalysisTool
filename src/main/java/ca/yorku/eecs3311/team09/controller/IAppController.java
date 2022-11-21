package ca.yorku.eecs3311.team09.controller;

import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.plots.factory.PlotFactory;

import javax.swing.*;
import java.util.List;

/**
 * Provides a strategy to handle user
 * interactions with the {@link ca.yorku.eecs3311.team09.views.AppView AppView}
 */
public interface IAppController {
    /**
     * Get a list of selectable countries.
     *
     * @return list of countries.
     */
    List<Country> getCountries();

    /**
     * Get a maximum date range.
     *
     * @return max date range
     */
    Integer getMaxDate();

    /**
     * Get a minimum date range.
     *
     * @return min date range
     */
    Integer getMinDate();

    /**
     * Get a list of selectable analyses.
     *
     * @return list of analyses
     */
    List<AnalysisFactory> getAnalyses();

    /**
     * Get a list of selectable plot views.
     *
     * @return list of plot views
     */
    List<PlotFactory> getPlotViews();

    /**
     * Return a new Plot view generated
     * with the selected options.
     *
     * @return new plot view
     */
    JComponent handlePlotCreation();

    /**
     * Handle the deletion of a plot view.
     */
    void handlePlotDeletion();

    /**
     * Handle the recalculation of a plot view.
     */
    void handleRecalculation();

    /**
     * Handle the selection of a plot view
     * with the given plotId.
     *
     * @param plotId plot id
     */
    void handlePlotSelection(String plotId);
}
