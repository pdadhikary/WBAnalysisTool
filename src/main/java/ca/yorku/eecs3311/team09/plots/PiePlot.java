package ca.yorku.eecs3311.team09.plots;

import ca.yorku.eecs3311.team09.analyses.*;
import ca.yorku.eecs3311.team09.enums.Indicator;
import ca.yorku.eecs3311.team09.exceptions.IncompatibleAnalysisException;
import ca.yorku.eecs3311.team09.plots.designer.PlotDesigner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.util.Map;

/**
 * Generates a {@link javax.swing.JComponent Panel} containing a pie chart of the visited analysis result.
 */
public class PiePlot extends Plot {
    public PiePlot(PlotDesigner designer) {
        this.designer = designer;
    }

    @Override
    public void plotAnalysis(AirPollutionForestArea analysis) {
        throw new IncompatibleAnalysisException("This analysis is incompatible with a pie plot!");

    }

    @Override
    public void plotAnalysis(CO2EnergyUseAirPollution analysis) {
        throw new IncompatibleAnalysisException("This analysis is incompatible with a pie plot!");
    }

    @Override
    public void plotAnalysis(CO2GDP analysis) {
        throw new IncompatibleAnalysisException("This analysis is incompatible with a pie plot!");
    }

    @Override
    public void plotAnalysis(ForestArea analysis) {
        analysis.performCalculation();

        DefaultPieDataset dataset = new DefaultPieDataset();

        Map<Indicator, Double> result = analysis.getResult();

        Double total = 0.0;
        for (Indicator i : result.keySet()) {
            Double indicatorValue = result.get(i);
            total += indicatorValue;
            PiePlot.insertData(dataset, indicatorValue, i.getLabel());

        }
        PiePlot.insertData(dataset, 100 - total, "The Rest");

        JFreeChart pieChart = ChartFactory.createPieChart(analysis.getTitle(), dataset);

        this.designer.applyTheme(pieChart);
        ChartPanel panel = new ChartPanel(pieChart);
        this.designer.formatChartPanel(panel);
        this.plot = panel;
    }

    @Override
    public void plotAnalysis(GovEducationHealthExpenditure analysis) {
        throw new IncompatibleAnalysisException("This analysis is incompatible with a pie plot!");

    }

    @Override
    public void plotAnalysis(GovernmentExpenditure analysis) {
        analysis.performCalculation();

        DefaultPieDataset dataset = new DefaultPieDataset();

        Map<Indicator, Double> result = analysis.getResult();

        Double total = 0.0;
        for (Indicator i : result.keySet()) {
            Double indicatorValue = result.get(i);
            total += indicatorValue;
            PiePlot.insertData(dataset, indicatorValue, i.getLabel());

        }
        PiePlot.insertData(dataset, 100 - total, "The Rest");

        JFreeChart pieChart = ChartFactory.createPieChart(analysis.getTitle(), dataset);

        this.designer.applyTheme(pieChart);
        ChartPanel panel = new ChartPanel(pieChart);
        this.designer.formatChartPanel(panel);
        this.plot = panel;

    }

    @Override
    public void plotAnalysis(HealthCareMortality analysis) {

        throw new IncompatibleAnalysisException("This analysis is incompatible with a pie plot!");
    }

    @Override
    public void plotAnalysis(HealthExpenditureHospitalBeds analysis) {

        throw new IncompatibleAnalysisException("This analysis is incompatible with a pie plot!");
    }

    private static void insertData(
            DefaultPieDataset dataset,
            Double column,
            String label
    ) {
        System.out.println("label: " + label + ", " + "column: " + column);
        dataset.insertValue(0, label, column);
    }

    @Override
    public String toString() {
        return "Pie Plot";
    }
}