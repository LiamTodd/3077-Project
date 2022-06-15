package view.forms.bookingform;


import java.util.Scanner;

public class OnsiteBookingFormView extends BookingFormView {

    public OnsiteBookingFormView(Scanner scanner) throws Exception {
            super(scanner);
        }
        /**
        * Displays form to user
         */
        @Override
        public void printForm() throws Exception {
            inputUserId();
            inputSiteId();
            inputDateTime();
            inputNotes();
            setAdditionalInfo(getNode().put("i", getTestRecommended()));
            setAdditionalInfo(getNode().put("revertStack", "[]"));
            bookTest();
        }
    }
