package model.users.factories;

import com.fasterxml.jackson.databind.node.ObjectNode;
import model.adapters.JsonToUserAdapter;
import model.users.Patient;
import model.users.User;

/**
 * Concrete factory for creating customers
 */
public class PatientFactory implements UserFactory {

    /**
     * Creates a Customer from API response data
     * @param dataNode API response to GET request for User data
     * @return A Customer instance corresponding to the API response
     */
    @Override
    public User createUser(ObjectNode dataNode) {
        return new Patient(new JsonToUserAdapter(dataNode));
    }
}
