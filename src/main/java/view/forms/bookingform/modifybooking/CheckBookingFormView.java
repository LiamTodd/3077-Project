package view.forms.bookingform.modifybooking;

import view.forms.bookingform.BookingFormView;
import model.bookings.Booking;

import java.util.ArrayList;
import java.util.Scanner;

public class CheckBookingFormView extends BookingFormView {
    public CheckBookingFormView(Scanner scanner) throws Exception {
        super(scanner);
    }

    @Override
    public void printForm() throws Exception {
        System.out.println("Enter \"all\" to filter bookings by active status or \"id\" to check status by booking id:");
        String type = scanner.next();
        String input;
        if(type.compareTo("all") == 0){
            System.out.println("Enter your user ID: ");
            input = scanner.next();

            System.out.println("Your active bookings as user " + input + " are as below:");

            ArrayList<Booking> active = getBookingManager().getNonCancelledBookings();
            for (Booking each:active) {
                //System.out.println(each.getId());
                if(input.compareTo(each.getCustomerId())==0){
                    System.out.println(each.toString());
                    //System.out.println("Booking: " + each.getId() + " for customer: "+ each.getCustomerId() + " and Status: " + each.getStatus());
                }
            }
        } else if (type.compareTo("id") == 0) {
            System.out.println("Enter ID of booking you wish to check");
            input = scanner.next();

            System.out.println("Your booking details are as below:");
            System.out.println(getBookingManager().getBookingById(input).get(0).toString());
            //System.out.println(getBookingManager().getBookingBySmsPin(getPin()).get(0).toString());
        }
    }
}
