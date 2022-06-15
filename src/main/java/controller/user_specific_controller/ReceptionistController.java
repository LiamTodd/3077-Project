package controller.user_specific_controller;

import controller.ShowBookingsController;
import controller.ShowNotificationsController;
import view.forms.bookingform.OnsiteBookingFormView;
import view.forms.bookingform.modifybooking.CancelBookingFormView;
import view.forms.bookingform.modifybooking.DeleteBookingFormView;
import view.forms.bookingform.modifybooking.ModifyBookingFormViewReceptionist;
import view.forms.bookingform.modifybooking.RevertBookingFormView;
import view.user_specific_view.ReceptionistView;

/**
 * Represents a Receptionist User
 */
public class ReceptionistController extends UserController {


    public ReceptionistController(){super();};

    @Override
    public void controlSystem() throws Exception {

        // create a View
        ReceptionistView view = new ReceptionistView(scanner);

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
            System.out.println("Booking on-site test");
            new OnsiteBookingFormView(scanner).printForm();
        } else if (input.equals("2")){
            ShowBookingsController showBookingsController = new ShowBookingsController(scanner);
            showBookingsController.controlSystem();
        }
        else if (input.equals("3")){
            System.out.println("Cancel Booking selected: ");
            new CancelBookingFormView(scanner).printForm();
        }
        else if (input.equals("4")){
            System.out.println("Delete Booking selected: ");
            new DeleteBookingFormView(scanner).printForm();
        }
        else if (input.equals("5")){
            System.out.println("Modify Booking selected: ");
            new ModifyBookingFormViewReceptionist(scanner).printForm();
        }
        else if (input.equals("6")){
            System.out.println("Options to Revert Booking");
            new RevertBookingFormView(scanner).printForm();
        }
        else if (input.equals("7")){
            System.out.println("Notifications relevant for the site you work at: ");
            System.out.println(this.getWorksAt());
            ShowNotificationsController showNotificationsController = new ShowNotificationsController(scanner, this.getWorksAt());
            showNotificationsController.controlSystem();
        }
        else if (input.equals("8")) { // On-site Testing (the main user will be healthcare workers/administerer)
            System.out.println("Logging out");
            flag = false;
        } else{
            System.out.println("Invalid option");
        }
        return flag;
    }
}
