package ca.yorku.eecs3311.team09.analyses.data_manipulation;

import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Map;

/**
 * Perform a manipulation operation on the data table.
 */
public interface SeriesOperation {
    /**
     * performs the operation on the data table.
     *
     * @param table data table
     */
    void calculate(Map<Indicator, Map<Integer, Double>> table);
}
