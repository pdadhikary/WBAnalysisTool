package ca.yorku.eecs3311.team09;

import ca.yorku.eecs3311.team09.analyses.*;
import ca.yorku.eecs3311.team09.enums.Country;

public class AnalysisStrategyExample {
    static Country country = Country.USA;
    static int fromDate = 2010;
    static int toDate = 2015;

    public static void main(String[] args) {
        run_analysis1();
        System.out.println(System.lineSeparator());

        run_analysis2();
        System.out.println(System.lineSeparator());

        run_analysis3();
        System.out.println(System.lineSeparator());

        run_analysis4();
        System.out.println(System.lineSeparator());

        run_analysis5();
        System.out.println(System.lineSeparator());

        run_analysis6();
        System.out.println(System.lineSeparator());
        
        run_analysis7();
        System.out.println(System.lineSeparator());

        run_analysis8();
        System.out.println(System.lineSeparator());
    }

    public static void run_analysis1() {
        Analysis analysis1 = new APCAirPollutionForestArea();
        analysis1.setData(country, fromDate, toDate);
        System.out.print("1. ");
        analysis1.showResult();
    }

    public static void run_analysis2() {
        Analysis analysis2 = new APCCO2EnergyUseAirPollution();
        analysis2.setData(country, fromDate, toDate);
        System.out.print("2. ");
        analysis2.showResult();
    }

    public static void run_analysis3() {
        Analysis analysis3 = new APCGovExpHealthExp();
        analysis3.setData(country, fromDate, toDate);
        System.out.print("3. ");
        analysis3.showResult();
    }

    public static void run_analysis4() {
        Analysis analysis4 = new HealthCareMortality();
        analysis4.setData(country, fromDate, toDate);
        System.out.print("4. ");
        analysis4.showResult();
    }

    public static void run_analysis5() {
        Analysis analysis5 = new RatioCO2GDP();
        analysis5.setData(country, fromDate, toDate);
        System.out.print("5. ");
        analysis5.showResult();
    }

    public static void run_analysis6() {
        Analysis analysis6 = new RatioHealthExpHospitalBeds();
        analysis6.setData(country, fromDate, toDate);
        System.out.print("6. ");
        analysis6.showResult();
    }

    public static void run_analysis7() {
        Analysis analysis6 = new AvgGovExp();
        analysis6.setData(country, fromDate, toDate);
        System.out.print("7. ");
        analysis6.showResult();
    }

    public static void run_analysis8() {
        Analysis analysis6 = new AvgForestArea();
        analysis6.setData(country, fromDate, toDate);
        System.out.print("8. ");
        analysis6.showResult();
    }
}
