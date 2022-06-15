package controller.user_specific_controller;

import view.forms.bookingform.OnsiteTestingFormView;
import view.user_specific_view.AdministererView;


public class AdministererController extends UserController {


    public AdministererController(){super();}


    @Override
    public void controlSystem() throws Exception {

        // create a View
        AdministererView view = new AdministererView(scanner);

        boolean flag = true;

        while (flag){
            // use View to update UI
            String selection = view.getUserInput();

            // interpret user input
            flag = interpretUserInput(selection);
        }

    }

    @Override
    protected boolean interpretUserInput(String input) throws Exception {
        boolean flag = true;
        if (input.equals("1")) { // on site test
            new OnsiteTestingFormView(scanner).printForm();
        } else if (input.equals("2")) { // On-site Booking (the main user will be administrators/receptionists)
            System.out.println("Logging out");
            flag = false;
        } else{
            System.out.println("Invalid option");
        }
        return flag;
    }
}
