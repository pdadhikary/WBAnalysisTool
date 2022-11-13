package ca.yorku.eecs3311.team09.analyses.visitors;

import ca.yorku.eecs3311.team09.analyses.*;

public interface PlotVisitor {
    void plotAnalysis(AirPollutionForestArea analysis);

    void plotAnalysis(CO2EnergyUseAirPollution analysis);

    void plotAnalysis(CO2GDP analysis);

    void plotAnalysis(ForestArea analysis);

    void plotAnalysis(GovEducationHealthExpenditure analysis);

    void plotAnalysis(GovernmentExpenditure analysis);

    void plotAnalysis(HealthCareMortality analysis);

    void plotAnalysis(HealthExpenditureHospitalBeds analysis);
}
