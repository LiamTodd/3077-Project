package model.tests.location;

/**
 * Interface to add home test functionalities to any sort of test
 */
public interface AtHomeTest extends TestLocation{
    /**
     * Have home test supervised by healthcare worker
     */
    void superviseTest();
}
