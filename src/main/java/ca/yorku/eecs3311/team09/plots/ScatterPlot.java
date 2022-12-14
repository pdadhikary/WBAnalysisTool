package ca.yorku.eecs3311.team09.plots;

import ca.yorku.eecs3311.team09.analyses.*;
import ca.yorku.eecs3311.team09.enums.Indicator;
import ca.yorku.eecs3311.team09.exceptions.IncompatibleAnalysisException;
import ca.yorku.eecs3311.team09.plots.designer.PlotDesigner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.Map;

/**
 * Generates a {@link javax.swing.JComponent Panel} containing a scatter chart of the visited analysis result.
 */
public class ScatterPlot extends Plot {

    public ScatterPlot(PlotDesigner designer) {
        this.designer = designer;
    }

    @Override
    public void plotAnalysis(ForestArea analysis) {
        throw new IncompatibleAnalysisException("This analysis is incompatible with scatter plot!");
    }

    @Override
    public void plotAnalysis(GovernmentExpenditure analysis) {
        throw new IncompatibleAnalysisException("This analysis is incompatible with scatter plot!");
    }


    @Override
    public void plotAnalysis(CO2GDP analysis) {
        analysis.performCalculation();

        XYSeriesCollection dataset = new XYSeriesCollection() {
        };
        Map<Integer, Double> result = analysis.getResult();

        // Series 1
        String label1 = "CO2 Emissions to GDP per Capita";
        XYSeries series1 = ScatterPlot.createSeries(result, label1);
        dataset.addSeries(series1);


        JFreeChart scatterPlot = ChartFactory.createScatterPlot(
                analysis.getTitle(), "Year", "Incidents per 1000 Births", dataset
        );

        this.format(scatterPlot);
    }

    @Override
    public void plotAnalysis(HealthExpenditureHospitalBeds analysis) {
        analysis.performCalculation();

        XYSeriesCollection dataset = new XYSeriesCollection() {
        };
        Map<Integer, Double> result = analysis.getResult();

        // Series 1
        String label1 = "Expenditure to GDP Ratio";
        XYSeries series1 = ScatterPlot.createSeries(result, label1);
        dataset.addSeries(series1);


        JFreeChart scatterPlot = ChartFactory.createScatterPlot(
                analysis.getTitle(), "Year", "Ratio", dataset
        );

        this.format(scatterPlot);
    }

    @Override
    public void plotAnalysis(HealthCareMortality analysis) {
        analysis.performCalculation();

        XYSeriesCollection dataset = new XYSeriesCollection() {
        };
        Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

        // Series 1
        Map<Integer, Double> womenHealthProblem = result.get(Indicator.PROBLEM_ACCESSING_HC_WOMEN);
        String label1 = Indicator.PROBLEM_ACCESSING_HC_WOMEN.getLabel();
        XYSeries series1 = ScatterPlot.createSeries(womenHealthProblem, label1);
        dataset.addSeries(series1);

        // Series 2
        Map<Integer, Double> mortality = result.get(Indicator.MORTALITY_RATE_INFANT);
        String label2 = Indicator.MORTALITY_RATE_INFANT.getLabel();
        XYSeries series2 = ScatterPlot.createSeries(mortality, label2);
        dataset.addSeries(series2);

        JFreeChart scatterPlot = ChartFactory.createScatterPlot(
                analysis.getTitle(), "Year", "Incidents per 1000 Births", dataset
        );

        this.format(scatterPlot);
    }

    @Override
    public void plotAnalysis(AirPollutionForestArea analysis) {
        analysis.performCalculation();

        XYSeriesCollection dataset = new XYSeriesCollection() {
        };
        Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

        // Series 1
        Map<Integer, Double> airPollution = result.get(Indicator.AIR_POLLUTION_MEAN);
        String label1 = Indicator.AIR_POLLUTION_MEAN.getLabel();
        XYSeries series1 = ScatterPlot.createSeries(airPollution, label1);
        dataset.addSeries(series1);

        // Series 2
        Map<Integer, Double> forestArea = result.get(Indicator.FOREST_AREA);
        String label2 = Indicator.FOREST_AREA.getLabel();
        XYSeries series2 = ScatterPlot.createSeries(forestArea, label2);
        dataset.addSeries(series2);


        JFreeChart scatterPlot = ChartFactory.createScatterPlot(
                analysis.getTitle(), "Year", "% Annual Change", dataset
        );

        this.format(scatterPlot);
    }

    @Override
    public void plotAnalysis(GovEducationHealthExpenditure analysis) {
        analysis.performCalculation();

        XYSeriesCollection dataset = new XYSeriesCollection() {
        };
        Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

        // Series 1
        Map<Integer, Double> eduExpenditure = result.get(Indicator.GOV_EXPENDITURE_EDU_GDP);
        String label1 = Indicator.GOV_EXPENDITURE_EDU_GDP.getLabel();
        XYSeries series1 = ScatterPlot.createSeries(eduExpenditure, label1);
        dataset.addSeries(series1);

        // Series 2
        Map<Integer, Double> healthExpenditure = result.get(Indicator.HEALTH_EXPENDITURE_GDP);
        String label2 = Indicator.HEALTH_EXPENDITURE_GDP.getLabel();
        XYSeries series2 = ScatterPlot.createSeries(healthExpenditure, label2);
        dataset.addSeries(series2);


        JFreeChart scatterPlot = ChartFactory.createScatterPlot(
                analysis.getTitle(), "Year", "% Annual Change", dataset
        );

        this.format(scatterPlot);
    }

    @Override
    public void plotAnalysis(CO2EnergyUseAirPollution analysis) {
        analysis.performCalculation();

        XYSeriesCollection dataset = new XYSeriesCollection() {
        };
        Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

        // Series 1
        Map<Integer, Double> co2Emissions = result.get(Indicator.CO2_EMISSIONS);
        String label1 = Indicator.CO2_EMISSIONS.getLabel();
        XYSeries series1 = ScatterPlot.createSeries(co2Emissions, label1);
        dataset.addSeries(series1);

        // Series 2
        Map<Integer, Double> energyUse = result.get(Indicator.ENERGY_USE);
        String label2 = Indicator.ENERGY_USE.getLabel();
        XYSeries series2 = ScatterPlot.createSeries(energyUse, label2);
        dataset.addSeries(series2);

        // Series 3
        Map<Integer, Double> airPollution = result.get(Indicator.AIR_POLLUTION_MEAN);
        String label3 = Indicator.AIR_POLLUTION_MEAN.getLabel();
        XYSeries series3 = ScatterPlot.createSeries(airPollution, label3);
        dataset.addSeries(series3);

        JFreeChart scatterPlot = ChartFactory.createScatterPlot(
                analysis.getTitle(), "Year", "% Annual Change", dataset
        );

        this.format(scatterPlot);
    }

    /**
     * Create an XYSeries with the given column and label.
     *
     * @param column data column
     * @param label  data label
     * @return a new {@link XYSeries XYSeries}
     */
    private static XYSeries createSeries(Map<Integer, Double> column, String label) {

        XYSeries series = new XYSeries(label);

        for (Integer year : column.keySet()) {
            series.add(year, column.get(year));
        }

        return series;
    }

    /**
     * Format and set the {@link JFreeChart Chart} as a plot.
     *
     * @param scatterChart Chart to format as a plot
     */
    private void format(JFreeChart scatterChart) {
        this.designer.applyTheme(scatterChart);
        this.designer.setYearFormat(scatterChart);
        ChartPanel panel = new ChartPanel(scatterChart);
        this.designer.formatChartPanel(panel);
        this.plot = panel;
    }

    @Override
    public String toString() {
        return "Scatter Plot";
    }
}
