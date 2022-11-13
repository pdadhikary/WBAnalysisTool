package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.analyses.*;
import ca.yorku.eecs3311.team09.analyses.visitors.AnalysisVisitor;
import ca.yorku.eecs3311.team09.analyses.visitors.PrinterAnalysisVisitor;
import ca.yorku.eecs3311.team09.enums.Country;
import ca.yorku.eecs3311.team09.exceptions.MissingDataException;

public class AnalysisStrategyExample {
    static Country country = Country.INDIA;
    static int fromDate = 2010;
    static int toDate = 2015;

    static AnalysisVisitor printVisitor = new PrinterAnalysisVisitor();

    public static void main(String[] args) {

        try {
            run_analysis1();
            System.out.println(System.lineSeparator());
        } catch (MissingDataException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }

        try {
            run_analysis2();
            System.out.println(System.lineSeparator());
        } catch (MissingDataException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }

        try {
            run_analysis3();
            System.out.println(System.lineSeparator());
        } catch (MissingDataException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }

        try {
            run_analysis4();
            System.out.println(System.lineSeparator());
        } catch (MissingDataException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }

        try {
            run_analysis5();
            System.out.println(System.lineSeparator());
        } catch (MissingDataException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }

        try {
            run_analysis6();
            System.out.println(System.lineSeparator());
        } catch (MissingDataException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }

        try {
            run_analysis7();
            System.out.println(System.lineSeparator());
        } catch (MissingDataException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }

        try {
            run_analysis8();
            System.out.println(System.lineSeparator());
        } catch (MissingDataException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public static void run_analysis1() {
        IAnalysis analysis1 = new AirPollutionForestArea(country, fromDate, toDate);
        analysis1.performCalculation();
        System.out.print("1. ");
        analysis1.accept(printVisitor);
    }

    public static void run_analysis2() {
        IAnalysis analysis2 = new CO2EnergyUseAirPollution(country, fromDate, toDate);
        analysis2.performCalculation();
        System.out.print("2. ");
        analysis2.accept(printVisitor);
    }

    public static void run_analysis3() {
        IAnalysis analysis3 = new GovEducationHealthExpenditure(country, fromDate, toDate);
        analysis3.performCalculation();
        System.out.print("3. ");
        analysis3.accept(printVisitor);
    }

    public static void run_analysis4() {
        IAnalysis analysis4 = new HealthCareMortality(country, fromDate, toDate);
        analysis4.performCalculation();
        System.out.print("4. ");
        analysis4.accept(printVisitor);
    }

    public static void run_analysis5() {
        IAnalysis analysis5 = new CO2GDP(country, fromDate, toDate);
        analysis5.performCalculation();
        System.out.print("5. ");
        analysis5.accept(printVisitor);
    }

    public static void run_analysis6() {
        IAnalysis analysis6 = new HealthExpenditureHospitalBeds(country, fromDate, toDate);
        analysis6.performCalculation();
        System.out.print("6. ");
        analysis6.accept(printVisitor);
    }

    public static void run_analysis7() {
        IAnalysis analysis7 = new GovernmentExpenditure(country, fromDate, toDate);
        analysis7.performCalculation();
        System.out.print("7. ");
        analysis7.accept(printVisitor);
    }

    public static void run_analysis8() {
        IAnalysis analysis8 = new ForestArea(country, fromDate, toDate);
        analysis8.performCalculation();
        System.out.print("8. ");
        analysis8.accept(printVisitor);
    }
}
