package ca.yorku.eecs3311.team09.analyses;

public interface AnalysisVisitor {
    void visitAnalysis(AnnualPercentChangeAnalysis analysis);

    void visitAnalysis(AverageAnalysis analysis);

    void visitAnalysis(RatioAnalysis analysis);

    void visitAnalysis(LazyAnalysis analysis);
}
