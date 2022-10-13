package ca.yorku.eecs3311.team09.analyses.data_manipulation;

import ca.yorku.eecs3311.team09.enums.Indicator;

import java.util.Map;

public interface SeriesOperation {
    void calculate(Map<Indicator, Map<Integer, Double>> table);
}
