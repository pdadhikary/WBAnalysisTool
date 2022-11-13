package ca.yorku.eecs3311.team09.analyses.visitors;

import ca.yorku.eecs3311.team09.analyses.AnnualPercentChangeAnalysis;
import ca.yorku.eecs3311.team09.analyses.AverageAnalysis;
import ca.yorku.eecs3311.team09.analyses.LazyAnalysis;
import ca.yorku.eecs3311.team09.analyses.RatioAnalysis;

public interface AnalysisVisitor {
    void visitAnalysis(AnnualPercentChangeAnalysis analysis);

    void visitAnalysis(AverageAnalysis analysis);

    void visitAnalysis(RatioAnalysis analysis);

    void visitAnalysis(LazyAnalysis analysis);
}
