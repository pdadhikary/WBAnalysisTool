package ca.yorku.eecs3311.team09.views;

import ca.yorku.eecs3311.team09.analyses.factory.AnalysisFactory;
import ca.yorku.eecs3311.team09.controller.IAppController;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.exceptions.*;
import ca.yorku.eecs3311.team09.plots.factory.PlotFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The main app GUI.
 */
public class AppView extends JFrame implements ActionListener, MouseListener {
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
    protected JComboBox<PlotFactory> plotType;

    /**
     * recalculate button.
     */
    protected JButton recalculateButton;

    protected JButton addButton;

    protected JButton removeButton;

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
        this.setSize(1280, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.north = new JPanel();
        this.south = new JPanel();
        this.center = new JPanel();

        createPlotViewSelection();
        createCountrySelection();
        createAnalysisSelection();
        createDateSelection();


        this.recalculateButton = new JButton("Recalculate");
        this.recalculateButton.addActionListener(this);
        south.add(recalculateButton);

        this.setLayout(new BorderLayout());
        this.add(north, BorderLayout.NORTH);
        this.add(south, BorderLayout.SOUTH);
        this.add(center, BorderLayout.CENTER);
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
    protected void createCountrySelection() {
        JLabel countryName = new JLabel("Choose A Country: ");
        this.countriesList = new JComboBox<>(
                new Vector<>(
                        this.controller.getCountries()
                )
        );
        this.north.add(countryName);
        this.north.add(countriesList);
    }


    /**
     * Creates DATE selection fields
     */
    protected void createDateSelection() {
        JLabel fromLabel = new JLabel("From");
        JLabel toLabel = new JLabel("To");

        List<Integer> fromDates = IntStream.rangeClosed(
                this.controller.getMinDate(),
                this.controller.getMaxDate()
        ).boxed().collect(Collectors.toList());

        List<Integer> toDates = new ArrayList<>(fromDates);
        Collections.reverse(toDates);

        this.fromDate = new JComboBox<>(
                new Vector<>(
                        fromDates
                )
        );

        this.toDate = new JComboBox<>(
                new Vector<>(
                        toDates
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
        this.addButton = new JButton("+");
        this.removeButton = new JButton("-");

        this.addButton.addActionListener(this);
        this.removeButton.addActionListener(this);


        this.south.add(plotLabel);
        this.south.add(plotType);
        this.south.add(this.addButton);
        this.south.add(this.removeButton);
    }

    public void greetUser(String username) {
        this.setVisible(true);
        JOptionPane.showMessageDialog(
                null,
                "Welcome, " + username + "!",
                "Successfully Logged In",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public Country getSelectedCountry() {
        int selectedIndex = this.countriesList.getSelectedIndex();
        return this.countriesList.getItemAt(selectedIndex);
    }

    public Integer getSelectedFromDate() {
        int selectedIndex = this.fromDate.getSelectedIndex();
        return this.fromDate.getItemAt(selectedIndex);
    }

    public Integer getSelectedToDate() {
        int selectedIndex = this.toDate.getSelectedIndex();
        return this.toDate.getItemAt(selectedIndex);
    }

    public AnalysisFactory getSelectedAnalysis() {
        int selectedIndex = this.analysisList.getSelectedIndex();
        return this.analysisList.getItemAt(selectedIndex);
    }

    public PlotFactory getSelectedPlot() {
        int selectedIndex = this.plotType.getSelectedIndex();
        return this.plotType.getItemAt(selectedIndex);
    }

    public void addPlotView(JComponent plotView, int index) {
        this.center.add(plotView, index);
        this.center.revalidate();
    }

    public void removePlotView(int index) {
        this.center.remove(index);
        this.center.revalidate();
        this.revalidate();
        this.repaint();
    }

    public void updatePlotView(JComponent plotView, int index) {
        plotView.addMouseListener(this);
        this.removePlotView(index);
        this.addPlotView(plotView, index);
    }

    public void addPlotHighlight(int index) {
        if (index > -1 && index < this.center.getComponentCount()) {
            JComponent component = (JComponent) this.center.getComponent(index);
            component.setBorder(BorderFactory.createLineBorder(Color.black, 5));
            component.revalidate();
        }
    }

    public void removePlotHighlight(int index) {
        if (index > -1 && index < this.center.getComponentCount()) {
            JComponent component = (JComponent) this.center.getComponent(index);
            component.setBorder(BorderFactory.createEmptyBorder());
            component.revalidate();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(this.addButton)) {
                JComponent plot = this.controller.handlePlotCreation();
                plot.addMouseListener(this);
            } else if (e.getSource().equals(this.removeButton)) {
                this.controller.handlePlotDeletion();
            } else if (e.getSource().equals(this.recalculateButton)) {
                this.controller.handleRecalculation();
            }
        } catch (PlotUIException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Invalid Action",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (IncompatibleAnalysisException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    "The selected plot view and analysis are not compatible.",
                    "Incompatible View",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (MissingDataException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    "The data for the selected criteria are not available.",
                    "Data Not Available",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (UnsupportedAnalysisException | UnsupportedPlotException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Unsupported Operation",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (RestrictedDataException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Data Restricted",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (ValidationException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Something went wrong...",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JComponent component = (JComponent) e.getSource();
        this.controller.handlePlotSelection(component.getName());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
