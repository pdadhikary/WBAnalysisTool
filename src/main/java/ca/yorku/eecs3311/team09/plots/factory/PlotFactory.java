package ca.yorku.eecs3311.team09.plots.factory;

import ca.yorku.eecs3311.team09.exceptions.UnsupportedPlotException;
import ca.yorku.eecs3311.team09.plots.*;
import ca.yorku.eecs3311.team09.plots.designer.DefaultPlotDesigner;
import ca.yorku.eecs3311.team09.plots.designer.PlotDesigner;

public class PlotFactory {
    protected final String plotName;
    protected final String plotType;

    public PlotFactory(String plotType, String plotName) {
        if (plotType == null) {
            throw new RuntimeException("Analysis code cannot be null!");
        }

        this.plotType = plotType;
        this.plotName = plotName;
    }

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

    public String getPlotType() {
        return this.plotType;
    }

    @Override
    public String toString() {
        return this.plotName;
    }
}
