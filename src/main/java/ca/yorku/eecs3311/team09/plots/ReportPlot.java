package ca.yorku.eecs3311.team09.plots;

import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ca.yorku.eecs3311.team09.analyses.AirPollutionForestArea;
import ca.yorku.eecs3311.team09.analyses.CO2EnergyUseAirPollution;
import ca.yorku.eecs3311.team09.analyses.CO2GDP;
import ca.yorku.eecs3311.team09.analyses.ForestArea;
import ca.yorku.eecs3311.team09.analyses.GovEducationHealthExpenditure;
import ca.yorku.eecs3311.team09.analyses.GovernmentExpenditure;
import ca.yorku.eecs3311.team09.analyses.HealthCareMortality;
import ca.yorku.eecs3311.team09.analyses.HealthExpenditureHospitalBeds;
import ca.yorku.eecs3311.team09.enums.Indicator;
import ca.yorku.eecs3311.team09.plots.designer.PlotDesigner;

/**
 * Generates a {@link javax.swing.JComponent Panel} containing a report of the
 * visited analysis result.
 */
public class ReportPlot extends Plot {
	private static class ReportPane extends JScrollPane {
		protected JTextArea reportTextArea;

		public ReportPane(JTextArea reportTextArea) {
			super(reportTextArea);
			this.reportTextArea = reportTextArea;
		}

		@Override
		public void addMouseListener(MouseListener l) {
			super.addMouseListener(l);
			System.out.println("listener added!");
			this.reportTextArea.addMouseListener(l);
		}

		@Override
		public void setName(String name) {
			super.setName(name);
			this.reportTextArea.setName(name);
		}
	}

	public ReportPlot(PlotDesigner designer) {
		this.designer = designer;
	}

	@Override
	public void plotAnalysis(AirPollutionForestArea analysis) {
		analysis.performCalculation();

		String reportText = this.generateDefaultReport(analysis.getTitle(), analysis.getFromDate(),
				analysis.getToDate(), analysis.getResult());

		this.plot = createReport(reportText);
	}

	@Override
	public void plotAnalysis(CO2EnergyUseAirPollution analysis) {
		analysis.performCalculation();

		String reportText = this.generateDefaultReport(analysis.getTitle(), analysis.getFromDate(),
				analysis.getToDate(), analysis.getResult());

		this.plot = createReport(reportText);
	}

	@Override
	public void plotAnalysis(CO2GDP analysis) {
		analysis.performCalculation();

		String reportText = this.generateRatioReport(analysis.getTitle(), analysis.getFromDate(), analysis.getToDate(),
				analysis.getResult());

		this.plot = createReport(reportText);
	}

	@Override
	public void plotAnalysis(ForestArea analysis) {
		analysis.performCalculation();

		String reportText = this.generateAverageReport(analysis.getTitle(), Indicator.FOREST_AREA.getLabel(),
				analysis.getResult().get(Indicator.FOREST_AREA));

		this.plot = createReport(reportText);
	}

	@Override
	public void plotAnalysis(GovEducationHealthExpenditure analysis) {
		analysis.performCalculation();

		String reportText = this.generateDefaultReport(analysis.getTitle(), analysis.getFromDate(),
				analysis.getToDate(), analysis.getResult());

		this.plot = createReport(reportText);
	}

	@Override
	public void plotAnalysis(GovernmentExpenditure analysis) {
		analysis.performCalculation();

		String reportText = this.generateAverageReport(analysis.getTitle(),
				Indicator.GOV_EXPENDITURE_EDU_GDP.getLabel(),
				analysis.getResult().get(Indicator.GOV_EXPENDITURE_EDU_GDP));

		this.plot = createReport(reportText);
	}

	@Override
	public void plotAnalysis(HealthCareMortality analysis) {
		analysis.performCalculation();

		String reportText = this.generateDefaultReport(analysis.getTitle(), analysis.getFromDate(),
				analysis.getToDate(), analysis.getResult());

		this.plot = createReport(reportText);
	}

	@Override
	public void plotAnalysis(HealthExpenditureHospitalBeds analysis) {
		analysis.performCalculation();

		String reportText = this.generateRatioReport(analysis.getTitle(), analysis.getFromDate(), analysis.getToDate(),
				analysis.getResult());

		this.plot = createReport(reportText);
	}

	/**
	 * Generate a report from analysis data.
	 *
	 * @param title    report title
	 * @param fromDate start date of analysis
	 * @param toDate   end date of analysis
	 * @param result   analysis result
	 * @return report
	 */
	private String generateDefaultReport(String title, int fromDate, int toDate,
			Map<Indicator, Map<Integer, Double>> result) {
		StringBuilder builder = new StringBuilder();

		this.writeTitle(builder, title);
		for (int year = fromDate; year <= toDate; year++) {
			boolean datePrinted = false;
			for (Indicator indicator : result.keySet()) {
				Map<Integer, Double> series = result.get(indicator);
				if (!series.containsKey(year))
					continue;
				if (!datePrinted) {
					this.writeYear(builder, year);
					datePrinted = true;
				}
				this.writeValue(builder, indicator.getLabel(), series.get(year));
			}
		}

		return builder.toString();
	}

	/**
	 * Generate a report from analysis data.
	 *
	 * @param title    report title
	 * @param fromDate start date of analysis
	 * @param toDate   end date of analysis
	 * @param result   analysis result
	 * @return report
	 */
	private String generateRatioReport(String title, int fromDate, int toDate, Map<Integer, Double> result) {
		StringBuilder builder = new StringBuilder();

		this.writeTitle(builder, title);

		for (int year = fromDate; year <= toDate; year++) {
			if (result.containsKey(year)) {
				this.writeYear(builder, year);
				this.writeValue(builder, "ratio", result.get(year));
			}
		}

		return builder.toString();
	}

	/**
	 * Generate a report from analysis data.
	 *
	 * @param title report title
	 * @param label data label
	 * @param value data value
	 * @return report
	 */
	private String generateAverageReport(String title, String label, Double value) {
		StringBuilder builder = new StringBuilder();

		this.writeTitle(builder, title);

		this.writeValue(builder, label, value);

		return builder.toString();
	}

	/**
	 * Write the title with formatting added.
	 *
	 * @param builder string builder
	 * @param title   title
	 */
	private void writeTitle(StringBuilder builder, String title) {
		builder.append(title);
		builder.append("\n");

		String underline = new String(new char[title.length()]).replace("\0", "=");
		builder.append(underline);
		builder.append("\n");
	}

	/**
	 * Write the year with formatting added.
	 *
	 * @param builder string builder
	 * @param year    title
	 */
	private void writeYear(StringBuilder builder, int year) {
		builder.append(String.format("%d:\n", year));
	}

	/**
	 * Write the label and value pair with formatting added.
	 *
	 * @param builder string builder
	 * @param label   label
	 * @param value   value
	 */
	private void writeValue(StringBuilder builder, String label, Double value) {
		builder.append(String.format("\t%s => %.2f\n", label, value));
	}

	private JScrollPane createReport(String result) {
		JTextArea report = new JTextArea();
		report.append(result);
		designer.formatReport(report);
		JScrollPane textPane = new ReportPane(report);
		designer.setScrollSize(textPane);
		return textPane;
	}

	@Override
	public String toString() {
		return "Report Plot";
	}
}