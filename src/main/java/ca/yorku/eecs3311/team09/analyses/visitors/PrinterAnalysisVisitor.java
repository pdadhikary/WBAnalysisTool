package ca.yorku.eecs3311.team09.analyses.visitors;

import ca.yorku.eecs3311.team09.analyses.AnnualPercentChangeAnalysis;
import ca.yorku.eecs3311.team09.analyses.AverageAnalysis;
import ca.yorku.eecs3311.team09.analyses.LazyAnalysis;
import ca.yorku.eecs3311.team09.analyses.RatioAnalysis;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Map;

/**
 * A visitor class that prints the analysis result to Standard Output.
 */
public class PrinterAnalysisVisitor implements AnalysisVisitor {

    /**
     * Prints the {@link AnnualPercentChangeAnalysis AnnualPercentChangeAnalysis} result to Standard Output.
     *
     * @param analysis an annual percent change analysis
     */
    @Override
    public void visitAnalysis(AnnualPercentChangeAnalysis analysis) {
        System.out.println(analysis.getTitle() + ":");
        this.defaultPrint(analysis.getResult());
    }

    /**
     * Prints the {@link AverageAnalysis AverageAnalysis} result to Standard Output.
     *
     * @param analysis an average analysis
     */
    @Override
    public void visitAnalysis(AverageAnalysis analysis) {
        System.out.println(analysis.getTitle() + ":");
        this.averagePrint(analysis.getResult());
    }

    /**
     * Prints the {@link RatioAnalysis RatioAnalysis} result to Standard Output.
     *
     * @param analysis a ratio analysis
     */
    @Override
    public void visitAnalysis(RatioAnalysis analysis) {
        System.out.println(analysis.getTitle() + ":");
        this.ratioPrint(analysis.getResult());
    }

    /**
     * Prints the {@link LazyAnalysis LazyAnalysis} result to Standard Output.
     *
     * @param analysis a lazy analysis
     */
    @Override
    public void visitAnalysis(LazyAnalysis analysis) {
        System.out.println(analysis.getTitle() + ":");
        this.defaultPrint(analysis.getResult());
    }

    /**
     * Helper function to print an analysis.
     *
     * @param result analysis result
     */
    protected void defaultPrint(Map<Indicator, Map<Integer, Double>> result) {
        int padding = 20;
        String fStringLabel = "%" + padding + "s";
        String fStringValue = "%" + padding + ".3f";

        System.out.printf(fStringLabel, "Indicator/Year");

        boolean years_printed = false;

        for (Indicator indicator : result.keySet()) {
            if (!years_printed) {
                for (Integer year : result.get(indicator).keySet()) {
                    System.out.printf(fStringLabel, year);
                }
                System.out.print('\n');
                years_printed = true;
            }
            System.out.printf(fStringLabel, indicator.getIndicator_token());
            for (Integer year : result.get(indicator).keySet()) {
                System.out.printf(fStringValue, result.get(indicator).get(year));
            }
            System.out.print('\n');
        }
    }

    /**
     * Helper function to print the result of a ratio analysis.
     *
     * @param result analysis result
     */
    protected void ratioPrint(Map<Integer, Double> result) {
        int padding = 20;
        String fStringLabel = "%" + padding + "s";
        String fStringValue = "%" + padding + ".3f";

        for (Integer year : result.keySet()) {
            System.out.printf(fStringLabel, year);
        }

        System.out.println();

        for (Integer year : result.keySet()) {
            System.out.printf(fStringValue, result.get(year));
        }
        System.out.println();
    }

    /**
     * Helper function to print the result of an average analysis.
     *
     * @param result analysis result
     */
    protected void averagePrint(Map<Indicator, Double> result) {
        int padding = 20;
        String fStringLabel = "%" + padding + "s";
        String fStringValue = "%" + padding + ".3f";

        System.out.printf(fStringLabel + fStringLabel, "Indicator", "Average");
        System.out.print('\n');

        for (Map.Entry<Indicator, Double> entry : result.entrySet()) {
            System.out.printf(
                    fStringLabel + fStringValue + "\n",
                    entry.getKey().getIndicator_token(), entry.getValue());
        }
    }
}
