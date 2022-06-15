package controller;

import controller.user_specific_controller.UserController;
import controller.user_specific_controller.UserControllerAssigner;
import model.users.User;
import model.users.UserManager;
import model.verification.VerifyJWT;
import model.verification.VerifyLogin;
import view.PreLoginView;

import java.util.Scanner;

/**
 * This class is a controller for the functionality available to the user before logging in.
 */
public class PreLoginController extends Controller{

    public PreLoginController(Scanner scanner){
        super(scanner);
    }


    /**
     * This method uses a PreLoginView to update the console UI and interprets the user's input, making communications with
     * the Model to validate a user's login attempts
     * @throws Exception
     */
    public void controlSystem() throws Exception {
        // create a View
        PreLoginView view = new PreLoginView(scanner);

        boolean flag = true;

        while (flag){
            // use View to update UI
            String selection = view.getUserInput();

            // interpret user input
            flag = interpretUserInput(selection);
        }
    }

    /**
     * This method interprets the user's input and conducts different logic depending on their selections
     * @param input
     * @return
     * @throws Exception
     */
    protected boolean interpretUserInput(String input) throws Exception {
        String jWT;
        VerifyJWT verifyJWT = new VerifyJWT();
        boolean flag = true;
        if (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")){ // Validate user input
            System.out.println("Invalid input");
        }
        else if (input.equals("1")) { //Login menu
            System.out.println("Login Menu -->");
            VerifyLogin verifyLogin = new VerifyLogin(scanner);
            jWT =  verifyLogin.authenticate();  // Ask for and verify Login credentials

            if (verifyJWT.verify(jWT)) { // Verify if JWT is still valid

                User user = UserManager.getInstance().getUserByUsername(verifyLogin.getUserName());
                UserController userController = UserControllerAssigner.assignController(user).createUserController();
                userController.setWorksAt(user.getWorksAt());
                userController.controlSystem();


            }
        } else if (input.equals("2")) { // Testing site search menu
            SearchController searchController = new SearchController(scanner);
            searchController.controlSystem();
        } else if (input.equals("3")) { // Testing site search menu
            System.out.println("Booking Forecast....");
            new BookingForecaster(scanner).forecast();


        } else {
            flag = false;
        }
        return flag;
    }
}
