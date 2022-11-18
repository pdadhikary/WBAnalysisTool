package ca.yorku.eecs3311.team09.controller;

import ca.yorku.eecs3311.team09.analyses.IAnalysis;
import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.exceptions.PlotUIException;
import ca.yorku.eecs3311.team09.models.ILoginObserver;
import ca.yorku.eecs3311.team09.models.IPlotsModel;
import ca.yorku.eecs3311.team09.models.IUserModel;
import ca.yorku.eecs3311.team09.plots.*;
import ca.yorku.eecs3311.team09.plots.designer.DefaultPlotDesigner;
import ca.yorku.eecs3311.team09.plots.designer.PlotDesigner;
import ca.yorku.eecs3311.team09.views.AppView;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class AppController implements IAppController, ILoginObserver {
    protected AppView view;
    protected IUserModel userModel;

    protected IPlotsModel plotsModel;

    protected int selectedPlot = -1;

    public AppController(IUserModel userModel, IPlotsModel plotsModel) {
        this.userModel = userModel;
        this.plotsModel = plotsModel;

        this.userModel.addLoginObserver(this);

        this.view = new AppView(this);
    }

    @Override
    public List<Country> getCountries() {
        return Arrays.asList(
                Country.INDIA,
                Country.CANADA,
                Country.BRAZIL,
                Country.CHINA,
                Country.USA
        );
    }

    @Override
    public Integer getMaxDate() {
        return 2020;
    }

    @Override
    public Integer getMinDate() {
        return 2010;
    }

    @Override
    public List<AnalysisFactory> getAnalyses() {
        return Arrays.asList(
                new AnalysisFactory("AirPollutionForestArea", "AirPollutionForestArea"),
                new AnalysisFactory("CO2EnergyUseAirPollution", "CO2EnergyUseAirPollution"),
                new AnalysisFactory("CO2GDP", "CO2GDP"),
                new AnalysisFactory("ForestArea", "ForestArea"),
                new AnalysisFactory("GovEducationHealthExpenditure", "GovEducationHealthExpenditure"),
                new AnalysisFactory("GovernmentExpenditure", "GovernmentExpenditure"),
                new AnalysisFactory("HealthCareMortality", "HealthCareMortality"),
                new AnalysisFactory("HealthExpenditureHospitalBeds", "HealthExpenditureHospitalBeds")
        );
    }

    @Override
    public List<Plot> getPlotViews() {
        PlotDesigner designer = DefaultPlotDesigner.getInstance();
        return Arrays.asList(
                new LinePlot(designer),
                new BarPlot(designer),
                new PiePlot(designer),
                new ScatterPlot(designer),
                new ReportPlot(designer)
        );
    }

    @Override
    public void successfulLogin() {
        this.view.greetUser(this.userModel.getUsername());
    }

    @Override
    public JComponent handlePlotCreation() {
        AnalysisFactory factory = this.view.getAnalysis();
        Plot plot = this.view.getPlot();
        Country country = this.view.getCountry();
        Integer fromDate = this.view.getFromDate();
        Integer toDate = this.view.getToDate();

        IAnalysis analysis = factory.getAnalysis(country, fromDate, toDate);
        analysis.accept(plot);

        JComponent generatedPlot = plot.getPlot();
        String plotId = this.plotsModel.generateUUID();
        generatedPlot.setName(plotId);
        this.plotsModel.addPlot(
                this.selectedPlot + 1,
                plotId,
                factory,
                plot
        );

        this.view.addPlotView(generatedPlot, this.selectedPlot + 1);
        return generatedPlot;
    }

    @Override
    public void handlePlotDeletion() {
        if (this.selectedPlot == -1) {
            throw new PlotUIException("Please select a plot view inorder to delete it.");
        }

        String plotId = this.plotsModel.getPlotId(this.selectedPlot);
        this.plotsModel.removePlot(plotId);
        this.view.removePlotView(this.selectedPlot);
        this.selectedPlot = -1;
    }

    @Override
    public void handleRecalculation() {
        if (this.selectedPlot == -1) {
            throw new PlotUIException("Please select a plot view to perform recalculation.");
        }

        Country country = this.view.getCountry();
        Integer fromDate = this.view.getFromDate();
        Integer toDate = this.view.getToDate();

        String plotId = this.plotsModel.getPlotId(this.selectedPlot);
        AnalysisFactory factory = this.plotsModel.getAnalysis(plotId);
        Plot plot = this.plotsModel.getPlot(plotId);

        IAnalysis analysis = factory.getAnalysis(country, fromDate, toDate);
        analysis.accept(plot);

        JComponent generatedPlot = plot.getPlot();
        generatedPlot.setName(plotId);

        this.view.updatePlotView(generatedPlot, this.selectedPlot);
        this.view.addPlotHighlight(this.selectedPlot);
    }

    @Override
    public void handlePlotSelection(String plotId) {
        int selected = this.plotsModel.getPlotIndex(plotId);
        this.view.removePlotHighlight(this.selectedPlot);

        if (this.selectedPlot == selected) {
            this.selectedPlot = -1;
        } else {
            this.view.addPlotHighlight(selected);
            this.selectedPlot = selected;
        }
    }

    @Override
    public int getSelectedIndex() {
        return this.selectedPlot;
    }
}
