package ca.yorku.eecs3311.team09.analyses;

import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.List;

public interface IAnalysis {
    /**
     * Returns the title of this Analysis
     *
     * @return title of this Analysis
     */
    String getTitle();

    /**
     * Returns the country on which the analysis is being performed.
     *
     * @return country on which the analysis is being performed
     */
    Country getCountry();

    /**
     * Returns the start date of the analysis data.
     *
     * @return start date of the analysis data
     */
    Integer getFromDate();

    /**
     * Returns the end date of the analysis data.
     *
     * @return start end of the analysis data
     */
    Integer getToDate();

    /**
     * Returns the list of indicators the analysis is being performed on.
     *
     * @return list of indicators the analysis is being performed on
     */
    List<Indicator> getIndicators();

    /**
     * Performs the analysis calculation.
     */
    void performCalculation();

    /**
     * Accepts a visitor.
     *
     * @param visitor visitor
     */
    void accept(AnalysisVisitor visitor);
}
