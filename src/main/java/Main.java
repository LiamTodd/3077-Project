import controller.PreLoginController;
import model.APISetUp;

import java.util.Scanner;

/**
 * Driver class for the app
 */
public class Main {
    public static void main(String[] args) throws Exception {

//        get the API key
        APISetUp setUp = APISetUp.getInstance();
        setUp.setUp();

//        set up login controller to run app
        PreLoginController controller = new PreLoginController(new Scanner(System.in));
         controller.controlSystem();
    }

}
