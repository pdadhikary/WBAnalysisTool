package ca.yorku.eecs3311.team09;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.junit.Assert;
import org.junit.Test;

import ca.yorku.eecs3311.team09.analyses.AirPollutionForestArea;
import ca.yorku.eecs3311.team09.analyses.AnnualPercentChangeAnalysis;
import ca.yorku.eecs3311.team09.analyses.AverageAnalysis;
import ca.yorku.eecs3311.team09.analyses.CO2EnergyUseAirPollution;
import ca.yorku.eecs3311.team09.analyses.CO2GDP;
import ca.yorku.eecs3311.team09.analyses.ForestArea;
import ca.yorku.eecs3311.team09.analyses.GovEducationHealthExpenditure;
import ca.yorku.eecs3311.team09.analyses.GovernmentExpenditure;
import ca.yorku.eecs3311.team09.analyses.HealthCareMortality;
import ca.yorku.eecs3311.team09.analyses.HealthExpenditureHospitalBeds;
import ca.yorku.eecs3311.team09.analyses.IAnalysis;
import ca.yorku.eecs3311.team09.analyses.LazyAnalysis;
import ca.yorku.eecs3311.team09.analyses.RatioAnalysis;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;
import ca.yorku.eecs3311.team09.exceptions.IncompatibleAnalysisException;
import ca.yorku.eecs3311.team09.plots.BarPlot;
import ca.yorku.eecs3311.team09.plots.LinePlot;
import ca.yorku.eecs3311.team09.plots.PiePlot;
import ca.yorku.eecs3311.team09.plots.Plot;
import ca.yorku.eecs3311.team09.plots.ReportPlot;
import ca.yorku.eecs3311.team09.plots.ScatterPlot;
import ca.yorku.eecs3311.team09.plots.designer.DefaultPlotDesigner;
import ca.yorku.eecs3311.team09.plots.designer.PlotDesigner;

public class PlotTest {

	private static final PlotDesigner designer = DefaultPlotDesigner.getInstance();

	@Test(expected = IncompatibleAnalysisException.class)
	public void PlotTestLine01() {
		Plot linePlot = new LinePlot(designer);
		IAnalysis analysis = new MyForestArea(Country.CANADA, 0, 0);

		analysis.accept(linePlot);
	}

	@Test(expected = IncompatibleAnalysisException.class)
	public void PlotTestLine02() {
		Plot linePlot = new LinePlot(designer);
		IAnalysis analysis = new MyGovernmentExpenditure(Country.CANADA, 0, 0);

		analysis.accept(linePlot);
	}

	@Test
	public void PlotTestLine03() {
		Plot linePlot = new LinePlot(designer);
		AnnualPercentChangeAnalysis analysis = new MyAirPollutionForestArea(Country.CANADA, 0, 0);
		analysis.accept(linePlot);

		Map<Indicator, Map<Integer, Double>> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(linePlot);

		checkDatasetsACP(analysisResult, dataset);
	}

	@Test
	public void PlotTestLine04() {
		Plot linePlot = new LinePlot(designer);
		RatioAnalysis analysis = new MyCO2GDP(Country.CANADA, 0, 0);
		analysis.accept(linePlot);

		Map<Integer, Double> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(linePlot);

		checkDatasetsRatio(analysisResult, dataset);
	}

	@Test
	public void PlotTestLine05() {
		Plot linePlot = new LinePlot(designer);
		AnnualPercentChangeAnalysis analysis = new MyCO2EnergyUseAirPollution(Country.CANADA, 0, 0);
		analysis.accept(linePlot);

		Map<Indicator, Map<Integer, Double>> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(linePlot);

		checkDatasetsACP(analysisResult, dataset);
	}

	@Test
	public void PlotTestLine06() {
		Plot linePlot = new LinePlot(designer);
		AnnualPercentChangeAnalysis analysis = new MyGovEducationHealthExpenditure(Country.CANADA, 0, 0);
		analysis.accept(linePlot);

		Map<Indicator, Map<Integer, Double>> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(linePlot);

		checkDatasetsACP(analysisResult, dataset);
	}

	@Test
	public void PlotTestLine07() {
		Plot linePlot = new LinePlot(designer);
		LazyAnalysis analysis = new MyHealthCareMortality(Country.CANADA, 0, 0);
		analysis.accept(linePlot);

		Map<Indicator, Map<Integer, Double>> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(linePlot);

		checkDatasetsACP(analysisResult, dataset);
	}

	@Test
	public void PlotTestLine08() {
		Plot linePlot = new LinePlot(designer);
		RatioAnalysis analysis = new MyHealthExpenditureHospitalBeds(Country.CANADA, 0, 0);
		analysis.accept(linePlot);

		Map<Integer, Double> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(linePlot);

		checkDatasetsRatio(analysisResult, dataset);
	}

	@Test(expected = IncompatibleAnalysisException.class)
	public void PlotTestBar01() {
		Plot barPlot = new BarPlot(designer);
		IAnalysis analysis = new MyForestArea(Country.CANADA, 0, 0);

		analysis.accept(barPlot);
	}

	@Test(expected = IncompatibleAnalysisException.class)
	public void PlotTestBar02() {
		Plot barPlot = new BarPlot(designer);
		IAnalysis analysis = new MyGovernmentExpenditure(Country.CANADA, 0, 0);

		analysis.accept(barPlot);
	}

	@Test
	public void PlotTestBar03() {
		Plot barPlot = new BarPlot(designer);
		AnnualPercentChangeAnalysis analysis = new MyAirPollutionForestArea(Country.CANADA, 0, 0);
		analysis.accept(barPlot);

		Map<Indicator, Map<Integer, Double>> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(barPlot);

		checkDatasetsACP(analysisResult, dataset);
	}

	@Test
	public void PlotTestBar04() {
		Plot barPlot = new BarPlot(designer);
		RatioAnalysis analysis = new MyCO2GDP(Country.CANADA, 0, 0);
		analysis.accept(barPlot);

		Map<Integer, Double> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(barPlot);

		checkDatasetsRatio(analysisResult, dataset);
	}

	@Test
	public void PlotTestBar05() {
		Plot barPlot = new BarPlot(designer);
		AnnualPercentChangeAnalysis analysis = new MyCO2EnergyUseAirPollution(Country.CANADA, 0, 0);
		analysis.accept(barPlot);

		Map<Indicator, Map<Integer, Double>> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(barPlot);

		checkDatasetsACP(analysisResult, dataset);
	}

	@Test
	public void PlotTestBar06() {
		Plot barPlot = new BarPlot(designer);
		AnnualPercentChangeAnalysis analysis = new MyGovEducationHealthExpenditure(Country.CANADA, 0, 0);
		analysis.accept(barPlot);

		Map<Indicator, Map<Integer, Double>> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(barPlot);

		checkDatasetsACP(analysisResult, dataset);
	}

	@Test
	public void PlotTestBar07() {
		Plot barPlot = new BarPlot(designer);
		LazyAnalysis analysis = new MyHealthCareMortality(Country.CANADA, 0, 0);
		analysis.accept(barPlot);

		Map<Indicator, Map<Integer, Double>> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(barPlot);

		checkDatasetsACP(analysisResult, dataset);
	}

	@Test
	public void PlotTestBar08() {
		Plot barPlot = new BarPlot(designer);
		RatioAnalysis analysis = new MyHealthExpenditureHospitalBeds(Country.CANADA, 0, 0);
		analysis.accept(barPlot);

		Map<Integer, Double> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(barPlot);

		checkDatasetsRatio(analysisResult, dataset);
	}

	@Test(expected = IncompatibleAnalysisException.class)
	public void PlotTestScatter01() {
		Plot scatterPlot = new ScatterPlot(designer);
		IAnalysis analysis = new MyForestArea(Country.CANADA, 0, 0);

		analysis.accept(scatterPlot);
	}

	@Test(expected = IncompatibleAnalysisException.class)
	public void PlotTestScatter02() {
		Plot scatterPlot = new ScatterPlot(designer);
		IAnalysis analysis = new MyGovernmentExpenditure(Country.CANADA, 0, 0);

		analysis.accept(scatterPlot);
	}

	@Test
	public void PlotTestScatter03() {
		Plot scatterPlot = new ScatterPlot(designer);
		AnnualPercentChangeAnalysis analysis = new MyAirPollutionForestArea(Country.CANADA, 0, 0);
		analysis.accept(scatterPlot);

		Map<Indicator, Map<Integer, Double>> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(scatterPlot);

		checkDatasetsACP(analysisResult, dataset);
	}

	@Test
	public void PlotTestScatter04() {
		Plot scatterPlot = new ScatterPlot(designer);
		RatioAnalysis analysis = new MyCO2GDP(Country.CANADA, 0, 0);
		analysis.accept(scatterPlot);

		Map<Integer, Double> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(scatterPlot);

		checkDatasetsRatio(analysisResult, dataset);
	}

	@Test
	public void PlotTestScatter05() {
		Plot scatterPlot = new ScatterPlot(designer);
		AnnualPercentChangeAnalysis analysis = new MyCO2EnergyUseAirPollution(Country.CANADA, 0, 0);
		analysis.accept(scatterPlot);

		Map<Indicator, Map<Integer, Double>> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(scatterPlot);

		checkDatasetsACP(analysisResult, dataset);
	}

	@Test
	public void PlotTestScatter06() {
		Plot scatterPlot = new ScatterPlot(designer);
		AnnualPercentChangeAnalysis analysis = new MyGovEducationHealthExpenditure(Country.CANADA, 0, 0);
		analysis.accept(scatterPlot);

		Map<Indicator, Map<Integer, Double>> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(scatterPlot);

		checkDatasetsACP(analysisResult, dataset);
	}

	@Test
	public void PlotTestScatter07() {
		Plot scatterPlot = new ScatterPlot(designer);
		LazyAnalysis analysis = new MyHealthCareMortality(Country.CANADA, 0, 0);
		analysis.accept(scatterPlot);

		Map<Indicator, Map<Integer, Double>> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(scatterPlot);

		checkDatasetsACP(analysisResult, dataset);
	}

	@Test
	public void PlotTestScatter08() {
		Plot scatterPlot = new ScatterPlot(designer);
		RatioAnalysis analysis = new MyHealthExpenditureHospitalBeds(Country.CANADA, 0, 0);
		analysis.accept(scatterPlot);

		Map<Integer, Double> analysisResult = analysis.getResult();
		XYDataset dataset = getXYDataset(scatterPlot);

		checkDatasetsRatio(analysisResult, dataset);
	}

	@Test
	public void PlotTestPie01() {
		Plot pieplot = new PiePlot(designer);
		AverageAnalysis analysis = new MyForestArea(Country.CANADA, 0, 0);
		analysis.accept(pieplot);

		PieDataset<Double> dataset = getDataset(pieplot);

		checkDatasetsAvg(45d, dataset);
	}

	@Test(expected = IncompatibleAnalysisException.class)
	public void PlotTestPie02() {
		Plot piePlot = new PiePlot(designer);
		IAnalysis analysis = new MyCO2GDP(Country.CANADA, 0, 0);

		analysis.accept(piePlot);
	}

	@Test(expected = IncompatibleAnalysisException.class)
	public void PlotTestPie03() {
		Plot piePlot = new PiePlot(designer);
		IAnalysis analysis = new MyAirPollutionForestArea(Country.CANADA, 0, 0);

		analysis.accept(piePlot);
	}

	@Test(expected = IncompatibleAnalysisException.class)
	public void PlotTestPie04() {
		Plot piePlot = new PiePlot(designer);
		IAnalysis analysis = new MyCO2EnergyUseAirPollution(Country.CANADA, 0, 0);

		analysis.accept(piePlot);
	}

	@Test(expected = IncompatibleAnalysisException.class)
	public void PlotTestPie05() {
		Plot piePlot = new PiePlot(designer);
		IAnalysis analysis = new MyGovEducationHealthExpenditure(Country.CANADA, 0, 0);

		analysis.accept(piePlot);
	}

	@Test(expected = IncompatibleAnalysisException.class)
	public void PlotTestPie06() {
		Plot piePlot = new PiePlot(designer);
		IAnalysis analysis = new MyHealthCareMortality(Country.CANADA, 0, 0);

		analysis.accept(piePlot);
	}

	@Test(expected = IncompatibleAnalysisException.class)
	public void PlotTestPie07() {
		Plot piePlot = new PiePlot(designer);
		IAnalysis analysis = new MyHealthExpenditureHospitalBeds(Country.CANADA, 0, 0);

		analysis.accept(piePlot);
	}

	@Test
	public void PlotTestPie08() {
		Plot pieplot = new PiePlot(designer);
		AverageAnalysis analysis = new MyGovernmentExpenditure(Country.CANADA, 0, 0);
		analysis.accept(pieplot);

		PieDataset<Double> dataset = getDataset(pieplot);

		checkDatasetsAvg(6.0, dataset);
	}

	@Test
	public void PlotTestReport01() {
		Plot reportPlot = new ReportPlot(designer);
		AnnualPercentChangeAnalysis analysis = new MyAirPollutionForestArea(Country.CANADA, 2010, 2013);
		analysis.accept(reportPlot);

		JScrollPane panel = (JScrollPane) reportPlot.getPlot();
		JViewport viewPort = (JViewport) panel.getComponent(0);
		JTextArea textArea = (JTextArea) viewPort.getComponent(0);

		String expected = "Air pollution vs forest area % change (Canada) [2010 - 2013]"
				+ "============================================================" + "2010:"
				+ "Forest area (% land area) => 3.30" + "Air pollution (ig/m3) => 3.30" + "2011:"
				+ "Forest area (% land area) => 4.00" + "Air pollution (ig/m3) => 4.00" + "2012:"
				+ "Forest area (% land area) => 5.00" + "Air pollution (ig/m3) => 5.00" + "2013:"
				+ "Forest area (% land area) => 6.00" + "Air pollution (ig/m3) => 6.00";

		String actual = textArea.getText().replaceAll("[\t\r\n]", "");

		Assert.assertEquals("", expected.length(), actual.length());
	}

	@Test
	public void PlotTestReport02() {
		Plot reportPlot = new ReportPlot(designer);
		AverageAnalysis analysis = new MyGovernmentExpenditure(Country.CANADA, 2010, 2013);
		analysis.accept(reportPlot);

		JScrollPane panel = (JScrollPane) reportPlot.getPlot();
		JViewport viewPort = (JViewport) panel.getComponent(0);
		JTextArea textArea = (JTextArea) viewPort.getComponent(0);

		String expected = "Average education expenditure (Canada) [2010 - 2013]"
				+ "====================================================" + "Education expenditure (% of GDP) => 6.00";

		String actual = textArea.getText().replaceAll("[\t\r\n]", "");

		Assert.assertEquals("", expected.length(), actual.length());
	}

	@Test
	public void PlotTestReport03() {
		Plot reportPlot = new ReportPlot(designer);
		AnnualPercentChangeAnalysis analysis = new MyCO2EnergyUseAirPollution(Country.CANADA, 2010, 2013);
		analysis.accept(reportPlot);

		JScrollPane panel = (JScrollPane) reportPlot.getPlot();
		JViewport viewPort = (JViewport) panel.getComponent(0);
		JTextArea textArea = (JTextArea) viewPort.getComponent(0);

		String expected = ("CO2 vs energy use & air pollution % change (Canada) [2010 - 2013]\r\n"
				+ "=================================================================\r\n" + "2010:\r\n"
				+ "	Energy use kg/capita => 1.70\r\n" + "	CO2 emissions t/capita) => 6.70\r\n"
				+ "	Air pollution (ig/m3) => 3.30\r\n" + "2011:\r\n" + "	Energy use kg/capita => 7.40\r\n"
				+ "	CO2 emissions t/capita) => 3.40\r\n" + "	Air pollution (ig/m3) => 4.00\r\n" + "2012:\r\n"
				+ "	Energy use kg/capita => 4.00\r\n" + "	CO2 emissions t/capita) => 1.00\r\n"
				+ "	Air pollution (ig/m3) => 5.00\r\n" + "2013:\r\n" + "	Energy use kg/capita => 6.90\r\n"
				+ "	CO2 emissions t/capita) => 1.90\r\n" + "	Air pollution (ig/m3) => 6.00")
				.replaceAll("[\t\r\n]", "");
		String actual = textArea.getText().replaceAll("[\t\r\n]", "");

		Assert.assertEquals("", expected.length(), actual.length());
	}

	@Test
	public void PlotTestReport04() {
		Plot reportPlot = new ReportPlot(designer);
		RatioAnalysis analysis = new MyCO2GDP(Country.CANADA, 2010, 2013);
		analysis.accept(reportPlot);

		JScrollPane panel = (JScrollPane) reportPlot.getPlot();
		JViewport viewPort = (JViewport) panel.getComponent(0);
		JTextArea textArea = (JTextArea) viewPort.getComponent(0);

		String expected = "GDP to CO2 ratio (Canada) [2010 - 2013]" + "======================================="
				+ "2010:" + "ratio => 3.30" + "2011:" + "ratio => 5.00" + "2012:" + "ratio => 5.00" + "2013:"
				+ "ratio => 6.00";
		String actual = textArea.getText().replaceAll("[\t\r\n]", "");

		Assert.assertEquals("", expected.length(), actual.length());
	}

	@Test
	public void PlotTestReport05() {
		Plot reportPlot = new ReportPlot(designer);
		AverageAnalysis analysis = new MyForestArea(Country.CANADA, 2010, 2013);
		analysis.accept(reportPlot);

		JScrollPane panel = (JScrollPane) reportPlot.getPlot();
		JViewport viewPort = (JViewport) panel.getComponent(0);
		JTextArea textArea = (JTextArea) viewPort.getComponent(0);

		String expected = ("Average forest area (Canada) [2010 - 2013]\r\n"
				+ "==========================================\r\n" + "	Forest area (% land area) => 45.00")
				.replaceAll("[\t\r\n]", "");
		String actual = textArea.getText().replaceAll("[\t\r\n]", "");

		Assert.assertEquals("", expected.length(), actual.length());
	}

	@Test
	public void PlotTestReport06() {
		Plot reportPlot = new ReportPlot(designer);
		LazyAnalysis analysis = new MyHealthCareMortality(Country.CANADA, 2010, 2013);
		analysis.accept(reportPlot);

		JScrollPane panel = (JScrollPane) reportPlot.getPlot();
		JViewport viewPort = (JViewport) panel.getComponent(0);
		JTextArea textArea = (JTextArea) viewPort.getComponent(0);

		String expected = ("Health care vs mortality rate (Canada) [2010 - 2013]\r\n"
				+ "====================================================\r\n" + "2010:\r\n"
				+ "	Problems accessing health care (% of women) => 3.30\r\n"
				+ "	Infant Mortality rate/1000 => 1.70\r\n" + "2011:\r\n"
				+ "	Problems accessing health care (% of women) => 4.00\r\n"
				+ "	Infant Mortality rate/1000 => 7.40\r\n" + "2012:\r\n"
				+ "	Problems accessing health care (% of women) => 5.00\r\n"
				+ "	Infant Mortality rate/1000 => 4.00\r\n" + "2013:\r\n"
				+ "	Problems accessing health care (% of women) => 6.00\r\n" + "	Infant Mortality rate/1000 => 6.90")
				.replaceAll("[\t\r\n]", "");
		String actual = textArea.getText().replaceAll("[\t\r\n]", "");

		Assert.assertEquals("", expected.length(), actual.length());
	}

	@Test
	public void PlotTestReport07() {
		Plot reportPlot = new ReportPlot(designer);
		RatioAnalysis analysis = new MyHealthExpenditureHospitalBeds(Country.CANADA, 2010, 2013);
		analysis.accept(reportPlot);

		JScrollPane panel = (JScrollPane) reportPlot.getPlot();
		JViewport viewPort = (JViewport) panel.getComponent(0);
		JTextArea textArea = (JTextArea) viewPort.getComponent(0);

		String expected = ("Health expenditure to hospital beds ratio (Canada) [2010 - 2013]\r\n"
				+ "================================================================\r\n" + "2010:\r\n"
				+ "	ratio => 3.30\r\n" + "2011:\r\n" + "	ratio => 5.00\r\n" + "2012:\r\n" + "	ratio => 5.00\r\n"
				+ "2013:\r\n" + "	ratio => 6.00").replaceAll("[\t\r\n]", "");
		String actual = textArea.getText().replaceAll("[\t\r\n]", "");

		Assert.assertEquals("", expected.length(), actual.length());
	}

	@Test
	public void PlotTestReport08() {
		Plot reportPlot = new ReportPlot(designer);
		AnnualPercentChangeAnalysis analysis = new MyGovEducationHealthExpenditure(Country.CANADA, 2010, 2013);
		analysis.accept(reportPlot);

		JScrollPane panel = (JScrollPane) reportPlot.getPlot();
		JViewport viewPort = (JViewport) panel.getComponent(0);
		JTextArea textArea = (JTextArea) viewPort.getComponent(0);

		String expected = ("Education vs health expenditure % change (Canada) [2010 - 2013]\r\n"
				+ "===============================================================\r\n" + "2010:\r\n"
				+ "	Education expenditure (% of GDP) => 3.30\r\n" + "	Health expenditure (% of GDP) => 1.70\r\n"
				+ "2011:\r\n" + "	Education expenditure (% of GDP) => 4.00\r\n"
				+ "	Health expenditure (% of GDP) => 7.40\r\n" + "2012:\r\n"
				+ "	Education expenditure (% of GDP) => 5.00\r\n" + "	Health expenditure (% of GDP) => 4.00\r\n"
				+ "2013:\r\n" + "	Education expenditure (% of GDP) => 6.00\r\n"
				+ "	Health expenditure (% of GDP) => 6.90").replaceAll("[\t\r\n]", "");
		String actual = textArea.getText().replaceAll("[\t\r\n]", "");

		Assert.assertEquals("", expected.length(), actual.length());
	}

	private void checkDatasetsRatio(Map<Integer, Double> analysisResult, XYDataset dataset) {
		int yearIdx = 0;
		for (Integer year : analysisResult.keySet()) {
			Double value = dataset.getYValue(0, yearIdx);
			Assert.assertEquals("Incorrect value inserted into dataset", (Double) analysisResult.get(year), value);
			yearIdx++;
		}
	}

	private void checkDatasetsACP(Map<Indicator, Map<Integer, Double>> analysisResult, XYDataset dataset) {
		for (Indicator indicator : analysisResult.keySet()) {
			Map<Integer, Double> column = analysisResult.get(indicator);

			int indicatorIdx = dataset.indexOf(indicator.toString());
			int yearIdx = 0;

			for (Integer year : column.keySet()) {
				Double value = dataset.getYValue(indicatorIdx, yearIdx);
				Assert.assertEquals("Incorrect value inserted into dataset", (Double) column.get(year), value);
				yearIdx++;
			}
		}
	}

	private void checkDatasetsAvg(Double value, PieDataset<Double> dataset) {
		Assert.assertEquals("Incorrect value inserted into dataset", value, dataset.getValue(0));
	}

	private XYDataset getXYDataset(Plot plot) {
		ChartPanel panel = (ChartPanel) plot.getPlot();
		XYPlot chartPlot = panel.getChart().getXYPlot();

		return chartPlot.getDataset();
	}

	private PieDataset<Double> getDataset(Plot plot) {
		ChartPanel panel = (ChartPanel) plot.getPlot();
		@SuppressWarnings("unchecked")
		org.jfree.chart.plot.PiePlot<Double> chartPlot = (org.jfree.chart.plot.PiePlot<Double>) panel.getChart()
				.getPlot();

		return chartPlot.getDataset();
	}

	private static class MyForestArea extends ForestArea {
		public MyForestArea(Country country, Integer from, Integer to) {
			super(country, from, to);
		}

		@Override
		public void performCalculation() {
			this.result = new HashMap<>();

			result.put(Indicator.FOREST_AREA, 45d);
		}
	}

	private static class MyAirPollutionForestArea extends AirPollutionForestArea {
		public MyAirPollutionForestArea(Country country, Integer from, Integer to) {
			super(country, from, to);
		}

		@Override
		public void performCalculation() {
			this.result = new HashMap<Indicator, Map<Integer, Double>>();

			Map<Integer, Double> first = new HashMap<>();

			Map<Integer, Double> second = new HashMap<>();

			first.put(2010, 3.3);
			first.put(2011, 4.0);
			first.put(2012, 5.0);
			first.put(2013, 6.0);

			second.put(2010, 3.3);
			second.put(2011, 4.0);
			second.put(2012, 5.0);
			second.put(2013, 6.0);

			result.put(Indicator.AIR_POLLUTION_MEAN, first);

			result.put(Indicator.FOREST_AREA, second);

		}
	}

	private static class MyCO2EnergyUseAirPollution extends CO2EnergyUseAirPollution {
		public MyCO2EnergyUseAirPollution(Country country, Integer from, Integer to) {
			super(country, from, to);
		}

		@Override
		public void performCalculation() {

			this.result = new HashMap<Indicator, Map<Integer, Double>>();

			Map<Integer, Double> first = new HashMap<Integer, Double>();

			Map<Integer, Double> second = new HashMap<Integer, Double>();

			Map<Integer, Double> third = new HashMap<Integer, Double>();

			first.put(2010, 3.3);
			first.put(2011, 4.0);
			first.put(2012, 5.0);
			first.put(2013, 6.0);

			second.put(2010, 1.7);
			second.put(2011, 7.4);
			second.put(2012, 4.0);
			second.put(2013, 6.9);

			third.put(2010, 6.7);
			third.put(2011, 3.4);
			third.put(2012, 1.0);
			third.put(2013, 1.9);

			this.result.put(Indicator.AIR_POLLUTION_MEAN, first);

			this.result.put(Indicator.ENERGY_USE, second);

			this.result.put(Indicator.CO2_EMISSIONS, third);

		}
	}

	private static class MyCO2GDP extends CO2GDP {
		public MyCO2GDP(Country country, Integer from, Integer to) {
			super(country, from, to);
		}

		@Override
		public void performCalculation() {

			this.result = new HashMap<Integer, Double>();

			this.result.put(2010, 3.3);
			this.result.put(2011, 5.0);
			this.result.put(2012, 5.0);
			this.result.put(2013, 6.0);

		}
	}

	private static class MyGovEducationHealthExpenditure extends GovEducationHealthExpenditure {
		public MyGovEducationHealthExpenditure(Country country, Integer from, Integer to) {
			super(country, from, to);
		}

		@Override
		public void performCalculation() {
			this.result = new HashMap<Indicator, Map<Integer, Double>>();

			Map<Integer, Double> first = new HashMap<>();

			Map<Integer, Double> second = new TreeMap<>();

			first.put(2010, 3.3);
			first.put(2011, 4.0);
			first.put(2012, 5.0);
			first.put(2013, 6.0);

			second.put(2010, 1.7);
			second.put(2011, 7.4);
			second.put(2012, 4.0);
			second.put(2013, 6.9);

			result.put(Indicator.GOV_EXPENDITURE_EDU_GDP, first);

			result.put(Indicator.HEALTH_EXPENDITURE_GDP, second);

		}

	}

	private static class MyGovernmentExpenditure extends GovernmentExpenditure {
		public MyGovernmentExpenditure(Country country, Integer from, Integer to) {
			super(country, from, to);
		}

		@Override
		public void performCalculation() {

			this.result = new HashMap<Indicator, Double>();

			this.result.put(Indicator.GOV_EXPENDITURE_EDU_GDP, 6.0);

		}
	}

	private static class MyHealthCareMortality extends HealthCareMortality {
		public MyHealthCareMortality(Country country, Integer from, Integer to) {
			super(country, from, to);
		}

		@Override
		public void performCalculation() {
			this.result = new HashMap<Indicator, Map<Integer, Double>>();

			Map<Integer, Double> first = new HashMap<>();

			Map<Integer, Double> second = new HashMap<>();

			first.put(2010, 3.3);
			first.put(2011, 4.0);
			first.put(2012, 5.0);
			first.put(2013, 6.0);

			second.put(2010, 1.7);
			second.put(2011, 7.4);
			second.put(2012, 4.0);
			second.put(2013, 6.9);

			result.put(Indicator.PROBLEM_ACCESSING_HC_WOMEN, first);

			result.put(Indicator.MORTALITY_RATE_INFANT, second);

		}
	}

	private static class MyHealthExpenditureHospitalBeds extends HealthExpenditureHospitalBeds {
		public MyHealthExpenditureHospitalBeds(Country country, Integer fromDate, Integer toDate) {
			super(country, fromDate, toDate);
		}

		@Override
		public void performCalculation() {
			this.result = new HashMap<Integer, Double>();

			this.result.put(2010, 3.3);
			this.result.put(2011, 5.0);
			this.result.put(2012, 5.0);
			this.result.put(2013, 6.0);

		}
	}

}