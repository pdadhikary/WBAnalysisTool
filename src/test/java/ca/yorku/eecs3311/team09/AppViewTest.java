package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.controller.IAppController;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.plots.factory.PlotFactory;
import ca.yorku.eecs3311.team09.views.AppView;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppViewTest {
    private static class TestAppView extends AppView {

        /**
         * Create an app view.
         *
         * @param controller controller of the view.
         */
        public TestAppView(IAppController controller) {
            super(controller);
        }

        public JComboBox<Country> getCountriesList() {
            return this.countriesList;
        }

        public JComboBox<AnalysisFactory> getAnalysisList() {
            return this.analysisList;
        }

        public JComboBox<Integer> getFromDate() {
            return this.fromDate;
        }

        public JComboBox<Integer> getToDate() {
            return this.toDate;
        }

        public JPanel getCenter() {
            return this.center;
        }
    }

    private static class DummyAppController implements IAppController {

        @Override
        public List<Country> getCountries() {
            return Arrays.asList(Country.INDIA, Country.CANADA);
        }

        @Override
        public Integer getMaxDate() {
            return 2010;
        }

        @Override
        public Integer getMinDate() {
            return 2000;
        }

        @Override
        public List<AnalysisFactory> getAnalyses() {
            return new ArrayList<>();
        }

        @Override
        public List<PlotFactory> getPlotViews() {
            return new ArrayList<>();
        }

        @Override
        public JComponent handlePlotCreation() {
            return new JPanel();
        }

        @Override
        public void handlePlotDeletion() {

        }

        @Override
        public void handleRecalculation() {

        }

        @Override
        public void handlePlotSelection(String plotId) {

        }
    }

    @Test
    public void AppViewTest01() {
//        DummyAppController controller = new DummyAppController();
//        TestAppView view = new TestAppView(controller);
//
//        Assert.assertEquals("Incorrect number of countries were added",
//                view.getCountriesList().getSize().height,
//                2
//        );
    }
}
