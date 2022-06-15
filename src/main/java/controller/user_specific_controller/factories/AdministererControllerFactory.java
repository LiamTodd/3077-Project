package controller.user_specific_controller.factories;

import controller.user_specific_controller.AdministererController;
import controller.user_specific_controller.UserController;

/**
 * Concrete factory for creating AdministerControllers
 */
public class AdministererControllerFactory implements UserControllerFactory {

    /**
     * Creates a AdministererController
     * @return An Administerer instance
     */
    @Override
    public UserController createUserController() {
        return new AdministererController();
    }
}
