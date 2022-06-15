package model.users.factories;

import com.fasterxml.jackson.databind.node.ObjectNode;
import model.users.User;

/**
 * Base class for User factories for factory method pattern
 */
public interface UserFactory {

    /**
     * Creation of User subclass
     * @param dataNode API response to GET request for User data
     * @return the User instance constructed from the API response
     */
    User createUser(ObjectNode dataNode);
}
