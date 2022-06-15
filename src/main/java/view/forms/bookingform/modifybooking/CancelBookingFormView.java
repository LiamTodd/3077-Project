package view.forms.bookingform.modifybooking;

import view.forms.bookingform.BookingFormView;

import java.io.IOException;
import java.util.Scanner;

public class CancelBookingFormView extends BookingFormView {

    public CancelBookingFormView(Scanner scanner) throws Exception {
        super(scanner);
    }

    @Override
    public void printForm() throws Exception {
        System.out.println("Enter Booking ID of Booking you wish to cancel: ");
        String input = scanner.next();
        setBookingId(input);
        if(!input.isEmpty()){
            cancelBooking();
        }
    }

    private void cancelBooking() throws IOException, InterruptedException {
        getBookingManager().cancelBooking(getBookingId());
    }
}
