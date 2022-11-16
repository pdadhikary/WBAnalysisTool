package ca.yorku.eecs3311.team09.plots.designer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class DefaultPlotDesigner implements PlotDesigner {

    private StandardChartTheme chartTheme;

    private static final PlotDesigner designer = new DefaultPlotDesigner();

    private DefaultPlotDesigner() {
        this.chartTheme = new StandardChartTheme("Default Theme");

        chartTheme.setLargeFont(
                new Font("Serif", Font.PLAIN, 12)
        );
        chartTheme.setRegularFont(
                new Font("San-Serif", Font.PLAIN, 10)
        );
        chartTheme.setSmallFont(
                new Font("San-Serif", Font.PLAIN, 6)
        );

        chartTheme.setPlotBackgroundPaint(Color.WHITE);
        chartTheme.setRangeGridlinePaint(Color.BLACK);
        chartTheme.setDomainGridlinePaint(Color.BLACK);
    }

    public static PlotDesigner getInstance() {
        return DefaultPlotDesigner.designer;
    }

    @Override
    public void designPlot(XYPlot plot) {
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
    }

    @Override
    public void designPlot(JTextArea plot) {
        plot.setEditable(false);
        plot.setPreferredSize(new Dimension(400, 300));
        plot.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        plot.setBackground(Color.white);
    }

    @Override
    public void applyTheme(JFreeChart chart) {
        this.chartTheme.apply(chart);
    }

    @Override
    public void setYearFormat(JFreeChart chart) {
        final NumberAxis rangeAxis = (NumberAxis) chart.getXYPlot().getDomainAxis();
        final DecimalFormat format = new DecimalFormat("####");
        rangeAxis.setNumberFormatOverride(format);
    }

    @Override
    public void formatChartPanel(ChartPanel panel) {
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.white);
    }
}
