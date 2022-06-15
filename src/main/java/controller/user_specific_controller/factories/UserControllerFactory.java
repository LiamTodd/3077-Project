package controller.user_specific_controller.factories;

import controller.user_specific_controller.UserController;

/**
 * Base class for UserController factories for factory method pattern
 */
public interface UserControllerFactory {

    /**
     * Creation of a UserController subclass
     * @return a UserController instance
     */
    UserController createUserController();
}
