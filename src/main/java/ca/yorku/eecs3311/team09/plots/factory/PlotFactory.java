package ca.yorku.eecs3311.team09.plots.factory;

import ca.yorku.eecs3311.team09.exceptions.UnsupportedPlotException;
import ca.yorku.eecs3311.team09.plots.*;
import ca.yorku.eecs3311.team09.plots.designer.DefaultPlotDesigner;
import ca.yorku.eecs3311.team09.plots.designer.PlotDesigner;

/**
 * A class that acts as a dispatcher for {@link Plot Plot} objects.
 */
public class PlotFactory {
    /**
     * Name of the plot
     */
    protected final String plotName;
    /**
     * Plot type
     */
    protected final String plotType;

    /**
     * Initializes a new plot factory.
     *
     * @param plotType plot type
     * @param plotName plot name
     */
    public PlotFactory(String plotType, String plotName) {
        if (plotType == null) {
            throw new RuntimeException("Analysis code cannot be null!");
        }

        this.plotType = plotType;
        this.plotName = plotName;
    }

    /**
     * Returns a Plot of the specified type.
     *
     * @return plot
     */
    public Plot getPlot() {
        Plot plot;
        PlotDesigner designer = DefaultPlotDesigner.getInstance();

        switch (this.plotType) {
            case "Bar":
                plot = new BarPlot(designer);
                break;
            case "Line":
                plot = new LinePlot(designer);
                break;
            case "Pie":
                plot = new PiePlot(designer);
                break;
            case "Report":
                plot = new ReportPlot(designer);
                break;
            case "Scatter":
                plot = new ScatterPlot(designer);
                break;
            default:
                throw new UnsupportedPlotException(this.plotName + " is an unsupported plot type.");
        }

        return plot;
    }

    /**
     * Return the plot type.
     *
     * @return plot type
     */
    public String getPlotType() {
        return this.plotType;
    }

    /**
     * Return the plot name.
     *
     * @return plot name
     */
    @Override
    public String toString() {
        return this.plotName;
    }
}
