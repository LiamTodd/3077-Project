package model.users;

import com.fasterxml.jackson.databind.node.ObjectNode;
import model.users.factories.AdministererFactory;
import model.users.factories.PatientFactory;
import model.users.factories.ReceptionistFactory;
import model.users.factories.UserFactory;

/**
 * Contains the logic to decide the concrete type of User from the api response
 */
public class RoleAssigner {

    /**
     * Returns the correct type of factory corresponding to the role of the user, defined by their data in the API
     * @param dataNode The API response corresponding to the user
     * @return A UserFactory which will generate the correct type of user according to the API response
     */
    public static UserFactory assignRoles(ObjectNode dataNode) {

        if (dataNode.findValue("isCustomer").asBoolean()) {
            return new PatientFactory();
        } else if (dataNode.findValue("isReceptionist").asBoolean()) {
            return new ReceptionistFactory();
        } else if (dataNode.findValue("isHealthcareWorker").asBoolean()) {
            return new AdministererFactory();
        }else{
            System.out.println("No role assigned in the system so defaulted to Customer.");
            return new PatientFactory();
        }
    }
}
