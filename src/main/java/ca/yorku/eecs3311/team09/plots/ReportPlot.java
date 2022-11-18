package ca.yorku.eecs3311.team09.plots;

import ca.yorku.eecs3311.team09.analyses.*;
import ca.yorku.eecs3311.team09.enums.Indicator;
import ca.yorku.eecs3311.team09.plots.designer.PlotDesigner;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Generates a {@link javax.swing.JComponent Panel} containing a bar chart of the visited analysis result.
 */
public class ReportPlot extends Plot {
    public ReportPlot(PlotDesigner designer) {
        this.designer = designer;
    }


    public JScrollPane createReport(String result) {

        JTextArea report = new JTextArea(); // creating text area

        report.setEditable(false);

        report.setBackground(Color.white);

        report.append(result);
        JScrollPane textpane = new JScrollPane(report);
        designer.setScrollSize(textpane);

        return textpane;
    }

    @Override
    public void plotAnalysis(AirPollutionForestArea analysis) {
        analysis.performCalculation();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

        Map<Integer, Double> airPollution = result.get(Indicator.AIR_POLLUTION_MEAN);
        String label1 = "PM2.5 Air Pollution";

        Map<Integer, Double> forestArea = result.get(Indicator.FOREST_AREA);
        String label2 = "Forest Area";


        String one = "\n========================\n" + analysis.getTitle() + "\n========================\n";

        for (Integer year : airPollution.keySet()) {

            one = one + ("\n\nYear: " + year + "\n");
            one = one + ("\n" + "Airpollution % Annual Change : " + airPollution.get(year));
            one = one + ("\n" + "Forest Area % Annual Change : " + forestArea.get(year));


        }// end for



      /* one =one+  ReportPlot.insertData(dataset, airPollution, label1,"% Annual Change");
       one=one+ ReportPlot.insertData(dataset, forestArea, label2, "% Annual Change");*/


        this.plot = createReport(one);
    }

    @Override
    public void plotAnalysis(CO2EnergyUseAirPollution analysis) {
        analysis.performCalculation();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

        Map<Integer, Double> co2Emissions = result.get(Indicator.CO2_EMISSIONS);
        String label1 = "CO2 Emissions metric ton per capita";

        Map<Integer, Double> energyUse = result.get(Indicator.ENERGY_USE);
        String label2 = "Energy Use kg per capita";

        Map<Integer, Double> airPollution = result.get(Indicator.AIR_POLLUTION_MEAN);
        String label3 = "Air Pollution per m^3";

        String one = "\n========================\n" + analysis.getTitle() + "\n========================\n";

        for (Integer year : co2Emissions.keySet()) {

            one = one + ("\n\nYear: " + year + "\n");
            one = one + ("\n" + "CO2 Emission Ratio : " + co2Emissions.get(year));
            one = one + ("\n" + "Energy Use Ratio : " + energyUse.get(year));
            one = one + ("\n" + "Air Pollution Ratio : " + airPollution.get(year));


        }

        this.plot = createReport(one);
    }

    @Override
    public void plotAnalysis(CO2GDP analysis) {
        analysis.performCalculation();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<Integer, Double> result = analysis.getResult();
        String label = "CO2 Emission to GDP per capita";

        String one = "\n========================\n" + analysis.getTitle() + "\n========================\n";

        for (Integer year : result.keySet()) {

            one = one + ("\n\nYear: " + year + "\n");
            one = one + ("\n" + "% Annual Change : " + result.get(year));

        }// end for


        //  one=one+ ReportPlot.insertData(dataset, result, label,"% Annual Change");

        JScrollPane report = createReport(one); // creating text area

        this.plot = report;
    }

    @Override
    public void plotAnalysis(ForestArea analysis) {

        analysis.performCalculation();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<Indicator, Double> result = analysis.getResult();


        String one = "\n========================\n" + analysis.getTitle() + "\n========================\n";


        for (Indicator i : result.keySet()) {

            one = one + ("\n\n" + i + "\n");
            one = one + ("\n" + "Forest Area percent of land: " + result.get(i));

        }


        // one= one+ ReportPlot.insertData(dataset, result, label,"Ratio");

        JScrollPane report = createReport(one); // creating text area

        this.plot = report;
    }

    @Override
    public void plotAnalysis(GovEducationHealthExpenditure analysis) {
        analysis.performCalculation();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

        Map<Integer, Double> eduExpenditure = result.get(Indicator.GOV_EXPENDITURE_EDU_GDP);
        String label1 = "Government Expenditure on Education";

        Map<Integer, Double> healthExpenditure = result.get(Indicator.HEALTH_EXPENDITURE_GDP);
        String label2 = "Government Expenditure on Health";

        String one = "\n========================\n" + analysis.getTitle() + "\n========================\n";


        for (Integer year : eduExpenditure.keySet()) {

            one = one + ("\n\nYear: " + year + "\n");
            one = one + ("\n" + "Edu Expenditure % Annual Change : " + eduExpenditure.get(year));
            one = one + ("\n" + "Health Expenditure % Annual Change: " + healthExpenditure.get(year));


        }// end for


       /* one= one+ ReportPlot.insertData(dataset, eduExpenditure, label1,"% Annual Change");
        one=one+ReportPlot.insertData(dataset, healthExpenditure, label2,"% Annual Change");
        */
        JScrollPane report = createReport(one); // creating text area

        this.plot = report;
    }

    @Override
    public void plotAnalysis(GovernmentExpenditure analysis) {

        analysis.performCalculation();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<Indicator, Double> result = analysis.getResult();


        String one = "\n========================\n" + analysis.getTitle() + "\n========================\n";


        for (Indicator i : result.keySet()) {

            one = one + ("\n\n" + i + "\n");
            one = one + ("\n" + "Government Expenditure: " + result.get(i));

        }


        // one= one+ ReportPlot.insertData(dataset, result, label,"Ratio");

        this.plot = createReport(one);
    }

    @Override
    public void plotAnalysis(HealthCareMortality analysis) {
        analysis.performCalculation();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

        Map<Integer, Double> womenHCProblem = result.get(Indicator.PROBLEM_ACCESSING_HC_WOMEN);
        String label1 = "Problems in accessing health care (% of women)";

        Map<Integer, Double> mortality = result.get(Indicator.MORTALITY_RATE_INFANT);
        String label2 = "Mortality rate, infant";

        String one = "\n========================\n" + analysis.getTitle() + "\n========================\n";


        for (Integer year : womenHCProblem.keySet()) {

            one = one + ("\n\nYear: " + year + "\n");
            one = one + ("\n" + "Women Heatlth care accsess Indcidents/per 1,000 births : " + womenHCProblem.get(year));
            one = one + ("\n" + "Infant Mortality Indcidents/per 1,000 births : " + mortality.get(year));


        }// end for


       /* one= one+ ReportPlot.insertData(dataset, womenHCProblem, label1,"Indcidents/per 1,000 births");
        one= one+ReportPlot.insertData(dataset, mortality, label2,"Indcidents/per 1,000 births");
        */
        JScrollPane report = createReport(one); // creating text area

        this.plot = report;
    }

    @Override
    public void plotAnalysis(HealthExpenditureHospitalBeds analysis) {
        analysis.performCalculation();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<Integer, Double> result = analysis.getResult();
        String label = "Expenditure to GDP ratio";

        String one = "\n========================\n" + analysis.getTitle() + "\n========================\n";

        for (Integer year : result.keySet()) {

            one = one + ("\n\nYear: " + year + "\n");
            one = one + ("\n" + "Ratio : " + result.get(year));

        }// end for


        // one= one+ ReportPlot.insertData(dataset, result, label,"Ratio");

        this.plot = createReport(one);
    }

    private static String insertData(
            DefaultCategoryDataset dataset,
            Map<Integer, Double> column,
            String label, String name
    ) {

        String result = "\n" + label;

        for (Integer year : column.keySet()) {
            dataset.setValue(
                    column.get(year),
                    label,
                    year
            );


            result = result + ("\n" + name + ": " + column.get(year) + " and Year: " + year + "\n");

        }

        return result;
    }

    @Override
    public String toString() {
        return "Report Plot";
    }
}