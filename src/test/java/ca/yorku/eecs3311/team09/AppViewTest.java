package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.controller.IAppController;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.plots.factory.PlotFactory;
import ca.yorku.eecs3311.team09.views.AppView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
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
        
        public JButton getAddButton() {
        	return this.addButton;
        }
        
        public JButton getRemoveButton() {
        	return this.removeButton;
        }
        
        public JButton getRecalculateButton() {
        	return this.recalculateButton;
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
        	AppViewTest.plotCreated = true;
            return new JPanel();
        }

        @Override
        public void handlePlotDeletion() {
        	AppViewTest.plotDeleted = true;
        }

        @Override
        public void handleRecalculation() {
        	AppViewTest.plotRecalculated = true;
        }

        @Override
        public void handlePlotSelection(String plotId) {

        }
    }
    
    protected static boolean plotCreated = false;
    protected static boolean plotDeleted = false;
    protected static boolean plotRecalculated = false;
    
    @Before
    public void before() {
    	AppViewTest.plotCreated = false;
    	AppViewTest.plotDeleted = false;
    	AppViewTest.plotRecalculated = false;
    }
    
    @Test
    public void AppViewTest01() {
        DummyAppController controller = new DummyAppController();
        TestAppView view = new TestAppView(controller);
        Assert.assertEquals("Incorrect number of countries were added",
                2,
                view.getCountriesList().getItemCount()
        );

        Assert.assertEquals("Incorrect number of countries were added",
                "India",
                view.getCountriesList().getItemAt(0).getName()
        );

        Assert.assertEquals("Incorrect number of countries were added",
                "Canada",
                view.getCountriesList().getItemAt(1).getName()
        );
    }

    @Test
    public void AppViewTest02() {
    	DummyAppController controller = new DummyAppController();
        TestAppView view = new TestAppView(controller);
        
        view.getCountriesList().setSelectedIndex(0);
        
        Assert.assertEquals("Incorrect number of countries were added",
                "India",
                view.getSelectedCountry().getName()
        );
        
        view.getCountriesList().setSelectedIndex(1);
        Assert.assertEquals("Incorrect number of countries were added",
                "Canada",
                view.getSelectedCountry().getName()
        );
    }
    
    @Test
    public void AppViewTest03() {
    	DummyAppController controller = new DummyAppController();
        TestAppView view = new TestAppView(controller);
        
        for(Integer year = 2000, i = 0; year <= 2010; year++, i++) {
        	view.getFromDate().setSelectedIndex(i);
        	
        	Assert.assertEquals("Incorrect from date added/returned",
                    year,
                    view.getSelectedFromDate()
            );
        	
        }
        
        for(Integer year = 2010, i = 0; year >= 2000; year--, i++) {
        	view.getToDate().setSelectedIndex(i);
        	
        	Assert.assertEquals("Incorrect to date added/returned",
                    year,
                    view.getSelectedToDate()
            );
        	
        }
    }
    
    @Test
    public void AppViewTest04() {
    	DummyAppController controller = new DummyAppController();
        TestAppView view = new TestAppView(controller);
        
        JPanel panel = new JPanel();
        
        view.addPlotView(panel, 0);
        
        Assert.assertEquals("Plot view was not added",
        		panel,
        		view.getCenter().getComponent(0)
        );
    }
    
    @Test
    public void AppViewTest05() {
    	DummyAppController controller = new DummyAppController();
        TestAppView view = new TestAppView(controller);
        
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        
        view.addPlotView(panel, 0);
        view.addPlotView(panel2, 1);
        
        Assert.assertEquals("Plot views were not added",
        		2,
        		view.getCenter().getComponentCount()
        );
        
        view.removePlotView(0);
        
        Assert.assertEquals("Plot view was not removed",
        		1,
        		view.getCenter().getComponentCount()
        );
        
        Assert.assertEquals("Wrong plot view removed",
        		panel2,
        		view.getCenter().getComponent(0)
        );
    }
    
    @Test
    public void AppViewTest06() {
    	DummyAppController controller = new DummyAppController();
        TestAppView view = new TestAppView(controller);
        
        ActionEvent event = new ActionEvent(view.getAddButton(), 0, "add");
        
        view.actionPerformed(event);
        
        Assert.assertTrue("Plot creation was not deferred to the controller", 
        		AppViewTest.plotCreated);
    }
    
    @Test
    public void AppViewTest07() {
    	DummyAppController controller = new DummyAppController();
        TestAppView view = new TestAppView(controller);
        
        ActionEvent event = new ActionEvent(view.getRemoveButton(), 1, "remove");
        
        view.actionPerformed(event);
        
        Assert.assertTrue("Plot deletion was not deferred to the controller", 
        		AppViewTest.plotDeleted);
    }
    
    @Test
    public void AppViewTest08() {
    	DummyAppController controller = new DummyAppController();
        TestAppView view = new TestAppView(controller);
        
        ActionEvent event = new ActionEvent(view.getRecalculateButton(), 2, "recalculate");
        
        view.actionPerformed(event);
        
        Assert.assertTrue("Plot deletion was not deferred to the controller", 
        		AppViewTest.plotRecalculated);
    }
    
    @Test
    public void AppViewTest09() {
    	DummyAppController controller = new DummyAppController();
        TestAppView view = new TestAppView(controller);
        
        JPanel panel = new JPanel();
        view.addPlotView(panel, 0);
        
        JPanel panel2 = new JPanel();
        view.updatePlotView(panel2, 0);
        
        Assert.assertEquals("Plot view was not updated",
        		panel2,
        		view.getCenter().getComponent(0)
        );
    }
    
    @Test
    public void AppViewTest10() {
    	DummyAppController controller = new DummyAppController();
        TestAppView view = new TestAppView(controller);
        
        JPanel panel = new JPanel();
        view.addPlotView(panel, 0);
        
        view.addPlotHighlight(0);
        
        Assert.assertTrue("Plot selection border was not added", 
        		panel.getBorder().isBorderOpaque());
        
        view.removePlotHighlight(0);
        
        Assert.assertFalse("Plot selection border was not removed", 
        		panel.getBorder().isBorderOpaque());
    }
}