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

/**
 * A singleton plot designer.
 */
public class DefaultPlotDesigner implements PlotDesigner {

    /**
     * Chart theme.
     */
    private final StandardChartTheme chartTheme;

    /**
     * Singleton plot designer
     */
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
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
    }

    @Override
    public void applyTheme(JFreeChart chart) {
        this.chartTheme.apply(chart);
    }

    @Override
    public void setYearFormat(JFreeChart chart) {
        final NumberAxis rangeAxis = (NumberAxis) chart.getXYPlot().getDomainAxis();
        final DecimalFormat format = new DecimalFormat("####");

        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setNumberFormatOverride(format);
    }

    @Override
    public void formatChartPanel(ChartPanel panel) {
        panel.setPreferredSize(new Dimension(450, 300));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.white);
    }

    @Override
    public void setScrollSize(JScrollPane pane) {
        pane.setPreferredSize(new Dimension(450, 300));
    }

    @Override
    public void formatReport(JTextArea report) {
        report.setEditable(false);
        report.setBackground(Color.white);
        report.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        report.setMargin(new Insets(5, 10, 5, 10));
    }
}
