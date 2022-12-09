package ca.yorku.eecs3311.team09.plots;

import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.CategoryTableXYDataset;

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
 * Generates a {@link javax.swing.JComponent Panel} containing a bar chart of
 * the visited analysis result.
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
		String label1 = Indicator.AIR_POLLUTION_MEAN.toString();

		Map<Integer, Double> forestArea = result.get(Indicator.FOREST_AREA);
		String label2 = Indicator.FOREST_AREA.toString();

		BarPlot.insertData(dataset, airPollution, label1);
		BarPlot.insertData(dataset, forestArea, label2);

		JFreeChart barChart = ChartFactory.createXYBarChart(analysis.getTitle(), "Year", false, "% Annual Change",
				dataset);

		this.format(barChart);
	}

	@Override
	public void plotAnalysis(CO2EnergyUseAirPollution analysis) {
		analysis.performCalculation();

		CategoryTableXYDataset dataset = new CategoryTableXYDataset();
		Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

		Map<Integer, Double> co2Emissions = result.get(Indicator.CO2_EMISSIONS);
		String label1 = Indicator.CO2_EMISSIONS.toString();

		Map<Integer, Double> energyUse = result.get(Indicator.ENERGY_USE);
		String label2 = Indicator.ENERGY_USE.toString();

		Map<Integer, Double> airPollution = result.get(Indicator.AIR_POLLUTION_MEAN);
		String label3 = Indicator.AIR_POLLUTION_MEAN.toString();

		BarPlot.insertData(dataset, co2Emissions, label1);
		BarPlot.insertData(dataset, energyUse, label2);
		BarPlot.insertData(dataset, airPollution, label3);

		JFreeChart barChart = ChartFactory.createXYBarChart(analysis.getTitle(), "Year", false, "% Annual Change",
				dataset);

		this.format(barChart);
	}

	@Override
	public void plotAnalysis(CO2GDP analysis) {
		analysis.performCalculation();

		CategoryTableXYDataset dataset = new CategoryTableXYDataset();

		Map<Integer, Double> result = analysis.getResult();
		String label = "CO2 Emission to GDP per capita";

		BarPlot.insertData(dataset, result, label);

		JFreeChart barChart = ChartFactory.createXYBarChart(analysis.getTitle(), "Year", false, "Ratio", dataset);

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
		String label1 = Indicator.GOV_EXPENDITURE_EDU_GDP.toString();

		Map<Integer, Double> healthExpenditure = result.get(Indicator.HEALTH_EXPENDITURE_GDP);
		String label2 = Indicator.HEALTH_EXPENDITURE_GDP.toString();

		BarPlot.insertData(dataset, eduExpenditure, label1);
		BarPlot.insertData(dataset, healthExpenditure, label2);

		JFreeChart barChart = ChartFactory.createXYBarChart(analysis.getTitle(), "Year", false, "% Annual Change",
				dataset);

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
		String label1 = Indicator.PROBLEM_ACCESSING_HC_WOMEN.toString();

		Map<Integer, Double> mortality = result.get(Indicator.MORTALITY_RATE_INFANT);
		String label2 = Indicator.MORTALITY_RATE_INFANT.toString();

		BarPlot.insertData(dataset, womenHCProblem, label1);
		BarPlot.insertData(dataset, mortality, label2);

		JFreeChart barChart = ChartFactory.createXYBarChart(analysis.getTitle(), "Year", false,
				"Incidents/per 1,000 births", dataset);

		this.format(barChart);
	}

	@Override
	public void plotAnalysis(HealthExpenditureHospitalBeds analysis) {
		analysis.performCalculation();

		CategoryTableXYDataset dataset = new CategoryTableXYDataset();

		Map<Integer, Double> result = analysis.getResult();
		String label = "Expenditure to GDP ratio";

		BarPlot.insertData(dataset, result, label);

		JFreeChart barChart = ChartFactory.createXYBarChart(analysis.getTitle(), "Year", false, "Ratio", dataset);

		this.format(barChart);
	}

	/**
	 * Inset the column into the provided dataset with the given label.
	 *
	 * @param dataset dataset to modify
	 * @param column  column to insert
	 * @param label   data label
	 */
	private static void insertData(CategoryTableXYDataset dataset, Map<Integer, Double> column, String label) {
		for (Integer year : column.keySet()) {
			dataset.add(year, column.get(year), label);
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
