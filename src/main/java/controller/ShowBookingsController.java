package controller;

import model.bookings.Booking;
import model.bookings.BookingManager;
import view.ShowBookingsView;

import java.util.ArrayList;
import java.util.Scanner;

public class ShowBookingsController extends Controller{
    public ShowBookingsController(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void controlSystem() throws Exception {
        ShowBookingsView view = new ShowBookingsView(scanner);
        boolean flag = true;

        while(flag){
            // use View to update UI
            String input = view.getUserInput();
            //interpret user input
            flag = interpretUserInput(input);
        }
    }

    @Override
    protected boolean interpretUserInput(String input) throws Exception {
        boolean flag = true;
        ArrayList<Booking> bookings = null;
        if (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5")){ // Validate input
            System.out.println("Invalid input");
        } else if (input.equals("1")){
            System.out.println("All bookings: ");
            bookings = BookingManager.getInstance().getAllBookings();
        } else if (input.equals("2")){
            System.out.println("Which site ID would you like to view bookings for? ");
            input = scanner.nextLine();
            bookings = BookingManager.getInstance().getBookingBySiteId(input); // Filter
        }
        else if (input.equals("3")){
            System.out.println("Cancelled bookings: ");
            bookings = BookingManager.getInstance().getCancelledBookings();
        }
        else if (input.equals("4")){
            System.out.println("non cancelled bookings...");
            bookings = BookingManager.getInstance().getNonCancelledBookings();
        }
        else{
            flag = false;
        }
        showBookings(bookings); // Select test type given by the user.
        return flag;
    }

    private void showBookings(ArrayList<Booking> bookings) {
        if (bookings == null || bookings.size() == 0){ // No sites returned
            System.out.println("Sorry, no such bookings found.");
        }
        else{
            for (int i = 0; i< bookings.size(); i++) { // Iterate through sites
                System.out.println(String.valueOf(i+1) + ". " + bookings.get(i).toString()); // Display each site option to user
            }

        }
    }
}
