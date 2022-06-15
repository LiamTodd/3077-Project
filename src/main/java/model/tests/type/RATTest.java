package model.tests.type;

import model.tests.location.AtHomeTest;
import model.tests.location.OnSiteTest;

/**
 * Represents Rapid Antigen Test for Covid-19
 */
public class RATTest extends CovidTest implements OnSiteTest, AtHomeTest {


    public RATTest(int testTimeMinutes) {
        super(15);
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
    /**
     * Have home test supervised by healthcare worker
     */
    @Override
    public void superviseTest() {

    }

    @Override
    public void recordResult() {

    }
}
