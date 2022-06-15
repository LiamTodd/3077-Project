package controller.user_specific_controller.factories;

import controller.user_specific_controller.ReceptionistController;
import controller.user_specific_controller.UserController;

/**
 * Concrete factory for creating ReceptionistControllers
 */
public class ReceptionistControllerFactory implements UserControllerFactory {

    /**
     * Creates a ReceptionistController
     * @return A ReceptionistController instance
     */
    @Override
    public UserController createUserController() {
        return new ReceptionistController();
    }
}
