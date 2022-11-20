package ca.yorku.eecs3311.team09.controller;

import ca.yorku.eecs3311.team09.analyses.IAnalysis;
import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.controller.config_loader.ConfigLoader;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.exceptions.PlotUIException;
import ca.yorku.eecs3311.team09.models.ILoginObserver;
import ca.yorku.eecs3311.team09.models.IPlotsModel;
import ca.yorku.eecs3311.team09.models.IUserModel;
import ca.yorku.eecs3311.team09.plots.Plot;
import ca.yorku.eecs3311.team09.plots.factory.PlotFactory;
import ca.yorku.eecs3311.team09.views.AppView;

import javax.swing.*;
import java.util.List;

public class AppController implements IAppController, ILoginObserver {

    protected ConfigLoader configLoader;
    protected AppView view;
    protected IUserModel userModel;
    protected IPlotsModel plotsModel;
    protected int selectedPlot = -1;

    public AppController(IUserModel userModel, IPlotsModel plotsModel) {
        this.configLoader = new ConfigLoader();

        this.userModel = userModel;
        this.plotsModel = plotsModel;

        this.userModel.addLoginObserver(this);

        this.view = new AppView(this);
    }

    @Override
    public List<Country> getCountries() {
        return this.configLoader.getCountries();
    }

    @Override
    public Integer getMaxDate() {
        return this.configLoader.getMaxDate();
    }

    @Override
    public Integer getMinDate() {
        return this.configLoader.getMinDate();
    }

    @Override
    public List<AnalysisFactory> getAnalyses() {
        return this.configLoader.getAnalyses();
    }

    @Override
    public List<PlotFactory> getPlotViews() {
        return this.configLoader.getPlots();
    }

    @Override
    public void successfulLogin() {
        this.view.greetUser(this.userModel.getUsername());
    }

    @Override
    public JComponent handlePlotCreation() {
        this.validateState();

        AnalysisFactory analysisFactory = this.view.getSelectedAnalysis();
        PlotFactory plotFactory = this.view.getSelectedPlot();

        Country country = this.view.getSelectedCountry();
        Integer fromDate = this.view.getSelectedFromDate();
        Integer toDate = this.view.getSelectedToDate();

        IAnalysis analysis = analysisFactory.getAnalysis(country, fromDate, toDate);
        Plot plot = plotFactory.getPlot();
        analysis.accept(plot);

        JComponent generatedPlot = plot.getPlot();
        String plotId = this.plotsModel.generateUUID();
        generatedPlot.setName(plotId);
        this.plotsModel.addPlot(
                this.selectedPlot + 1,
                plotId,
                analysisFactory,
                plotFactory
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

        this.validateState();

        Country country = this.view.getSelectedCountry();
        Integer fromDate = this.view.getSelectedFromDate();
        Integer toDate = this.view.getSelectedToDate();

        String plotId = this.plotsModel.getPlotId(this.selectedPlot);
        AnalysisFactory analysisFactory = this.plotsModel.getAnalysis(plotId);
        PlotFactory plotFactory = this.plotsModel.getPlot(plotId);

        IAnalysis analysis = analysisFactory.getAnalysis(country, fromDate, toDate);
        Plot plot = plotFactory.getPlot();
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

    protected void validateState() {
        FormValidationUtility.checkLessThanEqual(
                this.view.getSelectedFromDate(),
                this.view.getSelectedToDate(),
                "'From' cannot be greater than 'To' date"
        );

        String selectedAnalysisCode = this.view.getSelectedAnalysis().getAnalysisCode();
        List<Integer> restrictedDates = this.configLoader.getDateExclusion(selectedAnalysisCode);

        FormValidationUtility.restrictInputs(
                this.view.getSelectedFromDate(),
                this.view.getSelectedToDate(),
                restrictedDates,
                "The following dates are restricted: " + restrictedDates
        );

        String selectedCountryCode = this.view.getSelectedCountry().getCode();
        List<String> restrictedCountries = this.configLoader.getCountryCodeExclusion(selectedAnalysisCode);

        FormValidationUtility.restrictInputs(
                selectedCountryCode,
                restrictedCountries,
                "Data for the following countries are restricted: " + restrictedCountries
        );
    }
}
