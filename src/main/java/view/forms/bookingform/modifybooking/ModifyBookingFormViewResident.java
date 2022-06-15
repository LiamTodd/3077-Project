package view.forms.bookingform.modifybooking;

import view.forms.bookingform.BookingFormView;

import java.util.Scanner;

public class ModifyBookingFormViewResident extends BookingFormView {
    public ModifyBookingFormViewResident(Scanner scanner) throws Exception {
        super(scanner);
    }
    /**
     * Displays form to user
     */
    @Override
    public void printForm() throws Exception {
        System.out.println("Enter your ID:");
        String input = scanner.next();
        setUserId(input);
        System.out.println("User: " + getUserId());

        System.out.println("Enter Booking ID of Booking to modify:");
        input = scanner.next();
        setBookingId(input);
        System.out.println("Booking: " + getBookingId());

        System.out.println("Enter Site ID you want test at now");
        input = scanner.next();
        setSiteId(input);
        System.out.println("Site: " + getSiteId());


        System.out.println("Enter date in the format (YYYY-MM-DD) to book for testing:");
        input = scanner.next();
        setTestDate(input);
        System.out.println("Enter time in the format (HH:MM) to book for testing:");
        input = scanner.next();
        setStartTime(input);

        if(isBookingFuture(getBookingId())){
            modifyBooking();
            getBookingManager().modBooking(getBookingId());
        }else{
            System.out.println("Booking is not in the future and can not be modified!");
        }
    }

}
