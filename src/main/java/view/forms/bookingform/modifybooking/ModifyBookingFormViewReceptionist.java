package view.forms.bookingform.modifybooking;

import view.forms.bookingform.BookingFormView;

import java.util.Scanner;

public class ModifyBookingFormViewReceptionist extends BookingFormView {
    public ModifyBookingFormViewReceptionist(Scanner scanner) throws Exception {
        super(scanner);
    }

    @Override
    public void printForm() throws Exception {
        System.out.println("Enter \"ID\" to modify by booking ID and \"pin\" to modify by booking pin");

        String type = scanner.next();

        String input;
        if (type.compareTo("ID") == 0) {
            System.out.println("Enter Booking ID of booking to modify:");
            input = scanner.next();
            setBookingId(input);
            System.out.println("Your booking details are as below:");
            System.out.println(getBookingManager().getBookingById(getBookingId()).get(0));
        } else if (type.compareTo("pin") == 0) {
            System.out.println("Enter PIN of booking to modify:");
            input = scanner.next();
            setPin(input);
            setBookingId(getBookingManager().getBookingBySmsPin(getPin()).get(0).getId());
            System.out.println("Your booking details are as below:");
            System.out.println(getBookingManager().getBookingBySmsPin(getPin()).get(0));
        }

        System.out.println("Enter ID of user:");
        input = scanner.next();
        setUserId(input);
        System.out.println("User: " + getUserId());

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
