package model.tests.type;

/**
 * Represents all sorts of covid model.tests
 */
public abstract class CovidTest{
    private boolean results;
    private int testTimeMinutes;

    public CovidTest(int testTimeMinutes) {
        this.testTimeMinutes = testTimeMinutes;
    }

    /**
     * method to share the test results with the user
     * @return test results
     */
    public abstract boolean shareResults();

    /**
     * Have the test conducted by assigned healthcare worker
     */
    public abstract void conductTest();
}
