package ca.yorku.eecs3311.team09.plots.designer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;

import javax.swing.*;

/**
 * Provided design functionalities for {@link ca.yorku.eecs3311.team09.plots.Plot Plots}.
 */
public interface PlotDesigner {

    /**
     * Make plot specific adjustment for {@link XYPlot}
     *
     * @param plot plot
     */
    void designPlot(XYPlot plot);

    /**
     * Apply a uniform theme to the chart
     *
     * @param chart chart
     */
    void applyTheme(JFreeChart chart);

    /**
     * Adjust the axis of the chart to properly represent
     * a year axis.
     *
     * @param chart chart
     */
    void setYearFormat(JFreeChart chart);

    /**
     * Make chart panel specific modifications.
     *
     * @param panel chart panel
     */
    void formatChartPanel(ChartPanel panel);

    /**
     * Set the scroll size of a report panel.
     *
     * @param pane report panel
     */
    void setScrollSize(JScrollPane pane);

    /**
     * Format a report text area.
     *
     * @param report text area
     */
    void formatReport(JTextArea report);
}
