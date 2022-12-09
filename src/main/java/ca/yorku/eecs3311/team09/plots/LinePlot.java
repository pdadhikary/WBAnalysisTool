package ca.yorku.eecs3311.team09.plots;

import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import ca.yorku.eecs3311.team09.analyses.AirPollutionForestArea;
import ca.yorku.eecs3311.team09.analyses.CO2EnergyUseAirPollution;
import ca.yorku.eecs3311.team09.analyses.CO2GDP;
import ca.yorku.eecs3311.team09.analyses.ForestArea;
import ca.yorku.eecs3311.team09.analyses.GovEducationHealthExpenditure;
import ca.yorku.eecs3311.team09.analyses.GovernmentExpenditure;
import ca.yorku.eecs3311.team09.analyses.HealthCareMortality;
import ca.yorku.eecs3311.team09.analyses.HealthExpenditureHospitalBeds;
import ca.yorku.eecs3311.team09.enums.Indicator;
import ca.yorku.eecs3311.team09.exceptions.IncompatibleAnalysisException;
import ca.yorku.eecs3311.team09.plots.designer.PlotDesigner;

/**
 * Generates a {@link javax.swing.JComponent Panel} containing a line chart of
 * the visited analysis result.
 */
public class LinePlot extends Plot {
	public LinePlot(PlotDesigner designer) {
		this.designer = designer;
	}

	@Override
	public void plotAnalysis(AirPollutionForestArea analysis) {
		analysis.performCalculation();
		Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

		Map<Integer, Double> airPollution = result.get(Indicator.AIR_POLLUTION_MEAN);
		String label1 = Indicator.AIR_POLLUTION_MEAN.toString();

		Map<Integer, Double> forestArea = result.get(Indicator.FOREST_AREA);
		String label2 = Indicator.FOREST_AREA.toString();

		XYSeries series1 = LinePlot.createSeries(airPollution, label1);
		XYSeries series2 = LinePlot.createSeries(forestArea, label2);

		XYSeriesCollection dataset = new XYSeriesCollection();

		dataset.addSeries(series1);
		dataset.addSeries(series2);

		JFreeChart lineChart = ChartFactory.createXYLineChart(analysis.getTitle(), "Year", "% Annual Change", dataset);

		this.designer.applyTheme(lineChart);
		this.designer.setYearFormat(lineChart);
		XYPlot xyPlot = lineChart.getXYPlot();
		this.designer.designPlot(xyPlot);
		ChartPanel panel = new ChartPanel(lineChart);
		this.designer.formatChartPanel(panel);
		this.plot = panel;
	}

	@Override
	public void plotAnalysis(CO2EnergyUseAirPollution analysis) {
		analysis.performCalculation();
		Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

		Map<Integer, Double> co2Emissions = result.get(Indicator.CO2_EMISSIONS);
		String label1 = Indicator.CO2_EMISSIONS.toString();

		Map<Integer, Double> energyUse = result.get(Indicator.ENERGY_USE);
		String label2 = Indicator.ENERGY_USE.toString();

		Map<Integer, Double> airPollution = result.get(Indicator.AIR_POLLUTION_MEAN);
		String label3 = Indicator.AIR_POLLUTION_MEAN.toString();

		XYSeries series1 = LinePlot.createSeries(co2Emissions, label1);
		XYSeries series2 = LinePlot.createSeries(energyUse, label2);
		XYSeries series3 = LinePlot.createSeries(airPollution, label3);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		JFreeChart lineChart = ChartFactory.createXYLineChart(analysis.getTitle(), "Year", "% Annual Change", dataset);

		this.format(lineChart);
	}

	@Override
	public void plotAnalysis(CO2GDP analysis) {
		analysis.performCalculation();

		Map<Integer, Double> result = analysis.getResult();
		String label = "CO2 Emission to GDP per capita";

		XYSeries series = LinePlot.createSeries(result, label);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);

		JFreeChart lineChart = ChartFactory.createXYLineChart(analysis.getTitle(), "Year", "Ratio", dataset);

		this.format(lineChart);
	}

	@Override
	public void plotAnalysis(ForestArea analysis) {
		throw new IncompatibleAnalysisException("This analysis is incompatible with a line plot!");
	}

	@Override
	public void plotAnalysis(GovEducationHealthExpenditure analysis) {
		analysis.performCalculation();
		Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

		Map<Integer, Double> eduExpenditure = result.get(Indicator.GOV_EXPENDITURE_EDU_GDP);
		String label1 = Indicator.GOV_EXPENDITURE_EDU_GDP.toString();

		Map<Integer, Double> healthExpenditure = result.get(Indicator.HEALTH_EXPENDITURE_GDP);
		String label2 = Indicator.HEALTH_EXPENDITURE_GDP.toString();

		XYSeries series1 = LinePlot.createSeries(eduExpenditure, label1);
		XYSeries series2 = LinePlot.createSeries(healthExpenditure, label2);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);

		JFreeChart lineChart = ChartFactory.createXYLineChart(analysis.getTitle(), "Year", "% Annual Change", dataset);

		this.format(lineChart);
	}

	@Override
	public void plotAnalysis(GovernmentExpenditure analysis) {
		throw new IncompatibleAnalysisException("This analysis is incompatible with a line plot!");
	}

	@Override
	public void plotAnalysis(HealthCareMortality analysis) {
		analysis.performCalculation();
		Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

		Map<Integer, Double> womenHCProblem = result.get(Indicator.PROBLEM_ACCESSING_HC_WOMEN);
		String label1 = Indicator.PROBLEM_ACCESSING_HC_WOMEN.toString();

		Map<Integer, Double> mortality = result.get(Indicator.MORTALITY_RATE_INFANT);
		String label2 = Indicator.MORTALITY_RATE_INFANT.toString();

		XYSeries series1 = LinePlot.createSeries(womenHCProblem, label1);
		XYSeries series2 = LinePlot.createSeries(mortality, label2);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);

		JFreeChart lineChart = ChartFactory.createXYLineChart(analysis.getTitle(), "Year", "Incidents per 1,000/births",
				dataset);

		this.format(lineChart);
	}

	@Override
	public void plotAnalysis(HealthExpenditureHospitalBeds analysis) {
		analysis.performCalculation();

		Map<Integer, Double> result = analysis.getResult();
		String label = "Expenditure to GDP ratio";

		XYSeries series = LinePlot.createSeries(result, label);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);

		JFreeChart lineChart = ChartFactory.createXYLineChart(analysis.getTitle(), "Year", "Ratio", dataset);

		this.format(lineChart);
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
	 * @param lineChart Chart to format as a plot
	 */
	private void format(JFreeChart lineChart) {
		this.designer.applyTheme(lineChart);
		this.designer.setYearFormat(lineChart);
		XYPlot xyPlot = lineChart.getXYPlot();
		this.designer.designPlot(xyPlot);
		ChartPanel panel = new ChartPanel(lineChart);
		this.designer.formatChartPanel(panel);
		this.plot = panel;
	}

	@Override
	public String toString() {
		return "Line Plot";
	}
}
