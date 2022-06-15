package view.forms.bookingform;

import java.io.IOException;
import java.util.Scanner;

public class HomeBookingFormView extends BookingFormView {
    public HomeBookingFormView(Scanner scanner) throws Exception {
        super(scanner);
    }
    /**
     * Displays form to user
     */
    @Override
    public void printForm() throws Exception {
        inputUserId();
        inputDateTime();
        inputNotes();
        askRAT();
    }

    /**
     * Asks user if they need or already have a RAT test kit
     * @throws IOException
     * @throws InterruptedException
     */
    private void askRAT() throws Exception {
        System.out.println("Enter site ID of the site where you want to collect a RAT test from OR hit 'enter' if you already have a RAT test kit.");
        setSiteId(scanner.nextLine()); //Gets site from user

        if (getSiteId() != null){ // If user presses enter, they already have a RAT.

            setAdditionalInfo(getNode().put("revertStack", "[]")); // add QR code to additional info
//            if(getVerifyJWT().verify(this.getJWT())){
//                bookTest();
//            }
            bookTest();
            System.out.println("Your QR code has been sent");
        }
        else{
            System.out.println("We hope your RAT test turns out negative! No QR code has been sent.");
        }
    }
}

