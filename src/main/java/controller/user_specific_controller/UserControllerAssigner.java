package controller.user_specific_controller;

import controller.user_specific_controller.factories.AdministererControllerFactory;
import controller.user_specific_controller.factories.PatientControllerFactory;
import controller.user_specific_controller.factories.ReceptionistControllerFactory;
import controller.user_specific_controller.factories.UserControllerFactory;
import model.users.User;

/**
 * Contains the logic to decide the concrete type of UserController
 */
public class UserControllerAssigner {

    /**
     * Returns the correct type of factory corresponding to the role of the user
     * @param user The user instance to create a controller for
     * @return A UserControllerFactory which will generate the correct type of controller
     */
    public static UserControllerFactory assignController(User user) {

        if (user.isPatient()) {
            return new PatientControllerFactory();
        } else if (user.isReceptionist()) {
            return new ReceptionistControllerFactory();
        } else if (user.isAdministerer()) {
            return new AdministererControllerFactory();
        }else{
            System.out.println("No role assigned in the system so defaulted to Customer.");
            return new PatientControllerFactory();
        }
    }
}
