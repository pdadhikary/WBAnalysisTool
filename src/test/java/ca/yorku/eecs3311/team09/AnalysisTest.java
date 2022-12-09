package ca.yorku.eecs3311.team09;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
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
import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.analyses.visitors.PrinterAnalysisVisitor;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;
import ca.yorku.eecs3311.team09.exceptions.MissingDataException;
import ca.yorku.eecs3311.team09.exceptions.UnsupportedAnalysisException;
import ca.yorku.eecs3311.team09.helpers.StringifyAnalysisVisitor;
import ca.yorku.eecs3311.team09.plots.LinePlot;
import ca.yorku.eecs3311.team09.plots.PiePlot;
import ca.yorku.eecs3311.team09.plots.Plot;
import ca.yorku.eecs3311.team09.plots.designer.DefaultPlotDesigner;

public class AnalysisTest {
	private StringifyAnalysisVisitor stringifyVisitor;
	private PrinterAnalysisVisitor analysisVisitor;
	private Plot linePlot, piePlot;

	@Before
	public void before() {
		this.stringifyVisitor = new StringifyAnalysisVisitor();
		this.analysisVisitor = new PrinterAnalysisVisitor();

		this.linePlot = new LinePlot(DefaultPlotDesigner.getInstance());
		this.piePlot = new PiePlot(DefaultPlotDesigner.getInstance());
		String linePlotString = this.linePlot.toString();
		String piePlotString = this.linePlot.toString();
	}

	/**
	 * Test ID: AnalysisTest01
	 * <p>
	 * Category: Tests an annual percent change analysis.
	 * <p>
	 * Requirements Coverage: UC3-Analysis-APC
	 * <p>
	 * Initial Condition: toDate is greater than or equal to fromDate and the range
	 * [fromDate, toDate] is greater than one year.
	 * <p>
	 * Steps required for this test:
	 * <p>
	 * - 1. Create a new instance of the analysis.
	 * <p>
	 * - 2. Call the performCalculation() function of the analysis.
	 * <p>
	 * - 3. visit the analysis class to retrieve the result in String form.
	 * <p>
	 * Expected Outcome: Expected result matches the actual result.
	 */
	@Test
	public void AnalysisTest01() {
		// Calculate expected value
		Map<Integer, Double> row1 = new TreeMap<Integer, Double>() {
			{
				put(2005, percentChange(0.887641212003517, 0.990292909581993));
			}
		};
		Map<Integer, Double> row2 = new TreeMap<Integer, Double>() {
			{
				put(2005, percentChange(417.287588979865, 449.76585702652));
			}
		};
		Map<Integer, Double> row3 = new TreeMap<Integer, Double>() {
			{
				put(2005, percentChange(84.1648061762591, 90.3264588038878));
			}
		};
		Map<Indicator, Map<Integer, Double>> map = new HashMap<Indicator, Map<Integer, Double>>() {
			{
				put(Indicator.CO2_EMISSIONS, row1);
				put(Indicator.ENERGY_USE, row2);
				put(Indicator.AIR_POLLUTION_MEAN, row3);
			}
		};
		String expected = map.toString();
		AnalysisFactory factory = new AnalysisFactory("CO2EnergyUseAirPollution",
				"CO2 vs energy use & air pollution % change");
		AnnualPercentChangeAnalysis analysis = (AnnualPercentChangeAnalysis) factory.getAnalysis(Country.INDIA, 2001,
				2005);

		analysis.performCalculation();
		analysis.accept(stringifyVisitor);

		analysis.accept(this.linePlot);

		CO2EnergyUseAirPollution plotAnalysis = (CO2EnergyUseAirPollution) analysis;
		this.linePlot.plotAnalysis(plotAnalysis);

		getAnalysisInfo(analysis);

		String actual = stringifyVisitor.analysisStringResult;
		System.out.println("Use Case #1 " + factory.getAnalysisCode());
		assertEquals("The analysis result was incorrect", expected, actual);
	}

	@Test
	public void AnalysisTest02() {
		// Calculate expected value
		Map<Integer, Double> row1 = new TreeMap<Integer, Double>() {
			{
				put(2005, percentChange(84.1648061762591, 90.3264588038878));
			}
		};
		Map<Integer, Double> row2 = new TreeMap<Integer, Double>() {
			{
				put(2005, percentChange(22.7334950003195, 23.0538579774586));
			}
		};
		Map<Indicator, Map<Integer, Double>> map = new HashMap<Indicator, Map<Integer, Double>>() {
			{
				put(Indicator.AIR_POLLUTION_MEAN, row1);
				put(Indicator.FOREST_AREA, row2);
			}
		};
		String expected = map.toString();
		AnalysisFactory factory = new AnalysisFactory("AirPollutionForestArea",
				"Air pollution vs forest area % change");
		IAnalysis analysis = factory.getAnalysis(Country.INDIA, 2001, 2005);
		// new AirPollutionForestArea(Country.INDIA, 2001, 2005);
		analysis.performCalculation();
		analysis.accept(stringifyVisitor);

		analysis.accept(analysisVisitor);
		analysisVisitor.visitAnalysis((AirPollutionForestArea) analysis);

		AirPollutionForestArea plotAnalysis = (AirPollutionForestArea) analysis;
		analysis.accept(this.linePlot);
		this.linePlot.plotAnalysis(plotAnalysis);

		String actual = stringifyVisitor.analysisStringResult;
		String analysis_code = factory.getAnalysisCode();
		String analysis_name = factory.toString();

		getAnalysisInfo(analysis);

		System.out.println("Use Case #1 " + analysis_code + ", of name: " + analysis_name);

		System.out.println("Use Case #1 " + analysis_code + ", of name: " + analysis_name);

		assertEquals("The analysis result was incorrect", expected, actual);
	}

	/**
	 * Test ID: AnalysisTest03
	 * <p>
	 * Category: Tests a ratio analysis
	 * <p>
	 * Requirement Coverage: UC3-Analysis-Ratio
	 * <p>
	 * Initial Condition: toDate is greater than or equal to fromDate.
	 * <p>
	 * Steps required for this test:
	 * <p>
	 * - 1. Create a fetcher containing the indicator data.
	 * <p>
	 * - 2. Create a list of operations to be performed before analysis calculation.
	 * <p>
	 * - 3. Pass the data fetcher and list of operations to the
	 * IAnalysisStrategy.performCalculation() method.
	 * <p>
	 * Expected Outcome: Expected result matches the actual result.
	 */

	@Test
	public void AnalysisTest03() {
		// Calculate expected value
		Double c = 0.001;
		Map<Integer, Double> map = new TreeMap<Integer, Double>() {
			{
				put(2001, ratio(0.887013875926229, 451.572997293746));
				put(2002, ratio(0.901339541648522, 470.986786810734));
				put(2003, ratio(0.910255466269211, 546.726613494985));
			}
		};
		String expected = map.toString();

		// Run strategy algorithm
		RatioAnalysis analysis = (RatioAnalysis) new AnalysisFactory("CO2GDP", "GDP to CO2 ratio")
				.getAnalysis(Country.INDIA, 2001, 2003);
		// new AirPollutionForestArea(Country.INDIA, 2001, 2005);
		// new CO2GDP(Country.INDIA, 2001, 2003);
		analysis.performCalculation();
		analysis.accept(stringifyVisitor);

		analysis.accept(analysisVisitor);
		analysisVisitor.visitAnalysis((CO2GDP) analysis);
		analysis.accept(this.linePlot);
		CO2GDP plotAnalysis = (CO2GDP) analysis;
		this.linePlot.plotAnalysis(plotAnalysis);

		getAnalysisInfo(analysis);

		String actual = stringifyVisitor.analysisStringResult;

		assertEquals("The analysis result was incorrect", expected, actual);
	}

	@Test(expected = UnsupportedAnalysisException.class)
	public void AnalysisTest03_part_no_Analysis_error() {
		// Calculate expected value
		Double c = 0.001;
		Map<Integer, Double> map = new TreeMap<Integer, Double>() {
			{
				put(2001, ratio(0.887013875926229, 451.572997293746));
				put(2002, ratio(0.901339541648522, 470.986786810734));
				put(2003, ratio(0.910255466269211, 546.726613494985));
			}
		};
		String expected = map.toString();

		// Run strategy algorithm
		AnalysisFactory factory = new AnalysisFactory("CO2GDP_2", "GDP to CO2 ratio 2");
		RatioAnalysis analysis = (RatioAnalysis) factory.getAnalysis(Country.INDIA, 2001, 2003);
		// new AirPollutionForestArea(Country.INDIA, 2001, 2005);
		// new CO2GDP(Country.INDIA, 2001, 2003);
		analysis.performCalculation();
		analysis.accept(stringifyVisitor);

		analysis.accept(analysisVisitor);
		analysisVisitor.visitAnalysis((CO2GDP) analysis);

		analysis.accept(this.linePlot);

		CO2GDP plotAnalysis = (CO2GDP) analysis;
		this.linePlot.plotAnalysis(plotAnalysis);

		getAnalysisInfo(analysis);

		String actual = stringifyVisitor.analysisStringResult;
		String analysis_code = factory.getAnalysisCode();
		String analysis_name = factory.toString();
		System.out.println("Use Case #1 " + analysis_code + ", of name: " + analysis_name);
		assertEquals("The analysis result was incorrect", expected, actual);
	}

	@Test(expected = java.lang.RuntimeException.class)
	public void AnalysisTest03_part_null_Analysis_error() {
		// Calculate expected value
		Double c = 0.001;
		Map<Integer, Double> map = new TreeMap<Integer, Double>() {
			{
				put(2001, ratio(0.887013875926229, 451.572997293746));
				put(2002, ratio(0.901339541648522, 470.986786810734));
				put(2003, ratio(0.910255466269211, 546.726613494985));
			}
		};
		String expected = map.toString();

		// Run strategy algorithm
		AnalysisFactory factory = new AnalysisFactory(null, "GDP to CO2 ratio 2");
		RatioAnalysis analysis = (RatioAnalysis) factory.getAnalysis(Country.INDIA, 2001, 2003);
		// new AirPollutionForestArea(Country.INDIA, 2001, 2005);
		// new CO2GDP(Country.INDIA, 2001, 2003);
		analysis.performCalculation();
		analysis.accept(stringifyVisitor);

		analysis.accept(analysisVisitor);
		analysisVisitor.visitAnalysis((CO2GDP) analysis);
		analysis.accept(this.linePlot);
		CO2GDP plotAnalysis = (CO2GDP) analysis;
		this.linePlot.plotAnalysis(plotAnalysis);

		getAnalysisInfo(analysis);

		String actual = stringifyVisitor.analysisStringResult;
		String analysis_code = factory.getAnalysisCode();
		String analysis_name = factory.toString();
		System.out.println("Use Case #1 " + analysis_code + ", of name: " + analysis_name);
		assertEquals("The analysis result was incorrect", expected, actual);
	}

	/**
	 * Test ID: AnalysisTest04
	 * <p>
	 * Category: Tests an average analysis.
	 * <p>
	 * Requirement Coverage: UC3-Analysis-Average.
	 * <p>
	 * Initial Condition: toDate is greater than or equal to fromDate.
	 * <p>
	 * Steps required for this test:
	 * <p>
	 * - 1. Create a new instance of the analysis.
	 * <p>
	 * - 2. Call the performCalculation() function of the analysis.
	 * <p>
	 * - 3. visit the analysis class to retrieve the result in String form.
	 * <p>
	 * Expected Outcome: Expected result matches the actual result.
	 */
	@Test
	public void AnalysisTest04() {
		// Calculate expected value
		Map<Indicator, Double> map = new TreeMap<Indicator, Double>() {
			{
				put(Indicator.FOREST_AREA, average(22.7975675957473, 22.8616401911751, 22.9257127866029));
			}
		};
		String expected = map.toString();

		// Run strategy algorithm
		AverageAnalysis analysis = (AverageAnalysis) new AnalysisFactory("ForestArea", "Average forest area")
				.getAnalysis(Country.INDIA, 2001, 2003);

		// AverageAnalysis analysis = new ForestArea(Country.INDIA, 2001, 2003);
		analysis.performCalculation();
		analysis.accept(stringifyVisitor);

		analysis.accept(analysisVisitor);
		analysisVisitor.visitAnalysis((ForestArea) analysis);
		analysis.accept(this.piePlot);
		ForestArea plotAnalysis = (ForestArea) analysis;
		this.piePlot.plotAnalysis(plotAnalysis);

		getAnalysisInfo(analysis);

		String actual = stringifyVisitor.analysisStringResult;

		assertEquals("The analysis result was incorrect", expected, actual);
	}

	@Test
	public void AnalysisTest05() {
		// Calculate expected value
		Map<Indicator, Double> map = new TreeMap<Indicator, Double>() {
			{
				put(Indicator.GOV_EXPENDITURE_EDU_GDP, average(3.61340999603271));
			}
		};
		String expected = map.toString();

		// Run strategy algorithm

		IAnalysis analysis = new AnalysisFactory("GovernmentExpenditure", "Average education expenditure")
				.getAnalysis(Country.INDIA, 2001, 2003);
		analysis.performCalculation();
		analysis.accept(stringifyVisitor);

		analysis.accept(analysisVisitor);
		analysisVisitor.visitAnalysis((GovernmentExpenditure) analysis);

		analysis.accept(this.piePlot);

		GovernmentExpenditure plotAnalysis = (GovernmentExpenditure) analysis;
		this.piePlot.plotAnalysis(plotAnalysis);

		getAnalysisInfo(analysis);

		String actual = stringifyVisitor.analysisStringResult;

		assertEquals("The analysis result was incorrect", expected, actual);
	}

	@Test
	public void AnalysisTest06() {
		// Calculate expected value

		Map<Integer, Double> map = new TreeMap<Integer, Double>() {
			{
				put(2001, ratio(0.019797677990000002, 0.64));
				put(2002, ratio(0.02023548126, 0.63));
				put(2003, ratio(0.021997798920000002, 0.8999999762));
			}
		};
		String expected = map.toString();

		// Run strategy algorithm
		IAnalysis analysis = new AnalysisFactory("HealthExpenditureHospitalBeds",
				"Health expenditure to hospital beds ratio").getAnalysis(Country.INDIA, 2001, 2003);
		analysis.performCalculation();
		analysis.accept(stringifyVisitor);

		analysis.accept(analysisVisitor);
		analysisVisitor.visitAnalysis((HealthExpenditureHospitalBeds) analysis);

		analysis.accept(this.linePlot);

		HealthExpenditureHospitalBeds plotAnalysis = (HealthExpenditureHospitalBeds) analysis;
		this.linePlot.plotAnalysis(plotAnalysis);
		getAnalysisInfo(analysis);

		String actual = stringifyVisitor.analysisStringResult;

		assertEquals("The analysis result was incorrect", expected, actual);
	}

	@Test(expected = MissingDataException.class)
	public void AnalysisTest07() {
		// Calculate expected value
		Map<Integer, Double> row1 = new TreeMap<Integer, Double>() {
			{
				put(2001, percentChange(3.3, 3.4));
				put(2002, percentChange(3.4, 4.1));
				put(2003, percentChange(4.1, 6.2));
			}
		};
		Map<Integer, Double> row2 = new TreeMap<Integer, Double>() {
			{
				put(2001, percentChange(1.4, 1.4));
				put(2002, percentChange(1.4, 2.1));
				put(2003, percentChange(2.1, 3.5));
			}
		};
		Map<Indicator, Map<Integer, Double>> map = new HashMap<Indicator, Map<Integer, Double>>() {
			{
				put(Indicator.PROBLEM_ACCESSING_HC_WOMEN, row1);
				put(Indicator.MORTALITY_RATE_INFANT, row2);
			}
		};
		String expected = map.toString();

		LazyAnalysis analysis = (LazyAnalysis) new AnalysisFactory("HealthCareMortality",
				"Health care vs mortality rate").getAnalysis(Country.INDIA, 2001, 2003);
		analysis.performCalculation();
		analysis.accept(stringifyVisitor);

		analysis.accept(analysisVisitor);
		analysisVisitor.visitAnalysis((HealthCareMortality) analysis);

		getAnalysisInfo(analysis);
		analysis.accept(this.linePlot);

		HealthCareMortality plotAnalysis = (HealthCareMortality) analysis;
		this.linePlot.plotAnalysis(plotAnalysis);

		String actual = stringifyVisitor.analysisStringResult;

		assertEquals("The analysis result was incorrect", expected, actual);
	}

	@Test
	public void AnalysisTest07Part2() {
		// Calculate expected value
		Map<Integer, Double> row1 = new TreeMap<Integer, Double>() {
			{
				put(2006, 34.8);
				put(2016, 44.4);
			}
		};
		Map<Integer, Double> row2 = new TreeMap<Integer, Double>() {
			{
				put(2006, 53.6);
				put(2016, 33.1);
			}
		};
		Map<Indicator, Map<Integer, Double>> map = new HashMap<Indicator, Map<Integer, Double>>() {
			{
				put(Indicator.PROBLEM_ACCESSING_HC_WOMEN, row1);
				put(Indicator.MORTALITY_RATE_INFANT, row2);
			}
		};
		String expected = map.toString();

		IAnalysis analysis = new AnalysisFactory("HealthCareMortality", "Health care vs mortality rate")
				.getAnalysis(Country.INDIA, 2006, 2016);
		analysis.performCalculation();
		analysis.accept(stringifyVisitor);
		analysis.accept(analysisVisitor);

		analysis.accept(this.linePlot);

		analysisVisitor.visitAnalysis((LazyAnalysis) analysis);

		String actual = stringifyVisitor.analysisStringResult;

		getAnalysisInfo(analysis);

		HealthCareMortality plotAnalysis = (HealthCareMortality) analysis;

		this.linePlot.plotAnalysis(plotAnalysis);

		assertEquals("The analysis result was incorrect", expected, actual);
	}

	@Test
	public void AnalysisTest08() {
		// Calculate expected value
		Map<Integer, Double> row1 = new TreeMap<Integer, Double>() {
			{
				put(2003, percentChange(4.32479000091553, 3.61340999603271));
			}
		};
		Map<Integer, Double> row2 = new TreeMap<Integer, Double>() {
			{
				put(2003, percentChange(4.03493214, 4.0084796));
			}
		};
		Map<Indicator, Map<Integer, Double>> map = new HashMap<Indicator, Map<Integer, Double>>() {
			{
				put(Indicator.GOV_EXPENDITURE_EDU_GDP, row1);
				put(Indicator.HEALTH_EXPENDITURE_GDP, row2);
			}
		};
		String expected = map.toString();

		IAnalysis analysis = new AnalysisFactory("GovEducationHealthExpenditure",
				"Education vs health expenditure % change").getAnalysis(Country.INDIA, 2001, 2003);
		analysis.performCalculation();
		analysis.accept(stringifyVisitor);
		String actual = stringifyVisitor.analysisStringResult;

		analysis.accept(analysisVisitor);
		analysis.accept(this.linePlot);

		analysisVisitor.visitAnalysis((GovEducationHealthExpenditure) analysis);

		getAnalysisInfo(analysis);

		GovEducationHealthExpenditure plotAnalysis = (GovEducationHealthExpenditure) analysis;
		this.linePlot.plotAnalysis(plotAnalysis);

		assertEquals("The analysis result was incorrect", expected, actual);
	}

	public void getAnalysisInfo(IAnalysis analysis) {
		Country country = analysis.getCountry();
		int from_date = analysis.getFromDate();
		int to_date = analysis.getToDate();
	}

	private Double percentChange(Double a, Double b) {
		return (b / a - 1.0d) * 100.0d;
	}

	private Double ratio(Double a, Double b) {
		return a / b;
	}

	private Double average(Double... numbers) {
		Double sum = 0.0;
		for (Double number : numbers) {
			sum += number;
		}
		return sum / (double) numbers.length;
	}

}