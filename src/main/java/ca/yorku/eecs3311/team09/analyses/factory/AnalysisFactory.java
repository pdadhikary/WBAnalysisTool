package ca.yorku.eecs3311.team09.analyses.factory;

import ca.yorku.eecs3311.team09.analyses.*;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.exceptions.UnsupportedAnalysisException;

/**
 * A class that acts as a dispatcher for {@link Analysis Analysis} objects.
 */
public class AnalysisFactory {
    /**
     * Name of the analysis
     */
    protected final String analysisName;
    /**
     * Code of the analysis
     */
    protected final String analysisCode;

    /**
     * Initialize an AnalysisFactory.
     *
     * @param analysisCode code of the analysis
     * @param analysisName name of the analysis
     */
    public AnalysisFactory(String analysisCode, String analysisName) {
        if (analysisCode == null) {
            throw new RuntimeException("Analysis code cannot be null!");
        }

        this.analysisCode = analysisCode;
        this.analysisName = analysisName;
    }

    /**
     * Return a new analysis object initialized with the provided options.
     *
     * @param country  country of the analysis
     * @param fromDate start date of the analysis
     * @param toDate   end date of the analysis
     * @return a new analysis object
     */
    public IAnalysis getAnalysis(Country country, Integer fromDate, Integer toDate) {
        IAnalysis analysis;

        switch (analysisCode) {
            case "AirPollutionForestArea":
                analysis = new AirPollutionForestArea(country, fromDate, toDate);
                break;
            case "CO2EnergyUseAirPollution":
                analysis = new CO2EnergyUseAirPollution(country, fromDate, toDate);
                break;
            case "CO2GDP":
                analysis = new CO2GDP(country, fromDate, toDate);
                break;
            case "ForestArea":
                analysis = new ForestArea(country, fromDate, toDate);
                break;
            case "GovEducationHealthExpenditure":
                analysis = new GovEducationHealthExpenditure(country, fromDate, toDate);
                break;
            case "GovernmentExpenditure":
                analysis = new GovernmentExpenditure(country, fromDate, toDate);
                break;
            case "HealthCareMortality":
                analysis = new HealthCareMortality(country, fromDate, toDate);
                break;
            case "HealthExpenditureHospitalBeds":
                analysis = new HealthExpenditureHospitalBeds(country, fromDate, toDate);
                break;
            default:
                throw new UnsupportedAnalysisException(this.analysisName + " is an unsupported analysis");
        }

        return analysis;
    }

    /**
     * Return the analysis code.
     *
     * @return code of the analysis
     */
    public String getAnalysisCode() {
        return analysisCode;
    }

    /**
     * String representation of the analysis.
     *
     * @return name of the analysis
     */
    @Override
    public String toString() {
        return analysisName;
    }
}
