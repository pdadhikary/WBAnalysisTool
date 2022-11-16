package ca.yorku.eecs3311.team09.analyses.factory;

import ca.yorku.eecs3311.team09.analyses.*;
import ca.yorku.eecs3311.team09.enums.Country;

public class AnalysisFactory {
    private String analysisName;
    private String analysisCode;

    public AnalysisFactory(String analysisCode, String analysisTitle) {
        if (analysisCode == null) {
            throw new RuntimeException("Analysis code cannot be null!");
        }

        this.analysisCode = analysisCode;
        this.analysisName = analysisTitle;
    }

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
                throw new RuntimeException("Unrecognized analysis...");
        }

        return analysis;
    }

    @Override
    public String toString() {
        return analysisName;
    }
}
