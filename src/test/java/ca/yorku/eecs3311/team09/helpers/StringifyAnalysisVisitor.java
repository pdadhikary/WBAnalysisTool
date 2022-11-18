package ca.yorku.eecs3311.team09.helpers;

import ca.yorku.eecs3311.team09.analyses.AnnualPercentChangeAnalysis;
import ca.yorku.eecs3311.team09.analyses.AverageAnalysis;
import ca.yorku.eecs3311.team09.analyses.LazyAnalysis;
import ca.yorku.eecs3311.team09.analyses.RatioAnalysis;
import ca.yorku.eecs3311.team09.analyses.visitors.AnalysisVisitor;

/**
 * A visitor class that stringifies the result of an Analysis.
 */
public class StringifyAnalysisVisitor implements AnalysisVisitor {
    public String analysisStringResult = "";

    @Override
    public void visitAnalysis(AnnualPercentChangeAnalysis analysis) {
        this.analysisStringResult = analysis.getResult().toString();
    }

    @Override
    public void visitAnalysis(AverageAnalysis analysis) {
        this.analysisStringResult = analysis.getResult().toString();
    }

    @Override
    public void visitAnalysis(RatioAnalysis analysis) {
        this.analysisStringResult = analysis.getResult().toString();
    }

    @Override
    public void visitAnalysis(LazyAnalysis analysis) {
        this.analysisStringResult = analysis.getResult().toString();
    }
}
