package view.forms.bookingform.modifybooking;

import com.fasterxml.jackson.databind.JsonNode;
import view.forms.bookingform.BookingFormView;
import model.bookings.Booking;

import java.util.ArrayList;
import java.util.Scanner;

public class RevertBookingFormView extends BookingFormView {
    public RevertBookingFormView(Scanner scanner) throws Exception {
        super(scanner);
    }


    @Override
    public void printForm() throws Exception {
        System.out.println("Enter booking ID of booking you wish to revert: ");
        String input = scanner.next();
        System.out.println("Reverting back to previous state...");
        Booking booking = getBookingManager().getBookingById(input).get(0);  //Booking to revert

        // get revert stack
        JsonNode revertStack = booking.getRevertStack();

        if (revertStack.size() > 0){
            ArrayList<String> mutableRevertStack = new ArrayList<>();

            // copy over all previous states
            for (JsonNode state: revertStack){
                mutableRevertStack.add(state.toString());
            }

            // pop off last state
            JsonNode prevState = revertStack.get(revertStack.size() - 1);
            mutableRevertStack.remove(mutableRevertStack.size() - 1);

            // update booking with popped state
            booking.setAdditionalInfoAsString(mutableRevertStack);
            booking.setTestingSiteId(prevState.get("testingSiteId").toString().replaceAll("^\"|\"$", ""));
            booking.setStartTime(prevState.get("startTime").toString().replaceAll("^\"|\"$", ""));

            // call api
            getBookingManager().modifyBooking(input, booking.modifiedBookingStringToJson());
            getBookingManager().getAllBookings(); // update local copies
        }
        else{
            System.out.println("No previous states of this booking exist to revert to.");
        }
    }
}
