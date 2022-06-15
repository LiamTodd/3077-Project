package model.users.factories;

import com.fasterxml.jackson.databind.node.ObjectNode;
import model.adapters.JsonToUserAdapter;
import model.users.Receptionist;
import model.users.User;

/**
 * Concrete factory for creating receptionists
 */
public class ReceptionistFactory implements UserFactory {

    /**
     * Creates a Receptionist from API response data
     * @param dataNode API response to GET request for User data
     * @return A Receptionist instance corresponding to the API response
     */
    @Override
    public User createUser(ObjectNode dataNode) {
        return new Receptionist(new JsonToUserAdapter(dataNode));
    }
}
