package ca.yorku.eecs3311.team09;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * All tests for the app.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginControllerValidationTest.class,
        SQLUserModelTest.class,
        DataFetcherTest.class
})
public class AllTests {
}
