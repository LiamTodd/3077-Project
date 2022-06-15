package model.users.factories;

import com.fasterxml.jackson.databind.node.ObjectNode;
import model.adapters.JsonToUserAdapter;
import model.users.Administerer;
import model.users.User;

/**
 * Concrete factory for creating adminsiterers
 */
public class AdministererFactory implements UserFactory {

    /**
     * Creates a Administerer from API response data
     * @param dataNode API response to GET request for User data
     * @return An Administerer instance corresponding to the API response
     */
    @Override
    public User createUser(ObjectNode dataNode) {
        return new Administerer(new JsonToUserAdapter(dataNode));
    }
}
