package ca.yorku.eecs3311.team09;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.yorku.eecs3311.team09.analyses.HealthExpenditureHospitalBeds;
import ca.yorku.eecs3311.team09.analyses.IAnalysis;
import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.models.PlotsModel;
import ca.yorku.eecs3311.team09.plots.LinePlot;
import ca.yorku.eecs3311.team09.plots.designer.DefaultPlotDesigner;
import ca.yorku.eecs3311.team09.plots.designer.PlotDesigner;
import ca.yorku.eecs3311.team09.plots.factory.PlotFactory;

public class PlotsModelTest {

	@Test
	public void PlotModelTest01_add() {
		PlotsModel pm = new PlotsModel();
		PlotsModel pm2 = new PlotsModel();
		Country country = Country.CANADA;
		Integer from = 2001;
		Integer to = 2003;

		IAnalysis analysis = new HealthExpenditureHospitalBeds(country, from, to);
		PlotDesigner designer = DefaultPlotDesigner.getInstance();

		AnalysisFactory analysisFactory = new AnalysisFactory("HealthExpenditureHospitalBeds", "My analysis");
		PlotFactory pf = new PlotFactory("Line", "my line plot");
		LinePlot lp = new LinePlot(designer);
		analysis.accept(lp);
		String plotId = pm.generateUUID();
		pm.addPlot(0, plotId, analysisFactory, pf);

		pm2.addPlot(0, plotId, analysisFactory, pf);
		pm2.addPlot(1, plotId, analysisFactory, pf);

		assertNotEquals(pm, pm2);

	}

	@Test
	public void PlotModelTest02_remove() {
		PlotsModel pm = new PlotsModel();
		PlotsModel pm2 = new PlotsModel();
		Country country = Country.CANADA;
		Integer from = 2001;
		Integer to = 2003;

		IAnalysis analysis = new HealthExpenditureHospitalBeds(country, from, to);
		PlotDesigner designer = DefaultPlotDesigner.getInstance();

		AnalysisFactory analysisFactory = new AnalysisFactory("HealthExpenditureHospitalBeds", "My analysis");
		PlotFactory pf = new PlotFactory("Line", "my line plot");
		LinePlot lp = new LinePlot(designer);
		analysis.accept(lp);
		String plotId = pm.generateUUID();
		String plotId2 = pm.generateUUID();
		pm.addPlot(0, plotId, analysisFactory, pf);

		pm2.addPlot(0, plotId, analysisFactory, pf);
		pm2.addPlot(1, plotId2, analysisFactory, pf);
		pm2.removePlot(plotId);

		assertTrue(pm2.getPlotIndex(plotId2) == 0);

	}

	// duplicate cases not always expected
	@Test
	public void PlotModelTest03_removeDuplicates() {
		PlotsModel pm = new PlotsModel();
		PlotsModel pm2 = new PlotsModel();
		Country country = Country.CANADA;
		Integer from = 2001;
		Integer to = 2003;

		IAnalysis analysis = new HealthExpenditureHospitalBeds(country, from, to);
		PlotDesigner designer = DefaultPlotDesigner.getInstance();

		AnalysisFactory analysisFactory = new AnalysisFactory("HealthExpenditureHospitalBeds", "My analysis");
		PlotFactory pf = new PlotFactory("Line", "my line plot");
		LinePlot lp = new LinePlot(designer);
		analysis.accept(lp);
		String plotId = pm.generateUUID();
		String plotId2 = pm.generateUUID();
		pm.addPlot(0, plotId, analysisFactory, pf);

		pm2.addPlot(0, plotId, analysisFactory, pf);
		pm2.addPlot(1, plotId, analysisFactory, pf);
		pm2.addPlot(2, plotId2, analysisFactory, pf);
		pm2.removePlot(plotId);

		assertTrue(pm2.getPlotIndex(plotId2) == 1);

	}

	@Test
	public void PlotModelTest04_getPlotIndex() {
		PlotsModel pm = new PlotsModel();

		Country country = Country.CANADA;
		Integer from = 2001;
		Integer to = 2003;

		IAnalysis analysis = new HealthExpenditureHospitalBeds(country, from, to);
		PlotDesigner designer = DefaultPlotDesigner.getInstance();

		AnalysisFactory analysisFactory = new AnalysisFactory("HealthExpenditureHospitalBeds", "My analysis");
		PlotFactory pf = new PlotFactory("Line", "my line plot");
		LinePlot lp = new LinePlot(designer);
		analysis.accept(lp);
		String plotId = pm.generateUUID();
		String plotId2 = pm.generateUUID();
		String plotId3 = pm.generateUUID();
		pm.addPlot(0, plotId, analysisFactory, pf);
		pm.addPlot(1, plotId2, analysisFactory, pf);
		pm.addPlot(2, plotId3, analysisFactory, pf);

		Integer index = pm.getPlotIndex(plotId2);

		assertEquals((Integer) 1, index);

	}

	@Test
	public void PlotModelTest05_getPlotId() {
		PlotsModel pm = new PlotsModel();

		Country country = Country.CANADA;
		Integer from = 2001;
		Integer to = 2003;

		IAnalysis analysis = new HealthExpenditureHospitalBeds(country, from, to);
		PlotDesigner designer = DefaultPlotDesigner.getInstance();

		AnalysisFactory analysisFactory = new AnalysisFactory("HealthExpenditureHospitalBeds", "My analysis");
		PlotFactory pf = new PlotFactory("Line", "my line plot");
		LinePlot lp = new LinePlot(designer);
		analysis.accept(lp);
		String plotId = pm.generateUUID();
		String plotId2 = pm.generateUUID();
		String plotId3 = pm.generateUUID();
		pm.addPlot(0, plotId, analysisFactory, pf);
		pm.addPlot(1, plotId2, analysisFactory, pf);
		pm.addPlot(2, plotId3, analysisFactory, pf);

		String plotId_get = pm.getPlotId(1);

		assertEquals(plotId2, plotId_get);

	}

	@Test // gets first lowest plot index
	public void PlotModelTest06_getPlotIndex() {
		PlotsModel pm = new PlotsModel();

		Country country = Country.CANADA;
		Integer from = 2001;
		Integer to = 2003;

		IAnalysis analysis = new HealthExpenditureHospitalBeds(country, from, to);
		PlotDesigner designer = DefaultPlotDesigner.getInstance();

		AnalysisFactory analysisFactory = new AnalysisFactory("HealthExpenditureHospitalBeds", "My analysis");
		PlotFactory pf = new PlotFactory("Line", "my line plot");
		LinePlot lp = new LinePlot(designer);
		analysis.accept(lp);
		String plotId = pm.generateUUID();
		String plotId2 = pm.generateUUID();
		String plotId3 = pm.generateUUID();
		pm.addPlot(0, plotId, analysisFactory, pf);
		pm.addPlot(1, plotId2, analysisFactory, pf);
		pm.addPlot(2, plotId2, analysisFactory, pf);

		Integer index = pm.getPlotIndex(plotId2);

		assertEquals((Integer) 1, index);

	}

	@Test
	public void PlotModelTest07_getPlot() {
		PlotsModel pm = new PlotsModel();

		Country country = Country.CANADA;
		Integer from = 2001;
		Integer to = 2003;

		IAnalysis analysis = new HealthExpenditureHospitalBeds(country, from, to);
		PlotDesigner designer = DefaultPlotDesigner.getInstance();

		AnalysisFactory analysisFactory = new AnalysisFactory("HealthExpenditureHospitalBeds", "My analysis");

		PlotFactory pf = new PlotFactory("Line", "my line plot");
		PlotFactory pf2 = new PlotFactory("Scatter", "my scatter plot");
		PlotFactory pf3 = new PlotFactory("Bar", "my bar plot");

		LinePlot lp = new LinePlot(designer);
		analysis.accept(lp);
		String plotId = pm.generateUUID();
		String plotId2 = pm.generateUUID();
		String plotId3 = pm.generateUUID();
		pm.addPlot(0, plotId, analysisFactory, pf);
		pm.addPlot(1, plotId2, analysisFactory, pf2);
		pm.addPlot(2, plotId3, analysisFactory, pf3);

		PlotFactory plot_get = pm.getPlot(plotId3);

		assertEquals(pf3, plot_get);

	}

	@Test
	public void PlotModelTest08_getAnalysis() {
		PlotsModel pm = new PlotsModel();

		Country country = Country.CANADA;
		Integer from = 2001;
		Integer to = 2003;

		IAnalysis analysis = new HealthExpenditureHospitalBeds(country, from, to);
		PlotDesigner designer = DefaultPlotDesigner.getInstance();

		AnalysisFactory analysisFactory = new AnalysisFactory("HealthExpenditureHospitalBeds", "My analysis");
		AnalysisFactory analysisFactory2 = new AnalysisFactory("CO2EnergyUseAirPollution", "My analysis");
		AnalysisFactory analysisFactory3 = new AnalysisFactory("GovernmentExpenditure", "My analysis");

		PlotFactory pf = new PlotFactory("Line", "my line plot");
		PlotFactory pf2 = new PlotFactory("Scatter", "my scatter plot");
		PlotFactory pf3 = new PlotFactory("Bar", "my bar plot");

		LinePlot lp = new LinePlot(designer);
		analysis.accept(lp);
		String plotId = pm.generateUUID();
		String plotId2 = pm.generateUUID();
		String plotId3 = pm.generateUUID();
		pm.addPlot(0, plotId, analysisFactory, pf);
		pm.addPlot(1, plotId2, analysisFactory2, pf2);
		pm.addPlot(2, plotId3, analysisFactory3, pf3);

		AnalysisFactory Analysis_get = pm.getAnalysis(plotId3);

		assertEquals(analysisFactory3, Analysis_get);

	}

	@Test
	public void PlotModelTest09_UUID() {
		PlotsModel pm = new PlotsModel();

		Country country = Country.CANADA;
		Integer from = 2001;
		Integer to = 2003;

		IAnalysis analysis = new HealthExpenditureHospitalBeds(country, from, to);
		PlotDesigner designer = DefaultPlotDesigner.getInstance();

		AnalysisFactory analysisFactory = new AnalysisFactory("HealthExpenditureHospitalBeds", "My analysis");
		AnalysisFactory analysisFactory2 = new AnalysisFactory("CO2EnergyUseAirPollution", "My analysis");
		AnalysisFactory analysisFactory3 = new AnalysisFactory("GovernmentExpenditure", "My analysis");

		PlotFactory pf = new PlotFactory("Line", "my line plot");
		PlotFactory pf2 = new PlotFactory("Scatter", "my scatter plot");
		PlotFactory pf3 = new PlotFactory("Bar", "my bar plot");

		LinePlot lp = new LinePlot(designer);
		analysis.accept(lp);
		String plotId = pm.generateUUID();
		String plotId2 = pm.generateUUID();
		String plotId3 = pm.generateUUID();

		assertNotEquals(plotId2, plotId);

	}
}