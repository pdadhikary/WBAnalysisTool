package ca.yorku.eecs3311.team09.views;

import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.controller.IAppController;
import ca.yorku.eecs3311.team09.enums.Country;
import org.jfree.chart.plot.Plot;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The main app GUI.
 */
public class AppView extends JFrame {
    /**
     * list of countries users can select from.
     */
    protected JComboBox<Country> countriesList;
    /**
     * list of analyses users can select from.
     */
    protected JComboBox<AnalysisFactory> analysisList;

    /**
     * list of beginning dates users can select from.
     */
    protected JComboBox<Integer> fromDate;


    /**
     * list of end dates users can select from.
     */
    protected JComboBox<Integer> toDate;


    /**
     * list of plot types users can select from.
     */
    protected JComboBox<Plot> plotType;
    /**
     * recalculate button.
     */
    protected JButton recalculateButton;

    /**
     * panel containing data selection fields.
     */
    protected JPanel north;
    /**
     * panel containing analysis selection fields.
     */
    protected JPanel south;
    /**
     * panel containing graph panels.
     */
    protected JPanel center;

    protected IAppController controller;

    /**
     * returns a new AppView
     */
    public AppView(IAppController controller) {
        this.controller = controller;

        this.setTitle("Country Statistics");
        this.setIconImage(new ImageIcon(LoginView.LOGO_URI).getImage());
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.north = new JPanel();
        this.south = new JPanel();
        this.center = new JPanel();

        createPlotViewSelection();
        createDataSelection();
        createAnalysisSelection();
        createDateSelection();


        this.recalculateButton = new JButton("Recalculate");
        south.add(recalculateButton);

        this.setLayout(new BorderLayout());
        this.add(north, BorderLayout.NORTH);
        this.add(south, BorderLayout.SOUTH);
    }

    /**
     * Creates analysis selection fields.
     */
    protected void createAnalysisSelection() {
        JLabel analysisName = new JLabel("Choose Analysis Method ");
        this.analysisList = new JComboBox<>(
                new Vector<>(
                        this.controller.getAnalyses()
                )
        );

        this.south.add(analysisName);
        this.south.add(analysisList);
    }

    /**
     * Creates data selection fields
     */
    protected void createDataSelection() {
        JLabel countryName = new JLabel("Choose A Country: ");

        this.countriesList = new JComboBox<>();

        this.north.add(countryName);
        this.north.add(countriesList);
    }


    /**
     * Creates DATE selection fields
     */
    protected void createDateSelection() {
        JLabel fromLabel = new JLabel("From");
        JLabel toLabel = new JLabel("To");

        List<Integer> dates = IntStream.range(
                this.controller.getMinDate(),
                this.controller.getMaxDate()
        ).boxed().collect(Collectors.toList());

        this.fromDate = new JComboBox<>(
                new Vector<>(
                        dates
                )
        );

        this.toDate = new JComboBox<>(
                new Vector<>(
                        dates
                )
        );

        this.north.add(fromLabel);
        this.north.add(fromDate);
        this.north.add(toLabel);
        this.north.add(toDate);
    }


    /**
     * Creates DATE selection fields
     */
    protected void createPlotViewSelection() {
        JLabel plotLabel = new JLabel("Available Views");
        this.plotType = new JComboBox<>(
                new Vector<>(
                        this.controller.getPlotViews()
                )
        );
        JButton plusButton = new JButton("+");
        JButton minusButton = new JButton("-");


        this.south.add(plotLabel);
        this.south.add(plotType);
        this.south.add(plusButton);
        this.south.add(minusButton);
    }
}
