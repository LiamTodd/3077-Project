package view.forms.bookingform.modifybooking;

import view.forms.bookingform.BookingFormView;

import java.io.IOException;
import java.util.Scanner;

public class DeleteBookingFormView extends BookingFormView {
    private String bookingID;
    public DeleteBookingFormView(Scanner scanner) throws Exception {
        super(scanner);
    }

    @Override
    public void printForm() throws Exception {
        System.out.println("Enter Booking ID of booking to delete:");
        inputBookingID();
        if(isBookingFuture(getBookingID())){
            deleteBooking();
        }else{
            System.out.println("Booking is not in the future and can not be deleted!");
        }

    }

    public String getBookingID() {
        return bookingID;
    }

    @Override
    public void inputBookingID(){
        bookingID = scanner.next();
    }


    private void deleteBooking() throws IOException, InterruptedException {
        if(getBookingManager().deleteBooking(getBookingID())){
            System.out.println("Booking Deleted!");
        }else {
            System.out.println("Failed to Delete Booking!");
        }

    }
}
