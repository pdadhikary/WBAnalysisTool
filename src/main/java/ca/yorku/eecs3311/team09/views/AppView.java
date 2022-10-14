package ca.yorku.eecs3311.team09.views;

import ca.yorku.eecs3311.team09.analyses.*;
import ca.yorku.eecs3311.team09.enums.Country;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class AppView extends JFrame {
    protected JComboBox<Country> countriesList;
    protected JComboBox<Analysis> analysisList;
    protected JButton recalculateButton;

    protected JPanel north;
    protected JPanel south;
    protected JPanel center;

    public AppView() {
        this.setTitle("Country Statistics");
        this.setIconImage(new ImageIcon(LoginView.LOGO_URI).getImage());
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.north = new JPanel();
        this.south = new JPanel();
        this.center = new JPanel();

        createCountrySelection();
        createAnalysisSelection();

        this.recalculateButton = new JButton("Recalculate");
        south.add(recalculateButton);

        this.setLayout(new BorderLayout());
        this.add(north, BorderLayout.NORTH);
        this.add(south, BorderLayout.SOUTH);
    }

    protected void createAnalysisSelection() {
        JLabel analysisName = new JLabel("Choose Analysis Method ");
        this.analysisList = new JComboBox<>(
                new Vector<>(
                        Arrays.asList(
                                new APCAirPollutionForestArea(),
                                new APCCO2EnergyUseAirPollution(),
                                new APCGovExpHealthExp(),
                                new AvgForestArea(),
                                new AvgGovExp(),
                                new HealthCareMortality(),
                                new RatioCO2GDP(),
                                new RatioHealthExpHospitalBeds()
                        )
                )
        );

        this.south.add(analysisName);
        this.south.add(analysisList);
    }

    protected void createCountrySelection() {
        JLabel countryName = new JLabel("Choose A Country: ");

        this.countriesList = new JComboBox<>(
                new Vector<>(Arrays.asList(Country.values()))
        );

        this.north.add(countryName);
        this.north.add(countriesList);
    }
}
