package ca.yorku.eecs3311.team09.plots;

import ca.yorku.eecs3311.team09.analyses.*;
import ca.yorku.eecs3311.team09.enums.Indicator;
import ca.yorku.eecs3311.team09.exceptions.IncompatibleAnalysisException;
import ca.yorku.eecs3311.team09.plots.designer.PlotDesigner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

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

        Indicator indicator = Indicator.FOREST_AREA;
        Double value = analysis.getResult().get(indicator);

        JFreeChart pieChart = this.createChart(value, analysis.getTitle(), indicator.getLabel());

        this.format(pieChart);
    }

    @Override
    public void plotAnalysis(GovEducationHealthExpenditure analysis) {
        throw new IncompatibleAnalysisException("This analysis is incompatible with a pie plot!");

    }

    @Override
    public void plotAnalysis(GovernmentExpenditure analysis) {
        analysis.performCalculation();

        Indicator indicator = Indicator.GOV_EXPENDITURE_EDU_GDP;
        Double value = analysis.getResult().get(indicator);

        JFreeChart pieChart = this.createChart(value, analysis.getTitle(), indicator.getLabel());

        this.format(pieChart);
    }

    @Override
    public void plotAnalysis(HealthCareMortality analysis) {

        throw new IncompatibleAnalysisException("This analysis is incompatible with a pie plot!");
    }

    @Override
    public void plotAnalysis(HealthExpenditureHospitalBeds analysis) {

        throw new IncompatibleAnalysisException("This analysis is incompatible with a pie plot!");
    }

    /**
     * Generate a pie chart with the given label, value, and
     * it's percentage complement.
     *
     * @param value value of the pie chart
     * @param title title
     * @param label data label
     * @return a new {@link JFreeChart JFreeChart}
     */
    private JFreeChart createChart(Double value, String title, String label) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        Double rest = 100 - value;

        dataset.insertValue(
                0,
                String.format("%s - [%.2f%%]", label, value),
                value
        );
        dataset.insertValue(
                1,
                String.format("The rest - [%.2f%%]", rest),
                rest
        );

        return ChartFactory.createPieChart(title, dataset);
    }

    /**
     * Format and set the {@link JFreeChart Chart} as a plot.
     *
     * @param pieChart Chart to format as a plot
     */
    private void format(JFreeChart pieChart) {
        this.designer.applyTheme(pieChart);
        ChartPanel panel = new ChartPanel(pieChart);
        this.designer.formatChartPanel(panel);
        this.plot = panel;
    }

    @Override
    public String toString() {
        return "Pie Plot";
    }
}