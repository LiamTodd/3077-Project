package model.tests.type;

import model.tests.location.OnSiteTest;

/**
 * Represents Polymerase Chain Reaction Tests for Covid-19
 */
public class PCRTest extends CovidTest implements OnSiteTest {


    public PCRTest(int testTimeMinutes) {
        super(4320);
    }
    /**
     * method to share the test results with the user
     * @return test results
     */
    @Override
    public boolean shareResults() {
        return false;
    }
    /**
     * Have the test conducted by assigned healthcare worker
     */
    @Override
    public void conductTest() {

    }

    @Override
    public void recordResult() {

    }
}
