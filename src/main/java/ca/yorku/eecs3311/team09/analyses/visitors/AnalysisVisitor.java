package ca.yorku.eecs3311.team09.analyses.visitors;

import ca.yorku.eecs3311.team09.analyses.AnnualPercentChangeAnalysis;
import ca.yorku.eecs3311.team09.analyses.AverageAnalysis;
import ca.yorku.eecs3311.team09.analyses.LazyAnalysis;
import ca.yorku.eecs3311.team09.analyses.RatioAnalysis;

/**
 * A visitor of an Analysis.
 */
public interface AnalysisVisitor {
    /**
     * Visit an {@link AnnualPercentChangeAnalysis AnnualPercentChange Analysis}.
     *
     * @param analysis analysis to visit
     */
    void visitAnalysis(AnnualPercentChangeAnalysis analysis);

    /**
     * Visit an {@link AverageAnalysis Average Analysis}.
     *
     * @param analysis analysis to visit
     */
    void visitAnalysis(AverageAnalysis analysis);

    /**
     * Visit a {@link RatioAnalysis Ratio Analysis}.
     *
     * @param analysis analysis to visit
     */
    void visitAnalysis(RatioAnalysis analysis);

    /**
     * Visit a {@link LazyAnalysis Lazy Analysis}.
     *
     * @param analysis analysis to visit
     */
    void visitAnalysis(LazyAnalysis analysis);
}
