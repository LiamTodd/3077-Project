package controller.user_specific_controller.factories;

import controller.user_specific_controller.PatientController;
import controller.user_specific_controller.UserController;

/**
 * Concrete factory for creating PatientControllers
 */
public class PatientControllerFactory implements UserControllerFactory {

    /**
     * Creates a PatientController
     * @return A PatientController
     */
    @Override
    public UserController createUserController() {
        return new PatientController();
    }
}
