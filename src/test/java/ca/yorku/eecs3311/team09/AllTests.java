package ca.yorku.eecs3311.team09;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * All tests for the app.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ DataFetcherTest.class, AnalysisTest.class, AppViewTest.class, LoginControllerTest.class,
		LoginViewTest.class, SQLUserModelTest.class, PlotsModelTest.class, PlotTest.class })
public class AllTests {
}
