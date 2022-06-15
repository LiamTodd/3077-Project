package model.users;

import model.APISetUp;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.EntityManager;
import model.users.factories.UserFactory;

import java.util.ArrayList;

/**
 * Manages the Users in the system
 */
public class UserManager extends EntityManager {

    /**
     * IDEA: UserManager does the API call, UserControllerManager uses UserManager to access users to make controllers.
     */

    private ArrayList<User> allUsers;
    private static UserManager INSTANCE;

    /**
     * A singleton constructor for the UserControllerManager
     * @throws Exception if the API response is invalid when retrieving the Users from the API
     */
    private UserManager() throws Exception {
        this.url = APISetUp.getUrlBase() + "user";
        this.allUsers = this.findAllEntities();
    }

    /**
     * Ensures that only a single instance is present in the system
     * @return
     * @throws Exception
     */
    public static UserManager getInstance() throws Exception {
        if (INSTANCE == null){
            INSTANCE = new UserManager();
        }
        return INSTANCE;
    }

    /**
     * Adds new users to the manager
     * @param jsonNodes An ArrayList of API response data for users
     * @return An ArrayList of User instances corresponding to the jsonNodes data
     * @throws Exception if the API response is invalid
     */
    @Override
    protected ArrayList addEntities(ObjectNode[] jsonNodes) {
        ArrayList entities = new ArrayList();
        for (ObjectNode node : jsonNodes) {
            UserFactory factory = RoleAssigner.assignRoles(node);
            User user = factory.createUser(node);
            entities.add(user);
        }
        return entities;

    }

    /**
     * Allows for user to be accessed via username
     * @param userName The username to search by
     * @return The user with username userName
     * @throws Exception if the API response is invalid
     */
    public User getUserByUsername(String userName) throws Exception {
        if (allUsers == null){
            this.allUsers = findAllEntities();
        }
        for (User user : allUsers){
            String uName = user.getUserName();
            if (uName.replaceAll("^\"|\"$", "").equals(userName)) {
                return user;
            }
        }
        return null;
    }

}
