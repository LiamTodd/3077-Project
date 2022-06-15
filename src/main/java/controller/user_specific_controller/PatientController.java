package controller.user_specific_controller;

import controller.SearchController;
import view.forms.bookingform.HomeBookingFormView;
import view.forms.bookingform.modifybooking.CancelBookingFormView;
import view.forms.bookingform.modifybooking.CheckBookingFormView;
import view.forms.bookingform.modifybooking.ModifyBookingFormViewResident;
import view.forms.bookingform.modifybooking.RevertBookingFormView;
import view.user_specific_view.PatientView;

/**
 * Represents a customer in the system
 */
public class PatientController extends UserController {

    public PatientController(){super();}

    @Override
    public void controlSystem() throws Exception {

        // create a View
        PatientView view = new PatientView(scanner);

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

        if (input.equals("1")) { // Testing site search menu
            SearchController searchController = new SearchController(scanner);
            searchController.controlSystem();
        } else if (input.equals("2")) { // On-site Booking (the main user will be administrators/receptionists)
            System.out.println("Booking Home Test");
            new HomeBookingFormView(scanner).printForm();
        } else if (input.equals("3")) { // On-site Booking (the main user will be administrators/receptionists)
            System.out.println("Check Booking");
            new CheckBookingFormView(scanner).printForm();
        }else if (input.equals("4")) { // On-site Booking (the main user will be administrators/receptionists)
            System.out.println("Options to modify Booking");
            new ModifyBookingFormViewResident(scanner).printForm();
        }else if (input.equals("5")) { // On-site Booking (the main user will be administrators/receptionists)
            System.out.println("Options to Cancel Booking");
            new CancelBookingFormView(scanner).printForm();
        }else if (input.equals("6")) { // On-site Booking (the main user will be administrators/receptionists)
            System.out.println("Options to Revert Booking");
            new RevertBookingFormView(scanner).printForm();
        }else if (input.equals("7")) { // On-site Testing (the main user will be healthcare workers/administerer)
            System.out.println("Logging out");
            flag = false;
        } else{
            System.out.println("Invalid option");
        }

        return flag;
    }
}
