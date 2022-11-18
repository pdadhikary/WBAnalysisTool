package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.analyses.HealthExpenditureHospitalBeds;
import ca.yorku.eecs3311.team09.analyses.IAnalysis;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.exceptions.IncompatibleAnalysisException;
import ca.yorku.eecs3311.team09.exceptions.MissingDataException;
import ca.yorku.eecs3311.team09.plots.LinePlot;
import ca.yorku.eecs3311.team09.plots.Plot;
import ca.yorku.eecs3311.team09.plots.designer.DefaultPlotDesigner;
import ca.yorku.eecs3311.team09.plots.designer.PlotDesigner;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class PlotDemo {
    public static void main(String[] args) {
        try {
            JFrame frame = getFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.pack();
            frame.setVisible(true);
        } catch (MissingDataException e) {
            System.out.println("Not enough data to perform analysis");
        } catch (IncompatibleAnalysisException e) {
            System.out.println("The analysis and view are incompatible");
        }
    }

    private static JFrame getFrame() {
        JFrame frame = new JFrame();

        JLabel chooseCountryLabel = new JLabel("Choose a country: ");
        Vector<String> countriesNames = new Vector<String>();
        countriesNames.add("USA");
        countriesNames.add("Canada");
        countriesNames.add("France");
        countriesNames.add("China");
        countriesNames.add("Brazil");
        countriesNames.sort(null);
        JComboBox<String> countriesList = new JComboBox<String>(countriesNames);

        JLabel from = new JLabel("From");
        JLabel to = new JLabel("To");
        Vector<String> years = new Vector<String>();
        for (int i = 2021; i >= 2010; i--) {
            years.add("" + i);
        }
        JComboBox<String> fromList = new JComboBox<String>(years);
        JComboBox<String> toList = new JComboBox<String>(years);

        JPanel north = new JPanel();
        north.add(chooseCountryLabel);
        north.add(countriesList);
        north.add(from);
        north.add(fromList);
        north.add(to);
        north.add(toList);

        // Set bottom bar
        JButton recalculate = new JButton("Recalculate");

        JLabel viewsLabel = new JLabel("Available Views: ");

        Vector<String> viewsNames = new Vector<String>();
        viewsNames.add("Pie Chart");
        viewsNames.add("Line Chart");
        viewsNames.add("Bar Chart");
        viewsNames.add("Scatter Chart");
        viewsNames.add("Report");
        JComboBox<String> viewsList = new JComboBox<String>(viewsNames);
        JButton addView = new JButton("+");
        JButton removeView = new JButton("-");

        JLabel methodLabel = new JLabel("        Choose analysis method: ");

        Vector<String> methodsNames = new Vector<String>();
        methodsNames.add("Mortality");
        methodsNames.add("Mortality vs Expenses");
        methodsNames.add("Mortality vs Expenses & Hospital Beds");
        methodsNames.add("Mortality vs GDP");
        methodsNames.add("Unemployment vs GDP");
        methodsNames.add("Unemployment");

        JComboBox<String> methodsList = new JComboBox<String>(methodsNames);

        JPanel south = new JPanel();
        south.add(viewsLabel);
        south.add(viewsList);
        south.add(addView);
        south.add(removeView);

        south.add(methodLabel);
        south.add(methodsList);
        south.add(recalculate);

        JPanel east = new JPanel();

        // Set charts region
        JPanel west = new JPanel();
        west.setLayout(new GridLayout(2, 2));

        // TODO: Test Plots here
        createBar(west);
        frame.getContentPane().add(north, BorderLayout.NORTH);
        frame.getContentPane().add(east, BorderLayout.EAST);
        frame.getContentPane().add(south, BorderLayout.SOUTH);
        frame.getContentPane().add(west, BorderLayout.WEST);

        return frame;
    }

    private static void createBar(JPanel west) {
        Country country = Country.INDIA;
        Integer fromDate = 2010;
        Integer toDate = 2015;

        IAnalysis analysis = new HealthExpenditureHospitalBeds(
                country,
                fromDate,
                toDate
        );

        PlotDesigner designer = DefaultPlotDesigner.getInstance();

        Plot plot = new LinePlot(designer);
        analysis.accept(plot);

        JComponent chartPanel = plot.getPlot();

        west.add(chartPanel);
    }
}
