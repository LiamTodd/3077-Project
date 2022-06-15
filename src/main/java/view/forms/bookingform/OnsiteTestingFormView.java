package view.forms.bookingform;

import java.util.Scanner;

public class OnsiteTestingFormView extends BookingFormView {
    public OnsiteTestingFormView(Scanner scanner) throws Exception {
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
        inputTestRecommended();
        System.out.println("Healthcare worker now performing " + getTestRecommended());

        bookTest();

    }
}
