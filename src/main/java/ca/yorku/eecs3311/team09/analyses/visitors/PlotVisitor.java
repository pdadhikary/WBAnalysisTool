package ca.yorku.eecs3311.team09.analyses.visitors;

import ca.yorku.eecs3311.team09.analyses.*;
import ca.yorku.eecs3311.team09.exceptions.IncompatibleAnalysisException;

/**
 * A plotter visitor capable of plotting an {@link Analysis Analysis}.
 * <p>
 * The visitor may throw {@link IncompatibleAnalysisException IncompatibleAnalysisException}
 * if it is not compatible with the analysis being plotted.
 */
public interface PlotVisitor {

    /**
     * Create a plot of the
     * {@link  AirPollutionForestArea Air Pollution vs Forest Area} analysis result.
     *
     * @param analysis analysis to plot.
     */
    void plotAnalysis(AirPollutionForestArea analysis);

    /**
     * Create a plot of the
     * {@link  CO2EnergyUseAirPollution CO2 vs Energy Use vs Air Pollution} analysis result.
     *
     * @param analysis analysis to plot.
     */
    void plotAnalysis(CO2EnergyUseAirPollution analysis);

    /**
     * Create a plot of the
     * {@link  CO2GDP CO2 vs GDP} analysis result.
     *
     * @param analysis analysis to plot.
     */
    void plotAnalysis(CO2GDP analysis);

    /**
     * Create a plot of the
     * {@link  ForestArea Forest Area} analysis result.
     *
     * @param analysis analysis to plot.
     */
    void plotAnalysis(ForestArea analysis);

    /**
     * Create a plot of the
     * {@link  GovEducationHealthExpenditure Government Education vs Health Expenditure}
     * analysis result.
     *
     * @param analysis analysis to plot.
     */
    void plotAnalysis(GovEducationHealthExpenditure analysis);

    /**
     * Create a plot of the
     * {@link  GovernmentExpenditure Govenment Expenditure on Education} analysis result.
     *
     * @param analysis analysis to plot.
     */
    void plotAnalysis(GovernmentExpenditure analysis);

    /**
     * Create a plot of the
     * {@link  HealthCareMortality Women Healthcare Problem vs Infant Mortality} analysis result.
     *
     * @param analysis analysis to plot.
     */
    void plotAnalysis(HealthCareMortality analysis);

    /**
     * Create a plot of the
     * {@link  HealthExpenditureHospitalBeds Govenment Health Expenditure vs Number of Hospital Beds}
     * analysis result.
     *
     * @param analysis analysis to plot.
     */
    void plotAnalysis(HealthExpenditureHospitalBeds analysis);
}
