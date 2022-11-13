package ca.yorku.eecs3311.team09.plots;

import ca.yorku.eecs3311.team09.analyses.visitors.PlotVisitor;

import javax.swing.*;

public abstract class Plot implements PlotVisitor {
    JComponent plot;

    public JComponent getPlot() {
        return this.plot;
    }
}
