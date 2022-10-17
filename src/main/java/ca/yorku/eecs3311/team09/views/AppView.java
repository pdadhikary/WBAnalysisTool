package ca.yorku.eecs3311.team09.views;

import ca.yorku.eecs3311.team09.analyses.*;
import ca.yorku.eecs3311.team09.enums.Country;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

/**
 * The main app GUI.
 */
public class AppView extends JFrame {
    /**
     * list of countries users can select from.
     */
    protected JComboBox<Country> countriesList;
    /**
     * list if analyses users can select from.
     */
    protected JComboBox<String> analysisList;
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

    /**
     * returns a new AppView
     */
    public AppView() {
        this.setTitle("Country Statistics");
        this.setIconImage(new ImageIcon(LoginView.LOGO_URI).getImage());
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.north = new JPanel();
        this.south = new JPanel();
        this.center = new JPanel();

        createDataSelection();
        createAnalysisSelection();

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
                        Arrays.asList(
                                CO2EnergyUseAirPollution.TITLE,
                                AirPollutionForestArea.TITLE,
                                CO2GDP.TITLE,
                                ForestArea.TITLE,
                                GovernmentExpenditure.TITLE,
                                HealthExpenditureHospitalBeds.TITLE,
                                HealthCareMortality.TITLE,
                                GovEducationHealthExpenditure.TITLE
                        )
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

        this.countriesList = new JComboBox<>(
                new Vector<>(Arrays.asList(Country.values()))
        );

        this.north.add(countryName);
        this.north.add(countriesList);
    }
}
