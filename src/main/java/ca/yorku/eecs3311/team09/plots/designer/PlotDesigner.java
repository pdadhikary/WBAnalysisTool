package ca.yorku.eecs3311.team09.plots.designer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;

import javax.swing.*;

public interface PlotDesigner {
    void designPlot(XYPlot plot);

    void designPlot(JTextArea plot);

    void applyTheme(JFreeChart chart);

    void setYearFormat(JFreeChart chart);

    void formatChartPanel(ChartPanel panel);

    void setScrollSize(JScrollPane pane);
}
