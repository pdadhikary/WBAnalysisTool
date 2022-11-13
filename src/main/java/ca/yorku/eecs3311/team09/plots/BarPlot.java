package ca.yorku.eecs3311.team09.plots;

import ca.yorku.eecs3311.team09.analyses.*;
import ca.yorku.eecs3311.team09.enums.Indicator;
import ca.yorku.eecs3311.team09.exceptions.IncompatibleAnalysisException;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.Map;

public class BarPlot extends Plot {

    @Override
    public void plotAnalysis(AirPollutionForestArea analysis) {
        analysis.performCalculation();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<Indicator, Map<Integer, Double>> result = analysis.getResult();

        Map<Integer, Double> airPollution = result.get(Indicator.AIR_POLLUTION_MEAN);
        String label1 = "PM2.5 Air Pollution";

        Map<Integer, Double> forestArea = result.get(Indicator.FOREST_AREA);
        String label2 = "Forest Area";

        for (Integer year : airPollution.keySet()) {
            dataset.setValue(
                    airPollution.get(year),
                    label1,
                    year
            );

            dataset.setValue(
                    forestArea.get(year),
                    label2,
                    year
            );
        }

        CategoryPlot plot = new CategoryPlot();
        BarRenderer renderer = new BarRenderer();

        plot.setDataset(dataset);
        plot.setRenderer(renderer);

        CategoryAxis domainAxis = new CategoryAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis("% Annual Change"));

        plot.mapDatasetToRangeAxis(0, 0);

        JFreeChart barChart = new JFreeChart("Air Pollution vs Forest Area (% annual change)",
                new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

        this.plot = new ChartPanel(barChart);
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
        String label3 = "Air Pollution per cubic meter";

        for (Integer year : airPollution.keySet()) {
            dataset.setValue(
                    co2Emissions.get(year),
                    label1,
                    year
            );

            dataset.setValue(
                    energyUse.get(year),
                    label2,
                    year
            );

            dataset.setValue(
                    airPollution.get(year),
                    label3,
                    year
            );
        }

        CategoryPlot plot = new CategoryPlot();
        BarRenderer renderer = new BarRenderer();

        plot.setDataset(dataset);
        plot.setRenderer(renderer);

        CategoryAxis domainAxis = new CategoryAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis("% Annual Change"));

        plot.mapDatasetToRangeAxis(0, 0);

        JFreeChart barChart = new JFreeChart("CO2 Emissions, Energy Use & Air Pollution (% annual change)",
                new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

        this.plot = new ChartPanel(barChart);
    }

    @Override
    public void plotAnalysis(CO2GDP analysis) {
        analysis.performCalculation();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<Integer, Double> result = analysis.getResult();

        for (Integer year : result.keySet()) {
            dataset.setValue(
                    result.get(year),
                    "",
                    year
            );
        }

        CategoryPlot plot = new CategoryPlot();
        BarRenderer renderer = new BarRenderer();

        plot.setDataset(dataset);
        plot.setRenderer(renderer);

        CategoryAxis domainAxis = new CategoryAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis("Ratio CO2 Emissions to GDP per capita"));

        plot.mapDatasetToRangeAxis(0, 0);

        JFreeChart barChart = new JFreeChart("Ratio CO2 Emissions to GDP per capita",
                new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

        this.plot = new ChartPanel(barChart);
    }

    @Override
    public void plotAnalysis(ForestArea analysis) {
        throw new IncompatibleAnalysisException("This analysis is incompatible with a bar plot!");
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

        for (Integer year : eduExpenditure.keySet()) {
            dataset.setValue(
                    eduExpenditure.get(year),
                    label1,
                    year
            );

            dataset.setValue(
                    healthExpenditure.get(year),
                    label2,
                    year
            );
        }

        CategoryPlot plot = new CategoryPlot();
        BarRenderer renderer = new BarRenderer();

        plot.setDataset(dataset);
        plot.setRenderer(renderer);

        CategoryAxis domainAxis = new CategoryAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis("% Annual Change"));

        plot.mapDatasetToRangeAxis(0, 0);

        JFreeChart barChart = new JFreeChart("Government Expenditure on Education vs Health (% annual change)",
                new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

        this.plot = new ChartPanel(barChart);
    }

    @Override
    public void plotAnalysis(GovernmentExpenditure analysis) {
        throw new IncompatibleAnalysisException("This analysis is incompatible with a bar plot!");
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

        for (Integer year : womenHCProblem.keySet()) {
            dataset.setValue(
                    womenHCProblem.get(year),
                    label1,
                    year
            );

            dataset.setValue(
                    mortality.get(year),
                    label2,
                    year
            );
        }

        CategoryPlot plot = new CategoryPlot();
        BarRenderer renderer = new BarRenderer();

        plot.setDataset(dataset);
        plot.setRenderer(renderer);

        CategoryAxis domainAxis = new CategoryAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis("% Annual Change"));

        plot.mapDatasetToRangeAxis(0, 0);

        JFreeChart barChart = new JFreeChart(
                "Problems accessing health care (Women) Vs. mortality rate (% annual change)",
                new Font("Serif", java.awt.Font.BOLD, 18), plot, true
        );

        this.plot = new ChartPanel(barChart);
    }

    @Override
    public void plotAnalysis(HealthExpenditureHospitalBeds analysis) {
        analysis.performCalculation();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<Integer, Double> result = analysis.getResult();

        for (Integer year : result.keySet()) {
            dataset.setValue(
                    result.get(year),
                    "Expenditure to GDP ratio",
                    year
            );
        }

        CategoryPlot plot = new CategoryPlot();
        BarRenderer renderer = new BarRenderer();

        plot.setDataset(dataset);
        plot.setRenderer(renderer);

        CategoryAxis domainAxis = new CategoryAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis(""));

        plot.mapDatasetToRangeAxis(0, 0);

        JFreeChart barChart = new JFreeChart("Ratio Government Health Care Expenditure to GDP per capita",
                new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

        this.plot = new ChartPanel(barChart);
    }
}
