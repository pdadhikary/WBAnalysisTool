package ca.yorku.eecs3311.team09.plots;

import ca.yorku.eecs3311.team09.analyses.visitors.PlotVisitor;
import ca.yorku.eecs3311.team09.plots.designer.PlotDesigner;

import javax.swing.*;

/**
 * Plots a visited analysis.
 */
public abstract class Plot implements PlotVisitor {
    protected JComponent plot;
    protected PlotDesigner designer;

    /**
     * Return the plot generated after this {@link PlotVisitor PlotVisitor} is accepted
     * by an {@link ca.yorku.eecs3311.team09.analyses.Analysis Analysis}.
     *
     * @return generated plot after visitation.
     */
    public JComponent getPlot() {
        return this.plot;
    }
}
