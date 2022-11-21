package ca.yorku.eecs3311.team09.plots;

import ca.yorku.eecs3311.team09.analyses.*;
import ca.yorku.eecs3311.team09.enums.Indicator;
import ca.yorku.eecs3311.team09.exceptions.IncompatibleAnalysisException;
import ca.yorku.eecs3311.team09.plots.designer.PlotDesigner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.CategoryTableXYDataset;

import java.util.Map;

/**
 * Generates a {@link javax.swing.JComponent Panel} containing a bar chart of the visited analysis result.
 */
public class BarPlot extends Plot {
    public BarPlot(PlotDesigner designer) {
        this.designer = designer;
    }

    @Override
    public void plotAnalysis(AirPollutionForestArea analysis) {
        analysis.performCalculation();

        CategoryTableXYDataset dataset = new CategoryTableXYDataset();

        Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

        Map<Integer, Double> airPollution = result.get(Indicator.AIR_POLLUTION_MEAN);
        String label1 = "PM2.5 Air Pollution";

        Map<Integer, Double> forestArea = result.get(Indicator.FOREST_AREA);
        String label2 = "Forest Area";

        BarPlot.insertData(dataset, airPollution, label1);
        BarPlot.insertData(dataset, forestArea, label2);

        JFreeChart barChart = ChartFactory.createXYBarChart(
                analysis.getTitle(),
                "Year",
                false,
                "% Annual Change",
                dataset
        );

        this.format(barChart);
    }

    @Override
    public void plotAnalysis(CO2EnergyUseAirPollution analysis) {
        analysis.performCalculation();

        CategoryTableXYDataset dataset = new CategoryTableXYDataset();
        Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

        Map<Integer, Double> co2Emissions = result.get(Indicator.CO2_EMISSIONS);
        String label1 = "CO2 Emissions metric ton per capita";

        Map<Integer, Double> energyUse = result.get(Indicator.ENERGY_USE);
        String label2 = "Energy Use kg per capita";

        Map<Integer, Double> airPollution = result.get(Indicator.AIR_POLLUTION_MEAN);
        String label3 = "Air Pollution per m^3";

        BarPlot.insertData(dataset, co2Emissions, label1);
        BarPlot.insertData(dataset, energyUse, label2);
        BarPlot.insertData(dataset, airPollution, label3);

        JFreeChart barChart = ChartFactory.createXYBarChart(
                analysis.getTitle(),
                "Year",
                false,
                "% Annual Change",
                dataset
        );

        this.format(barChart);
    }

    @Override
    public void plotAnalysis(CO2GDP analysis) {
        analysis.performCalculation();

        CategoryTableXYDataset dataset = new CategoryTableXYDataset();

        Map<Integer, Double> result = analysis.getResult();
        String label = "CO2 Emission to GDP per capita";

        BarPlot.insertData(dataset, result, label);

        JFreeChart barChart = ChartFactory.createXYBarChart(
                analysis.getTitle(),
                "Year",
                false,
                "Ratio",
                dataset
        );

        this.format(barChart);
    }

    @Override
    public void plotAnalysis(ForestArea analysis) {
        throw new IncompatibleAnalysisException("This analysis is incompatible with a bar plot!");
    }

    @Override
    public void plotAnalysis(GovEducationHealthExpenditure analysis) {
        analysis.performCalculation();

        CategoryTableXYDataset dataset = new CategoryTableXYDataset();
        Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

        Map<Integer, Double> eduExpenditure = result.get(Indicator.GOV_EXPENDITURE_EDU_GDP);
        String label1 = "Government Expenditure on Education";

        Map<Integer, Double> healthExpenditure = result.get(Indicator.HEALTH_EXPENDITURE_GDP);
        String label2 = "Government Expenditure on Health";

        BarPlot.insertData(dataset, eduExpenditure, label1);
        BarPlot.insertData(dataset, healthExpenditure, label2);

        JFreeChart barChart = ChartFactory.createXYBarChart(
                analysis.getTitle(),
                "Year",
                false,
                "% Annual Change",
                dataset
        );

        this.format(barChart);
    }

    @Override
    public void plotAnalysis(GovernmentExpenditure analysis) {
        throw new IncompatibleAnalysisException("This analysis is incompatible with a bar plot!");
    }

    @Override
    public void plotAnalysis(HealthCareMortality analysis) {
        analysis.performCalculation();

        CategoryTableXYDataset dataset = new CategoryTableXYDataset();
        Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

        Map<Integer, Double> womenHCProblem = result.get(Indicator.PROBLEM_ACCESSING_HC_WOMEN);
        String label1 = "Problems in accessing health care (% of women)";

        Map<Integer, Double> mortality = result.get(Indicator.MORTALITY_RATE_INFANT);
        String label2 = "Mortality rate, infant";

        BarPlot.insertData(dataset, womenHCProblem, label1);
        BarPlot.insertData(dataset, mortality, label2);

        JFreeChart barChart = ChartFactory.createXYBarChart(
                analysis.getTitle(),
                "Year",
                false,
                "Incidents/per 1,000 births",
                dataset
        );

        this.format(barChart);
    }

    @Override
    public void plotAnalysis(HealthExpenditureHospitalBeds analysis) {
        analysis.performCalculation();

        CategoryTableXYDataset dataset = new CategoryTableXYDataset();

        Map<Integer, Double> result = analysis.getResult();
        String label = "Expenditure to GDP ratio";

        BarPlot.insertData(dataset, result, label);

        JFreeChart barChart = ChartFactory.createXYBarChart(
                analysis.getTitle(),
                "Year",
                false,
                "Ratio",
                dataset
        );

        this.format(barChart);
    }

    /**
     * Inset the column into the provided dataset with the given label.
     *
     * @param dataset dataset to modify
     * @param column  column to insert
     * @param label   data label
     */
    private static void insertData(
            CategoryTableXYDataset dataset,
            Map<Integer, Double> column,
            String label
    ) {
        for (Integer year : column.keySet()) {
            dataset.add(
                    year,
                    column.get(year),
                    label
            );
        }
    }

    /**
     * Format and set the {@link JFreeChart Chart} as a plot.
     *
     * @param barChart Chart to format as a plot
     */
    private void format(JFreeChart barChart) {
        this.designer.applyTheme(barChart);
        this.designer.setYearFormat(barChart);
        ChartPanel panel = new ChartPanel(barChart);
        this.designer.formatChartPanel(panel);
        this.plot = panel;
    }

    @Override
    public String toString() {
        return "Bar Plot";
    }
}
